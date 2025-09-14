/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe.context;

import java.util.List;
import java.util.Map;

import cz.jahe.Jahe;
import cz.jahe.context.bean.DefaultBeanFactory;
import cz.jahe.context.bean.JaheBeanFactory;

/**
 * 
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public class JaheContextImpl implements JaheContext, JaheContextSetup {
	
	private Map<String, Object> beans = Jahe.map();
	
	/** {@inheritDoc}
	 * @see cz.jahe.context.JaheContext#get(java.lang.String) */
	@Override
	public <T> T get(final String id) {
		return (T) beans.get(id);
	}
	
	/** {@inheritDoc}
	 * @see cz.jahe.context.JaheContext#get(java.lang.String, java.lang.Class) */
	@Override
	public <T> T get(final String id, final Class<T> type) {
		return get(id);
	}
	
	// Bean factory ...
	
	private final JaheBeanFactory defaultBeanFactory = new DefaultBeanFactory();
	private final List<JaheBeanFactory> beanFactories = Jahe.list();
	private final Map<Class<?>, JaheBeanFactory> factoryCache = Jahe.map();
	
	protected JaheBeanFactory getFactory(final Class<?> type) {
		final JaheBeanFactory factory;
		if (factoryCache.containsKey(type)) {
			factory = (JaheBeanFactory) factoryCache.get(type);
		} else {
			synchronized (factoryCache) {
				if (factoryCache.containsKey(type)) {
					factory = (JaheBeanFactory) factoryCache.get(type);
				} else {
					factory = resolveFactory(type);
					factoryCache.put(type, factory);
				}
			}
		}
		return factory == null ? defaultBeanFactory : factory;
	}
	
	protected JaheBeanFactory resolveFactory(final Class<?> type) {
		for (final JaheBeanFactory factory : beanFactories) {
			if (factory.isSupported(type)) {
				return factory;
			}
		}
		return null;
	}
	
	/** {@inheritDoc}
	 * @see cz.jahe.context.JaheContextSetup#addFactory(cz.jahe.context.bean.JaheBeanFactory[]) */
	@Override
	public void addFactory(final JaheBeanFactory... factories) {
		synchronized (factoryCache) {
			if (factories != null) {
				for (final JaheBeanFactory factory : factories) {
					beanFactories.add(factory);
				}
			}
			factoryCache.clear();
		}
	}
	
}
