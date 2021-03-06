package util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * Wrapper for ResourceBundle used for translations
 * @author Nekuin
 *
 */
public enum Strings {
	
	INSTANCE;
	private ResourceBundle bundle;
	
	/**
	 * Private constructor, Singleton
	 */
	private Strings() {
	}
	
	/**
	 * Changes (or sets, if called for the first time) the default Locale and loads the correct ResourceBundle.
	 * @param locale instance of Locale
	 */
	public void changeBundle(Locale locale) {
		Locale.setDefault(locale);
		bundle = ResourceBundle.getBundle("Strings");
	}
	
	/**
	 * Returns a string for a given key from the loaded ResourceBundle.
	 * @param key the key for the desired string
	 * @return the string for the given key
	 */
	public String getString(String key) {
		try {
			String string = bundle.getString(key);
			return string;
		} catch (MissingResourceException e) {
			System.out.println("[Strings] Missing resources for the key: " + key);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get the loaded resource bundle
	 * @return ResourceBundle loaded bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}
	
	/**
	 * Returns an instance of Strings
	 * @return instance of Strings
	 */
	public static Strings getInstance() {
		return INSTANCE;
	}
}
