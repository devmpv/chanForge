package com.devmpv.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.jdo.JDOEnhancer;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;
import org.datanucleus.metadata.PersistenceUnitMetaData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;

import com.devmpv.model.OPost;
import com.devmpv.model.RPost;

/**
 * JDO and Datanucleus configuration
 * 
 * @author pmuravyov
 */
@Configuration
public class ChanConfig {

	//@formatter:off
	private static final Set<Class<?>> ENTITIES = new HashSet<Class<?>>(Arrays.asList(
				RPost.class,
				OPost.class
			));
	private static final Set<String> NAMES = getEntityNames();
	//@formatter:on

	private static Set<String> getEntityNames() {
		Set<String> result = new HashSet<>();
		ENTITIES.forEach(e -> result.add(e.getName()));
		return result;
	}

	/**
	 * Enhances entities
	 * 
	 * @return set of entity names
	 */
	public static void jdoEnhance() {
		Set<String> names = new HashSet<String>();
		JDOEnhancer enhancer = JDOHelper.getEnhancer();
		ENTITIES.forEach(e -> {
			names.add(e.getName());
			enhancer.addClasses(e.getName());
		});
		enhancer.setVerbose(true);
		enhancer.enhance();
	}

	@Inject
	private Environment env;

	/*
	 * @Bean public JdoTransactionManager jdoTransactionManager() { return new
	 * JdoTransactionManager(persistenceManagerProxyBean().
	 * getTargetPersistenceManagerFactory()); }
	 */

	/**
	 * Configuration of DataNucleus {@link PersistenceManagerFactory}
	 * 
	 * @return {@link PersistenceManagerFactory}
	 */
	@Bean
	public PersistenceManagerFactory persistenceManagerFactory() {
		PersistenceUnitMetaData pumd = new PersistenceUnitMetaData("ChanForge", "RESOURCE_LOCAL", null);
		pumd.addClassNames(NAMES);
		pumd.setExcludeUnlistedClasses();
		((AbstractEnvironment) env).getPropertySources().forEach(source -> {
			if (source instanceof MapPropertySource) {
				((MapPropertySource) source).getSource().entrySet().stream().filter(entry -> {
					String key = (entry.getKey());
					return key.startsWith("javax.jdo.") || key.startsWith("datanucleus.");
				}).forEach(entry -> {
					pumd.addProperty(entry.getKey(), (String) entry.getValue());
				});
			}
		});
		return new JDOPersistenceManagerFactory(pumd, null);
	}

	/**
	 * Spring proxy bean for JDO {@link PersistenceManager}
	 * 
	 * @return
	 */
	@Bean
	public TransactionAwarePersistenceManagerFactoryProxy persistenceManagerProxyBean() {
		TransactionAwarePersistenceManagerFactoryProxy proxyBean = new TransactionAwarePersistenceManagerFactoryProxy();
		proxyBean.setTargetPersistenceManagerFactory(persistenceManagerFactory());
		return proxyBean;
	}
}
