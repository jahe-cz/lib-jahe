/** Software license: { GNU / GPL Version 3 } - http://www.gnu.org/licenses/gpl-3.0.html */
package cz.jahe;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * {@link Jahe} unit tests.
 * 
 * @author Radek Pinc (pincr) {@literal <radek.pinc@softinel.cz>}
 */
public class JaheTest extends JaheTestSupport {
	
	@Test
	public void testIsEmptyNull() {
		assertTrue(Jahe.isEmpty((CharSequence) null));
		assertTrue(Jahe.isEmpty((Collection<?>) null));
		assertTrue(Jahe.isEmpty((Iterable<?>) null));
		assertTrue(Jahe.isEmpty((Map<?,?>) null));
		assertTrue(Jahe.isEmpty((Object[]) null));
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(Jahe.isEmpty(""));
		assertTrue(Jahe.isEmpty(Collections.emptyList()));
		assertTrue(Jahe.isEmpty((Iterable<?>) Collections.emptySet()));
		assertTrue(Jahe.isEmpty(Collections.emptyMap()));
		assertTrue(Jahe.isEmpty(Jahe.array()));
	}
	
	@Test
	public void testIsEmptyFalse() {
		assertFalse(Jahe.isEmpty(" "));
		assertFalse(Jahe.isEmpty(Jahe.list("")));
		assertFalse(Jahe.isEmpty((Iterable<?>) Jahe.set("")));
		assertFalse(Jahe.isEmpty(Jahe.map(1,"")));
		assertFalse(Jahe.isEmpty(Jahe.array("")));
	}
	
	@Test
	public void testNotEmptyNull() {
		assertFalse(Jahe.notEmpty((CharSequence) null));
		assertFalse(Jahe.notEmpty((Collection<?>) null));
		assertFalse(Jahe.notEmpty((Iterable<?>) null));
		assertFalse(Jahe.notEmpty((Map<?,?>) null));
		assertFalse(Jahe.notEmpty((Object[]) null));
	}
	
	@Test
	public void testNotEmpty() {
		assertFalse(Jahe.notEmpty(""));
		assertFalse(Jahe.notEmpty(Collections.emptyList()));
		assertFalse(Jahe.notEmpty((Iterable<?>) Collections.emptySet()));
		assertFalse(Jahe.notEmpty(Collections.emptyMap()));
		assertFalse(Jahe.notEmpty(Jahe.array()));
	}
	
	@Test
	public void testNotEmptyFalse() {
		assertTrue(Jahe.notEmpty(" "));
		assertTrue(Jahe.notEmpty(Jahe.list("")));
		assertTrue(Jahe.notEmpty((Iterable<?>) Jahe.set("")));
		assertTrue(Jahe.notEmpty(Jahe.map(1,"")));
		assertTrue(Jahe.notEmpty(Jahe.array("")));
	}
	
}
