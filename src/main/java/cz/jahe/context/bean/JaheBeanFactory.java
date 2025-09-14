/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe.context.bean;

/**
 * 
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public interface JaheBeanFactory {
	
	public boolean isSupported(final Class<?> type);
	
	public Object newBean(final Class<?> type);
	
}
