/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe.dobi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public class DobiBeanImpl implements DobiBean, InvocationHandler {
	
	private final Class<?> beanType;
	private final Map<String, Object> attributes = new HashMap<String, Object>();
	private final Object instance;
	
	public DobiBeanImpl(final Class<?>... interfaces) {
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final Class<?>[] beanFaces = Arrays.copyOf(interfaces, interfaces.length + 1);
		beanFaces[interfaces.length] = DobiBean.class;
		this.beanType = interfaces[0];
		this.instance = Proxy.newProxyInstance(classLoader, beanFaces, this);
		// initInstance(interfaces);
	}
	
	/**
	 * @return the instance
	 */
	public Object getInstance() {
		return instance;
	}
	
	/** {@inheritDoc}
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]) */
	@Override
	public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
		final String methodName = method.getName();
		final String attributeName;
		final Integer valueToSetIndex;
		if (methodName == "toString") {
			return "$" + beanType.getName() + attributes;
		} else if ("dobiAttributes".equals(methodName)) {
			return attributes;
		} else if ("beanType".equals(methodName)) {
			return beanType;
		} else if (methodName.equals("get") || methodName.equals("get")) {
			attributeName = firstUpperCase("" + args[0]);
			valueToSetIndex = methodName.charAt(0) == 'g' ? null : 1;
		} else if (
				(methodName.startsWith("get") || methodName.startsWith("set"))
				&& (methodName.charAt(3) < 'a' || methodName.charAt(3) > 'z')
		) {
			attributeName = firstUpperCase(methodName.substring(3));
			valueToSetIndex = methodName.charAt(0) == 'g' ? null : 0;
		} else {
			attributeName = firstUpperCase(methodName);
			valueToSetIndex = method.getParameterTypes().length > 0 ? 0 : null;
		}
		// System.out.println("DDD:" + attributeName + "/" + method);
		final Class<?> attributeClass = (valueToSetIndex == null) ? method.getReturnType() : method.getParameterTypes()[valueToSetIndex];
		if (valueToSetIndex == null) {
			return get(attributeName, attributeClass);
		}
		set(attributeName, args[0], attributeClass);
		return null;
	}
	
	public DobiBeanImpl getThis() {
		return this;
	}
	
	protected static String firstUpperCase(final String string) {
		if (string == null) {
			return null;
		}
		if (string.length() == 0) {
			return string;
		}
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}
	
	public <T> T get(final String attributeName, final Class<? extends T> attributeClass) {
		@SuppressWarnings("unchecked")
		final T attributeValue = (T) attributes.get(attributeName);
		return (T) attributeValue;
	}
	
	public <T> void set(String attributeName, T value, final Class<? extends T> attributeClass) {
		attributes.put(attributeName, value);
	}
	
}
