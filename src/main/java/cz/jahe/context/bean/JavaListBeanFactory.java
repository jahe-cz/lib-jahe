/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe.context.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link JaheBeanFactory} for {@link List} implemented by {@link ArrayList}.
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public class JavaListBeanFactory implements JaheBeanFactory {

	/** {@inheritDoc}
	 * @see cz.jahe.context.bean.JaheBeanFactory#isSupported(java.lang.Class) */
	@Override
	public boolean isSupported(final Class<?> type) {
		return List.class.equals(type);
	}

	/** {@inheritDoc}
	 * @see cz.jahe.context.bean.JaheBeanFactory#newBean(java.lang.Class) */
	@Override
	public Object newBean(final Class<?> type) {
		return new ArrayList<Object>();
	}
	
}
