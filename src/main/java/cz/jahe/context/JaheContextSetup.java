/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe.context;

import cz.jahe.context.bean.JaheBeanFactory;

/**
 * 
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public interface JaheContextSetup {
	
	public void addFactory(final JaheBeanFactory... factories);
	
}
