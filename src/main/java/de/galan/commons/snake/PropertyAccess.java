package de.galan.commons.snake;

import static org.apache.commons.lang3.StringUtils.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Methods required by the Snake
 *
 * @author daniel
 */
public interface PropertyAccess {

	public static final String KEY_SNAKE_BASE = "snake.base";
	public static final String KEY_SNAKE_INSTANCE = "snake.instance";

	public static final String KEY_SNAKE_INSTANCE_APPLICATION = "snake.instance.application";
	public static final String KEY_SNAKE_INSTANCE_ARTIFACT = "snake.instance.artifact";
	public static final String KEY_SNAKE_INSTANCE_CONFIGURATION = "snake.instance.configuration";
	public static final String KEY_SNAKE_INSTANCE_LOG = "snake.instance.log";
	public static final String KEY_SNAKE_INSTANCE_SCRIPT = "snake.instance.script";
	public static final String KEY_SNAKE_INSTANCE_STORAGE = "snake.instance.storage";
	public static final String KEY_SNAKE_INSTANCE_TEMP = "snake.instance.temp";


	public SystemModel system();


	public void addListener(SnakeListener listener);


	public void removeListener(SnakeListener listener);


	/**
	 * Returns the property for the given name.
	 *
	 * @param name The name of the property
	 * @return The value of the property, null if not set
	 */
	default String get(String name) {
		return getProperties().get(name);
	}


	default String get(String name, String fallback) {
		String result = get(name);
		return isNotBlank(result) ? result : fallback;
	}


	public Map<String, String> getProperties();


	default Set<String> getProperties(String prefix) {
		return getProperties().keySet().stream().filter(key -> startsWith(key, prefix)).collect(Collectors.toSet());
	}


	/**
	 * Sets the property. When the value is null or blank it will be removed.
	 *
	 * @param name Name of the property
	 * @param value The value of the property
	 */
	public void set(String name, String value);


	default void setObject(String name, Object value) {
		set(name, value == null ? null : value.toString());
	}


	default void remove(String name) {
		set(name, null);
	}


	default String getInstance() {
		return get(KEY_SNAKE_INSTANCE, "instance");
	}


	default String getDirectoryBase() {
		return get(KEY_SNAKE_BASE, system().getUserHome());
	}


	default String getDirectoryInstance() {
		return getDirectoryBase() + system().getFileSeparator() + getInstance() + system().getFileSeparator();
	}


	default String getDirectoryStorage() {
		return getDirectoryInstance() + "storage" + system().getFileSeparator();
	}


	default String getDirectoryLog() {
		return getDirectoryInstance() + "log" + system().getFileSeparator();
	}


	default String getDirectoryConfiguration() {
		return getDirectoryInstance() + "configuration" + system().getFileSeparator();
	}


	default String getDirectoryTemp() {
		return getDirectoryInstance() + "temp" + system().getFileSeparator();
	}


	default String getDirectoryScript() {
		return getDirectoryInstance() + "script" + system().getFileSeparator();
	}

}
