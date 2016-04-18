package com.devmpv.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.JDOEnhancer;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;
import org.datanucleus.metadata.PersistenceUnitMetaData;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devmpv.model.Post;

/**
 * JDO configuration
 * 
 * @author user1
 */
@Configuration
public class ChanConfig {

	//@formatter:off
	private static final Set<Class<?>> ENTITIES = new HashSet<Class<?>>(Arrays.asList(
				Post.class, Thread.class
			));
	//@formatter:on

	/**
	 * Enhances entities
	 * 
	 * @return set of entity names
	 */
	public Set<String> jdoEnhance() {
		Set<String> names = new HashSet<String>();
		JDOEnhancer enhancer = JDOHelper.getEnhancer();
		ENTITIES.forEach(e -> {
			names.add(e.getName());
			enhancer.addClasses(e.getName());
		});
		enhancer.setVerbose(true);
		enhancer.enhance();
		return names;
	}

	/**
	 * Configuration of DataNucleus {@link PersistenceManagerFactory}
	 * 
	 * @return {@link PersistenceManagerFactory}
	 */
	@Bean
	@ConfigurationProperties
	public PersistenceManagerFactory persistenceManagerFactory() {
		PersistenceUnitMetaData pumd = new PersistenceUnitMetaData("ChanForge", "RESOURCE_LOCAL", null);
		pumd.addClassNames(jdoEnhance());
		pumd.setExcludeUnlistedClasses();
		System.getProperties().entrySet().stream().filter(a -> {
			String key = ((String) a.getKey());
			return key.startsWith("javax.jdo.") || key.startsWith("datanucleus.");
		}).forEach(a -> pumd.addProperty((String) a.getKey(), (String) a.getValue()));
		return new JDOPersistenceManagerFactory(pumd, null);
	}
}
