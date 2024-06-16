package com.github.pireba.utils;

import java.util.logging.Level;

public class Logger {
	
	private Logger() {
		
	}
	
	public static void severe(String message) {
		log(Level.SEVERE, message, null);
	}
	
	public static void severe(String message, Throwable thrown) {
		log(Level.SEVERE, message, thrown);
	}
	
	public static void warning(String message) {
		log(Level.WARNING, message, null);
	}
	
	public static void warning(String message, Throwable thrown) {
		log(Level.WARNING, message, thrown);
	}
	
	public static void info(String message) {
		log(Level.INFO, message, null);
	}
	
	public static void info(String message, Throwable thrown) {
		log(Level.INFO, message, thrown);
	}
	
	public static void config(String message) {
		log(Level.CONFIG, message, null);
	}
	
	public static void config(String message, Throwable thrown) {
		log(Level.CONFIG, message, thrown);
	}
	
	public static void fine(String message) {
		log(Level.FINE, message, null);
	}
	
	public static void fine(String message, Throwable thrown) {
		log(Level.FINE, message, thrown);
	}
	
	public static void finer(String message) {
		log(Level.FINER, message, null);
	}
	
	public static void finer(String message, Throwable thrown) {
		log(Level.FINER, message, thrown);
	}
	
	public static void finest(String message) {
		log(Level.FINEST, message, null);
	}
	
	public static void finest(String message, Throwable thrown) {
		log(Level.FINEST, message, thrown);
	}
	
	private static java.util.logging.Logger getLogger() {
		String name = getName();
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger(name);
		return logger;
	}
	
	private static String getName() {
		StackTraceElement element = getStackTraceElement();
		
		if ( element == null ) {
			return Logger.class.getName();
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(element.getClassName());
		sb.append(".");
		sb.append(element.getMethodName());
		
		return sb.toString();
	}
	
	private static StackTraceElement getStackTraceElement() {
		int size = Thread.currentThread().getStackTrace().length;
		
		for ( int i=1; i<size; i++ ) {
			StackTraceElement element = Thread.currentThread().getStackTrace()[i];
			
			if ( ! element.getClassName().equals(Logger.class.getName()) ) {
				return element;
			}
		}
		
		return null;
	}
	
	private static void log(Level level, String message, Throwable thrown) {
		java.util.logging.Logger logger = getLogger();
		
		if ( logger.isLoggable(level) ) {
			logger.log(level, message, thrown);
		}
	}
}