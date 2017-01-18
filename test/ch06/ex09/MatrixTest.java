/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex09;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MatrixTest {

	@Test(expected=IllegalArgumentException.class)
	public void Matrix_throwsIllegalArgumentExceptionIfValueLessThan1IsPassedAsFirstArg() {
		new Matrix(0, 1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void Matrix_throwsIllegalArgumentExceptionIfValueLessThan1IsPassedAsSecondArg() {
		new Matrix(1, 0);
	}
	
	@Test
	public void Matrix_returns0_0AtAnyCoordinate() {
		Matrix matrix = new Matrix(2, 2);
		for (int i=1; i<=matrix.getNumOfCol(); i++) {
			for (int j=1; j<=matrix.getNumOfRow(); j++) {
				assertThat(matrix.getValue(i, j), is(0.0));
			}	
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setValue_throwsIllegalArgumentExceptionIfValueLessThan1IsPassedAsCol() {
		Matrix matrix = new Matrix(2, 2);
		matrix.setValue(0, 1, 1.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setValue_throwsIllegalArgumentExceptionIfValueMoreThanNumOfColIsPassedAsCol() {
		Matrix matrix = new Matrix(2, 2);
		matrix.setValue(matrix.getNumOfCol()+1, 1, 1.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setValue_throwsIllegalArgumentExceptionIfValueLessThan1IsPassedAsRow() {
		Matrix matrix = new Matrix(2, 2);
		matrix.setValue(1, 0, 1.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setValue_throwsIllegalArgumentExceptionIfValueMoreThanNumOfRowIsPassedAsRow() {
		Matrix matrix = new Matrix(2, 2);
		matrix.setValue(1, matrix.getNumOfRow()+1, 1.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getValue_throwsIllegalArgumentExceptionIfValueLessThan1IsPassedAsCol() {
		Matrix matrix = new Matrix(2, 2);
		matrix.getValue(0, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getValue_throwsIllegalArgumentExceptionIfValueMoreThanNumOfColIsPassedAsCol() {
		Matrix matrix = new Matrix(2, 2);
		matrix.getValue(matrix.getNumOfCol()+1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getValue_throwsIllegalArgumentExceptionIfValueLessThan1IsPassedAsRow() {
		Matrix matrix = new Matrix(2, 2);
		matrix.getValue(1, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getValue_throwsIllegalArgumentExceptionIfValueMoreThanNumOfRowIsPassedAsRow() {
		Matrix matrix = new Matrix(2, 2);
		matrix.getValue(1, matrix.getNumOfRow()+1);
	}
	
	@Test
	public void getValue_returnsValueSetBySetValueCase1() {
		Matrix matrix = new Matrix(2, 2);
		double expected = 3.14;
		matrix.setValue(1, 1, expected);
		
		double actual = matrix.getValue(1, 1);
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void getValue_returnsValueSetBySetValueCase2() {
		Matrix matrix = new Matrix(2, 2);
		double expected = -1.0;
		matrix.setValue(matrix.getNumOfCol(), matrix.getNumOfRow(), expected);
		
		double actual = matrix.getValue(matrix.getNumOfCol(), matrix.getNumOfRow());
		
		assertThat(actual, is(expected));
	}
	
	@Test(expected=NullPointerException.class)
	public void multiply_throwsNullPointerExceptionIfNullIsPassed(){
		new Matrix(2, 2).multiply(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void multiply_throwsIllegalArgumentExceptionIfIllegalSizeMarixIsPassed(){
		Matrix matrix = new Matrix(2, 2);
		Matrix otherMatrix = new Matrix(matrix.getNumOfRow()+1, 2);
		matrix.multiply(otherMatrix);
	}
	
	@Test
	public void multiply_returns(){
		Matrix expected = new Matrix(2, 1);
		expected.setValue(1, 1, 1.0 * 4.5);
		expected.setValue(2, 1, 1.2 * 1.5);
		
		Matrix matrix1 = new Matrix(2, 2);
		matrix1.setValue(1, 1, 1.0);
		matrix1.setValue(1, 2, 0.0);
		matrix1.setValue(2, 1, 0.0);
		matrix1.setValue(2, 2, 1.2);
		
		Matrix matrix2 = new Matrix(2, 1);
		matrix2.setValue(1, 1, 4.5);
		matrix2.setValue(2, 1, 1.5);
		
		Matrix actual = matrix1.multiply(matrix2);
		
		assertThat(actual, is(expected));
	}
}
