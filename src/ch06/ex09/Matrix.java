/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex09;

import java.util.Arrays;
import java.util.Objects;

public final class Matrix {

	public static void main (String... args) {
		final Matrix F = new Matrix(2, 2);
		F.setValue(1, 1, 1);
		F.setValue(1, 2, 1);
		F.setValue(2, 1, 1);
		F.setValue(2, 2, 0);
		final int size = 100;
		Matrix[] array = new Matrix[size];

		Arrays.parallelSetAll(array, i -> F);
		Arrays.parallelPrefix(array, Matrix::multiply); 
		Arrays.stream(array).forEach(matrix -> System.out.println(matrix.getValue(1, 1)));
	}
	
	private final int numOfCol;
	private final int numOfRow;
	private final double[][] doubleArray;
	
	public Matrix (int numOfCol, int numOfRow) {
		if (numOfCol<1 || numOfRow<1) {
			throw new IllegalArgumentException("numOfCol and numOfRow must be 1 or more");
		}
		this.numOfCol = numOfCol;
		this.numOfRow = numOfRow;
		doubleArray = new double[numOfCol][numOfRow];
	}
	
	public final int getNumOfCol() {
		return numOfCol;
	}

	public final int getNumOfRow() {
		return numOfRow;
	}

	public void setValue(int col, int row, double value) {
		validateColAndRow(col, row);
		doubleArray[col-1][row-1] = value;
	}
	
	public double getValue(int col, int row) {
		validateColAndRow(col, row);
		return doubleArray[col-1][row-1];
	}
	
	private void validateColAndRow(int col, int row) {
		if (col<1 || col>numOfCol) {
			throw new IllegalArgumentException("col is illegal size. col must be 1<=col<=" + numOfCol);
		}
		if (row<1 || row>numOfRow) {
			throw new IllegalArgumentException("row is illegal size. col must be 1<=row<=" + numOfRow);
		}
	}
	
	public Matrix multiply (Matrix otherMatrix) {
		Objects.requireNonNull(otherMatrix, "otherMatrix must not be null");
		if (this.numOfRow != otherMatrix.numOfCol) {
			throw new IllegalArgumentException("cannot multiply. Be sure that this.numOfRow is equal to otherMatrix.numOfCol");
		}
		final Matrix temp = new Matrix(this.numOfCol, otherMatrix.numOfRow);
		for (int i=0; i<this.numOfCol; i++) {
			for (int j=0; j<otherMatrix.numOfRow; j++) {
				double value = 0.0;
				for (int k=0; k<this.numOfRow; k++) {
					value += (this.getValue(i+1, k+1) * otherMatrix.getValue(k+1, j+1));
				}
				temp.setValue(i+1, j+1, value);
			}
		}
		return temp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(doubleArray);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix other = (Matrix) obj;
		if (!Arrays.deepEquals(doubleArray, other.doubleArray))
			return false;
		return true;
	}
}
