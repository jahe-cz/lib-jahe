/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe.context;

/**
 * 
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public interface JaheContext {
	
	public <T> T get(final String id);
	
	public <T> T get(final String id, final Class<T> type);
	
}
