/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe.dobi;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public class Dobi {
	
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(final Class<T> beanClass) {
		if ( ! beanClass.isInterface()) {
			try {
				return (T) beanClass.getDeclaredConstructor().newInstance();
			} catch (final Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		// FIXME pincr: Hardcoded special interfaces ... 
		if (beanClass.equals(List.class)) {
			return (T) new ArrayList<Object>();
		}
		if (beanClass.equals(Set.class)) {
			return (T) new LinkedHashSet<Object>();
		}
		// Common Dobi Bean ...
		return (T) new DobiBeanImpl(beanClass).getInstance();
	}
	
}
