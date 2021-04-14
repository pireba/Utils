package com.github.pireba.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class I18N {
	
	private static final Locale DEFAULT_LOCALE = Locale.US;
	
	private final ResourceBundle bundle;
	private final ObjectProperty<Locale> locale = new SimpleObjectProperty<>(DEFAULT_LOCALE);
	
	public I18N(final String bundle) {
		this(ResourceBundle.getBundle(bundle));
	}
	
	public I18N(final String bundle, final Locale locale) {
		this(ResourceBundle.getBundle(bundle, locale));
	}
	
	public I18N(final ResourceBundle bundle) {
		this.bundle = bundle;
		this.locale.addListener((observable, oldValue, newValue) -> {
			Locale.setDefault(newValue);
		});
	}
	
	public Locale getDefaultLocale() {
		return DEFAULT_LOCALE;
	}
	
	public Locale getLocale() {
		return this.locale.get();
	}
	
	public void setLocale(Locale locale) {
		this.locale.set(locale);
	}
	
	public ObjectProperty<Locale> localeProperty() {
		return this.locale;
	}
	
	public String get(final String key, final Object... args) {
		return this.getMessage(key, args);
	}
	
	public String getMessage(final String key, final Object... args) {
		return MessageFormat.format(this.bundle.getString(key), args);
	}
	
	public ResourceBundle getResourceBundle() {
		return this.bundle;
	}
}