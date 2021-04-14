package com.github.pireba.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class I18N {
	
	private static final Locale DEFAULT_LOCALE = Locale.US;
	private static final ObjectProperty<Locale> LOCALE = new SimpleObjectProperty<Locale>(getDefaultLocale());
	static {
		LOCALE.addListener((observable, oldValue, newValue) -> {
			Locale.setDefault(newValue);
		});
	}
	
	public static List<Locale> getSupportedLocales() {
		List<Locale> list = new ArrayList<>();
		list.add(Locale.GERMANY);
		list.add(DEFAULT_LOCALE);
		return list;
	}
	
	public static Locale getDefaultLocale() {
		Locale locale = Locale.getDefault();
		
		if ( getSupportedLocales().contains(locale) ) {
			return locale;
		} else {
			return DEFAULT_LOCALE;
		}
	}
	
	public static Locale getLocale() {
		return LOCALE.get();
	}
	
	public static void setLocale(Locale locale) {
		LOCALE.set(locale);
	}
	
	public static ObjectProperty<Locale> localeProperty() {
		return LOCALE;
	}
	
	public static ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("i18n/iTunes-Renamer", getLocale());
	}
	
	public static String get(final String key, final Object... args) {
		return MessageFormat.format(getResourceBundle().getString(key), args);
	}
}