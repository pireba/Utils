package com.github.pireba.utils;

import java.io.IOException;
import java.net.URL;
import java.util.function.UnaryOperator;
import java.util.logging.LogManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class Utils {
	
	private Utils() {}
	
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
	
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}
	
	public static String getOperatingSystem() {
		return System.getProperty("os.name");
	}
	
	public static String getUserHome() {
		return System.getProperty("user.home");
	}
	
	public static void initializeIntegerSpinner(Spinner<Integer> spinner) {
		initializeIntegerSpinner(spinner, 0, Integer.MAX_VALUE, 0);
	}
	
	public static void initializeIntegerSpinner(Spinner<Integer> spinner, int min) {
		initializeIntegerSpinner(spinner, min, Integer.MAX_VALUE, min);
	}
	
	public static void initializeIntegerSpinner(Spinner<Integer> spinner, int min, int max, int initialValue) {
		IntegerSpinnerValueFactory factory = new IntegerSpinnerValueFactory(min, max, initialValue);
		spinner.setValueFactory(factory);
		
		UnaryOperator<TextFormatter.Change> filter = (change) -> {
			try {
				Integer.parseInt(change.getControlNewText());
				return change;
			} catch ( NumberFormatException e ) {
				return null;
			}
		};
		TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), initialValue, filter);
		spinner.getEditor().setTextFormatter(formatter);
	}
	
	public static boolean isLinux() {
		return Utils.getOperatingSystem().toLowerCase().contains("linux");
	}
	
	public static boolean isMac() {
		return Utils.getOperatingSystem().toLowerCase().contains("mac") || Utils.getOperatingSystem().toLowerCase().contains("darwin");
	}
	
	public static boolean isWindows() {
		return Utils.getOperatingSystem().toLowerCase().contains("win");
	}
	
	public static <T> T loadFXML(final Object controller) throws IOException {
		Class<?> clazz = controller.getClass();
		URL url = clazz.getResource(clazz.getSimpleName()+".fxml");
		String path = url.getPath();
		return Utils.loadFXML(path, controller);
	}
	
	public static <T> T loadFXML(final String path, final Object controller) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setController(controller);
		loader.setLocation(Utils.class.getResource(path));
		loader.setResources(I18N.getResourceBundle());
		
		return loader.load();
	}
	
	public static <T> T loadFXMLAsRoot(final String path, final Object controller) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setController(controller);
		loader.setLocation(Utils.class.getResource(path));
		loader.setResources(I18N.getResourceBundle());
		loader.setRoot(controller);
		
		return loader.load();
	}
	
	public static void loadLoggingProperties() throws SecurityException, IOException {
		if ( System.getProperty("java.util.logging.config.file") == null ) {
			LogManager.getLogManager().readConfiguration(Utils.class.getResource("/logging.properties").openStream());
		}
	}
	
	public static String toTitleCase(String currentName) {
		char[] buffer = currentName.toCharArray();
		
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