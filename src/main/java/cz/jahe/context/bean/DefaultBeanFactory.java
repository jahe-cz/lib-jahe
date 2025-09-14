/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe.context.bean;

import cz.jahe.Jahe;

/**
 * Default generic bean factory.
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public class DefaultBeanFactory implements JaheBeanFactory {
	
	public static JaheBeanFactory[] JAVA_COLLECTIONS = Jahe.array(
			new JavaListBeanFactory(),
			new JavaSetBeanFactory()
	);
	
	
	/** {@inheritDoc}
	 * @see cz.jahe.context.bean.JaheBeanFactory#isSupported(java.lang.Class) */
	@Override
	public boolean isSupported(final Class<?> type) {
		return true;
	}
	
	/** {@inheritDoc}
	 * @see cz.jahe.context.bean.JaheBeanFactory#newBean(java.lang.Class) */
	@Override
	public Object newBean(final Class<?> type) {
		Jahe.assertNotNull(type, "Required argument: type");
		Jahe.assertFalse(type.isInterface(), "Interface is not supported: " + type);
		try {
			return type.getDeclaredConstructor().newInstance();
		} catch (final Exception e) {
			throw new RuntimeException("Factory newBean fails: " + e.getMessage(), e);
		}
	}
	
}
