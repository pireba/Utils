package com.github.pireba.utils;

import java.io.IOException;
import java.net.URL;
import java.util.logging.LogManager;

/**
 * A collection of utility functions.
 * 
 * @author Phillip Remmert
 */
public class Utils {
	
	private Utils() {}
	
	/**
	 * Returns the first non-null value in a list of values.
	 * 
	 * @param values The list of values.
	 * @return The first non-null value.
	 */
	@SafeVarargs
	public static <T> T coalesce(final T... values) {
		if ( values != null ) {
			for ( final T value : values ) {
				if ( value != null ) {
					return value;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the name of the operating system.
	 * 
	 * @return The name of the operating system.
	 */
	public static String getOperatingSystem() {
		return System.getProperty("os.name");
	}
	
	/**
	 * Returns a String of the users home directory.
	 * 
	 * @return The String of the users home directory.
	 */
	public static String getUserHome() {
		return System.getProperty("user.home");
	}
	
	/**
	 * Returns true if the operating system is Linux.
	 * 
	 * @return True if the operating system is Linux. Otherwise false.
	 */
	public static boolean isLinux() {
		return Utils.getOperatingSystem().toLowerCase().contains("linux");
	}
	
	/**
	 * Returns true if the operating system is MacOS.
	 * 
	 * @return True if the operating system is MacOS. Otherwise false.
	 */
	public static boolean isMac() {
		return Utils.getOperatingSystem().toLowerCase().contains("mac") || Utils.getOperatingSystem().toLowerCase().contains("darwin");
	}
	
	/**
	 * Returns true if the operating system is Windows.
	 * 
	 * @return True if the operating system is Windows. Otherwise false.
	 */
	public static boolean isWindows() {
		return Utils.getOperatingSystem().toLowerCase().contains("win");
	}
	
	/**
	 * Load "/logging.properties" file into the LogManager.
	 * The file must be exist in the root of the classpath.
	 * 
	 * @throws IOException If the file could not be found.
	 */
	public static void loadLoggingProperties() throws IOException {
		loadLoggingProperties("/logging.properties");
	}
	
	/**
	 * Load a properties file into the LogManager.
	 * 
	 * @param resource The path to the properties file.
	 * @throws IOException If the file could not be found.
	 */
	public static void loadLoggingProperties(String resource) throws IOException {
		if ( System.getProperty("java.util.logging.config.file") != null ) {
			return;
		}
		
		URL url = Utils.class.getResource(resource);
		
		if ( url == null ) {
			throw new IOException("The file could not be found.");
		}
		
		LogManager.getLogManager().readConfiguration(url.openStream());
	}
	
	/**
	 * Converts a String to Start Case.
	 * 
	 * @param string The String to be converted.
	 * @return The String converted to Start Case.
	 */
	public static String toStartCase(String string) {
		char[] buffer = string.toCharArray();
		
		boolean convert = false;
		for ( int i=0; i<buffer.length; i++ ) {
			if ( buffer[i] == ' ' ) {
				convert = true;
				continue;
			}
			
			if ( i == 0 || convert == true ) {
				buffer[i] = Character.toUpperCase(buffer[i]);
				convert = false;
			} else {
				buffer[i] = Character.toLowerCase(buffer[i]);
			}
		}
		
		return new String(buffer);
	}
}