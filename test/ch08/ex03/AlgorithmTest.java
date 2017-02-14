/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex03;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class AlgorithmTest {

	@Test
	public void euclideanAlgorithmUsingPercent_returnsGreatestCommonDivisor() {
		int actual = Algorithm.euclideanAlgorithmUsingPercent(6, 9);
		assertThat(actual, is(3));
	}

	@Test
	public void euclideanAlgorithmUsingPercent_returnsAIfBIs0() {
		int actual = Algorithm.euclideanAlgorithmUsingPercent(2, 0);
		assertThat(actual, is(2));
	}
	
	@Test
	public void euclideanAlgorithmUsingPercent_returnsPositiveNumIfNegativeNumIsPassedAsB() {
		int actual = Algorithm.euclideanAlgorithmUsingPercent(6, -9);
		assertThat(actual, is(3));
	}
	
	@Test
	public void euclideanAlgorithmUsingPercent_returnsPositiveNumIfNegativeNumIsPassedAsA() {
		int actual = Algorithm.euclideanAlgorithmUsingPercent(-6, 9);
		assertThat(actual, is(3));
	}
	
	@Test
	public void euclideanAlgorithmUsingPercent_returnsPositiveNumIfNegativeNumIsPassedAsBothAAndB() {
		int actual = Algorithm.euclideanAlgorithmUsingPercent(-6, -9);
		assertThat(actual, is(3));
	}
	
	@Test
	public void euclideanAlgorithmUsingFloorMod_returnsGreatestCommonDivisor() {
		int actual = Algorithm.euclideanAlgorithmUsingFloorMod(6, 9);
		assertThat(actual, is(3));
	}

	@Test
	public void euclideanAlgorithmUsingFloorMod_returnsAIfBIs0() {
		int actual = Algorithm.euclideanAlgorithmUsingFloorMod(2, 0);
		assertThat(actual, is(2));
	}
	
	@Test
	public void euclideanAlgorithmUsingFloorMod_returnsPositiveNumIfNegativeNumIsPassedAsB() {
		int actual = Algorithm.euclideanAlgorithmUsingFloorMod(6, -9);
		assertThat(actual, is(3));
	}
	
	@Test
	public void euclideanAlgorithmUsingFloorMod_returnsPositiveNumIfNegativeNumIsPassedAsA() {
		int actual = Algorithm.euclideanAlgorithmUsingFloorMod(-6, 9);
		assertThat(actual, is(3));
	}
	
	@Test
	public void euclideanAlgorithmUsingFloorMod_returnsPositiveNumIfNegativeNumIsPassedAsBothAAndB() {
		int actual = Algorithm.euclideanAlgorithmUsingFloorMod(-6, -9);
		assertThat(actual, is(3));
	}
	
	@Test
	public void euclideanAlgorithmUsingRem_returnsGreatestCommonDivisor() {
		int actual = Algorithm.euclideanAlgorithmUsingRem(6, 9);
		assertThat(actual, is(3));
	}

	@Test
	public void euclideanAlgorithmUsingRem_returnsAIfBIs0() {
		int actual = Algorithm.euclideanAlgorithmUsingRem(2, 0);
		assertThat(actual, is(2));
	}
	
	@Test
	public void euclideanAlgorithmUsingRem_returnsPositiveNumIfNegativeNumIsPassedAsB() {
		int actual = Algorithm.euclideanAlgorithmUsingRem(6, -9);
		assertThat(actual, is(3));
	}
	
	@Test
	public void euclideanAlgorithmUsingRem_returnsPositiveNumIfNegativeNumIsPassedAsA() {
		int actual = Algorithm.euclideanAlgorithmUsingRem(-6, 9);
		assertThat(actual, is(3));
	}
	
	@Test
	public void euclideanAlgorithmUsingRem_returnsPositiveNumIfNegativeNumIsPassedAsBothAAndB() {
		int actual = Algorithm.euclideanAlgorithmUsingRem(-6, -9);
		assertThat(actual, is(3));
	}
}
