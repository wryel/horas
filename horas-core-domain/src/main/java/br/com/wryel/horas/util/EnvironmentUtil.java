package br.com.wryel.horas.util;

public final class EnvironmentUtil {
	
	public static final String ENVIRONMENT_KEY = "environment.name";
	
	public static final String DEVELOPMENT = "DEV";
	
	public static final String PRODUCTION = "PRODUC";
	
	public static final String CURRENT_ENVIRONMENT = System.getProperty(ENVIRONMENT_KEY);
	
	public static boolean isDevelopment() {
		return DEVELOPMENT.equals(CURRENT_ENVIRONMENT);
	}
	
	public static boolean isProduction() {
		return PRODUCTION.equals(CURRENT_ENVIRONMENT);
	}
	
	private EnvironmentUtil() {
		
	}
}
