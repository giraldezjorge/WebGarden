package jgiraldez.j2ee.webgarden.util.configuration;


import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;

import jgiraldez.j2ee.webgarden.util.exceptions.
	MissingConfigurationParameterException;

/**
 * @author jorge
 *
 */
@SuppressWarnings("unchecked")
public class ConfigurationParametersManager {
	
	private static final String JNDI_PREFIX = "java:comp/env";
	private static final String CONFIGURATION_FILE = 
		"ConfigurationParameters.properties";
	
	private static boolean usesJNDI;
	private static Map parameters;
	
	static {
		
		try {
			/* read property file (if exists)*/
			Class configurationParametersManagerClass = 
				ConfigurationParametersManager.class;
			ClassLoader classLoader = 
				configurationParametersManagerClass.getClassLoader();
			InputStream inputStream = 
				classLoader.getResourceAsStream(CONFIGURATION_FILE);
			Properties properties = new Properties();
			properties.load(inputStream);
			inputStream.close();
			
			/* We have been able to read the file. */
			usesJNDI = false;
			System.out.println(
					"*** Using 'ConfigurationParameters.properties' " +
					"file for configuration ***");
			
			/*
			 * We use a "HashMap" instead of a "HashTable" because
			 * HashMap's method are *not* synchronized (so they are
			 * faster), and the parameters are only read.
			 */
			parameters = new HashMap(properties);
			
		} catch (Exception e) {
			
			/* We assume configuration with JNDI. */
			usesJNDI = true;
			System.out.println("*** Using JNDI for configuration ***");
			
			/*
			 * We use a synchronized map because it will be filled
			 * by using a lazy strategy.
			 */
			parameters = Collections.synchronizedMap(new HashMap());
		}
	}// static
	
	private ConfigurationParametersManager() {}
	
	public static String getParameter(String name) 
			throws MissingConfigurationParameterException {
		String value = (String) parameters.get(name);
		if (value == null) {
			if (usesJNDI) {
				try {
					InitialContext initialContext = new InitialContext();
					value = (String) initialContext.lookup(JNDI_PREFIX + name);
					parameters.put(name, value);
				} catch (Exception e) {
					throw new MissingConfigurationParameterException(name);
				}
			} else {
				throw new MissingConfigurationParameterException(name);
			}
		}
		return value;
	}
}// class
