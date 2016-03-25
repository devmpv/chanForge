package com.devmpv.config;

import javax.jdo.PersistenceManagerFactory;

import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;
import org.datanucleus.metadata.PersistenceUnitMetaData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devmpv.model.OPost;
import com.devmpv.model.Post;

@Configuration
public class ChanConfig {

	@Value("${javax.jdo.ConnectionDriverName}")
	private String driver;
	@Value("${javax.jdo.ConnectionURL}")
	private String url;
	@Value("${javax.jdo.ConnectionUserName}")
	private String user;
	@Value("${javax.jdo.ConnectionPassword}")
	private String password;

	/**
	 * Configuration of DataNucleus {@link PersistenceManagerFactory}
	 * 
	 * @return {@link PersistenceManagerFactory}
	 */
	@Bean
	public PersistenceManagerFactory persistenceManagerFactory() {
		PersistenceUnitMetaData pumd = new PersistenceUnitMetaData("ChanForge", "RESOURCE_LOCAL", null);
		pumd.addClassName(Post.class.getName());
		pumd.addClassName(OPost.class.getName());
		pumd.setExcludeUnlistedClasses();
		pumd.addProperty("javax.jdo.ConnectionDriverName", driver);
		pumd.addProperty("javax.jdo.ConnectionURL", url);
		pumd.addProperty("javax.jdo.ConnectionUserName", user);
		pumd.addProperty("javax.jdo.ConnectionPassword", password);
		pumd.addProperty("datanucleus.autoCreateSchema", "true");
		return new JDOPersistenceManagerFactory(pumd, null);
	}
}
