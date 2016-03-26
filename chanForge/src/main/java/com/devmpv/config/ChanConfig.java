package com.devmpv.config;

import javax.jdo.JDOEnhancer;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;
import org.datanucleus.metadata.PersistenceUnitMetaData;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devmpv.model.Post;

@Configuration
public class ChanConfig {

	@Bean
	public JDOEnhancer jdoEnhancer() {
		JDOEnhancer enhancer = JDOHelper.getEnhancer();
		enhancer.setVerbose(true);
		enhancer.addClasses(Post.class.getName());
		enhancer.enhance();
		return enhancer;
	}

	/**
	 * Configuration of DataNucleus {@link PersistenceManagerFactory}
	 * 
	 * @return {@link PersistenceManagerFactory}
	 */
	@Bean
	@ConfigurationProperties
	public PersistenceManagerFactory persistenceManagerFactory() {
		/*
		 * PersistenceUnitMetaData pumd = new
		 * PersistenceUnitMetaData("ChanForge", "RESOURCE_LOCAL", null);
		 * pumd.addClassName(Post.class.getName());
		 * pumd.setExcludeUnlistedClasses();
		 * pumd.addProperty("javax.jdo.ConnectionDriverName", driver);
		 * pumd.addProperty("javax.jdo.ConnectionURL", url);
		 * pumd.addProperty("javax.jdo.ConnectionUserName", user);
		 * pumd.addProperty("javax.jdo.ConnectionPassword", password);
		 * pumd.addProperty("datanucleus.autoCreateSchema", "true");
		 */

		PersistenceUnitMetaData pumd = new PersistenceUnitMetaData("ChanForge", "RESOURCE_LOCAL", null);
		pumd.addClassName(Post.class.getName());
		pumd.setExcludeUnlistedClasses();
		System.getProperties().entrySet().stream().filter(a -> {
			String key = ((String) a.getKey());
			return key.startsWith("javax.jdo.") || key.startsWith("datanucleus.");
		}).forEach(a -> pumd.addProperty((String) a.getKey(), (String) a.getValue()));
		return new JDOPersistenceManagerFactory(pumd, null);
	}

}
