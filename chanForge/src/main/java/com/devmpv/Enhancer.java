package com.devmpv;

import com.devmpv.config.ChanConfig;

/**
 * Datanucleus enhancer
 * 
 * @author user1
 *
 */
public class Enhancer {

	public static void main(String[] args) {
		ChanConfig.jdoEnhance();
	}
}
