package ch02.ex05;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RundomNumberStreamGeneratorTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIf0IsPassedAsModulus() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter m: 0, m must be (m>0)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(1, 1, 0, 0);
	}
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfNegativeValueIsPassedAsModulus() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter m: -1, m must be (m>0)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(1, 1, -1, 0);
	}
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIf0IsPassedAsMultiplier() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter a: 0, a must be (0<a && a<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(0, 3, 5, 0);
	}
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfNegativeValueIsPassedAsMultiplier() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter a: -1, a must be (0<a && a<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(-1, 3, 5, 0);
	}
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfMultiplierEqualsModulus() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter a: 5, a must be (0<a && a<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(5, 3, 5, 0);
	}

	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfMultiplierIsLargerThanModulus() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter a: 6, a must be (0<a && a<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(6, 3, 5, 0);
	}
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfNegativeValueIsPassedAsIncrement() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter c: -1, c must be (0<=c && c<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(3, -1, 5, 0);
	}
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfIncrementEqualsModulus() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter c: 5, c must be (0<=c && c<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(3, 5, 5, 0);
	}	
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfIncrementIsLargerThanModulus() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter c: 6, c must be (0<=c && c<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(3, 6, 5, 0);
	}
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfNegativeValueIsPassedAsSeed() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter seed: -1, seed must be (0<=seed && seed<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(3, 3, 5, -1);
	}
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfSeedEqualsModulus() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter seed: 5, seed must be (0<=seed && seed<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(3, 3, 5, 5);
	}	
	
	@Test
	public void linearCongruentialStream_throwsIllegalArgumentExceptionIfSeedIsLargerThanModulus() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal parameter seed: 6, seed must be (0<=seed && seed<m)");
		
		RundomNumberStreamGenerator.linearCongruentialStream(3, 3, 5, 6);
	}
}
