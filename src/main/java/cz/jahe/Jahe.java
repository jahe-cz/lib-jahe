/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility class of static helper methods for intuitive, efficient coding.
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public class Jahe {
	
	public static <T> boolean isEmpty(final Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}
	
	public static <T> boolean notEmpty(final Collection<T> collection) {
		return ! isEmpty(collection);
	}
	
	public static <T> boolean isEmpty(final T[] array) {
		return array == null || array.length == 0;
	}

	public static <T> boolean notEmpty(final T[] array) {
		return ! isEmpty(array);
	}
	
	public static boolean isEmpty(final CharSequence text) {
		return text == null || text.length() == 0;
	}
	
	public static boolean notEmpty(final CharSequence text) {
		return ! isEmpty(text);
	}
	
	public static <T> boolean isEmpty(final Iterable<T> sequence) {
		return sequence == null || ! sequence.iterator().hasNext();
	}

	public static <T> boolean notEmpty(final Iterable<T> sequence) {
		return ! isEmpty(sequence);
	}

	public static <K, V> boolean isEmpty(final Map<K, V> map) {
		return map == null || map.size() == 0;
	}

	public static <K, V> boolean notEmpty(final Map<K, V> map) {
		return ! isEmpty(map);
	}
	
	// Common structure support ...
	
	public static <T> T[] array(final T... items) {
		return items;
	}
	
	public static <T> List<T> list(final T... items) {
		if (items == null || items.length == 0) {
			return new ArrayList<T>();
		}
		return Arrays.asList(items);
	}
	
	public static <T> Set<T> set(final T... items) {
		return new LinkedHashSet<T>(list(items));
	}
	
	public static <K, V> Map<K, V> map() {
		return new LinkedHashMap<K, V>();
	}
	
	// Assert support ...
	
	public static void assertNotNull(final Object value, final String message) throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertTrue(final Boolean value, final String message) throws IllegalArgumentException {
		if (value != Boolean.TRUE) {
			throw new IllegalArgumentException(message);
		}
	}
	public static void assertFalse(final Boolean value, final String message) throws IllegalArgumentException {
		if (value != Boolean.FALSE) {
			throw new IllegalArgumentException(message);
		}
	}
	
}
