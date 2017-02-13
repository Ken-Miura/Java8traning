/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex09;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class ScannerUtility {

	private ScannerUtility() {
		throw new AssertionError("cannot instanciate");
	}
	
	public static Stream<String> words(Scanner scanner) {
		Objects.requireNonNull(scanner, ()->"scanner must not be null");
		Iterator<String> iter = new Iterator<String>() {
            @Override
            public boolean hasNext() {
            	return scanner.hasNext();
            }

            @Override
            public String next() {
            	return scanner.next();
            }
		};
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
            iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}
	
	public static Stream<String> lines(Scanner scanner) {
		Objects.requireNonNull(scanner, ()->"scanner must not be null");
		Iterator<String> iter = new Iterator<String>() {
	            @Override
	            public boolean hasNext() {
	            	return scanner.hasNext();
	            }

	            @Override
	            public String next() {
	            	return scanner.nextLine();
	            }
	    };
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}

	public static Stream<Integer> integers(Scanner scanner) {
		Objects.requireNonNull(scanner, ()->"scanner must not be null");
		Iterator<Integer> iter = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
            	return scanner.hasNextInt();
            }

            @Override
            public Integer next() {
            	return scanner.nextInt();
            }
		};
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
            iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}
	
	public static Stream<Double> doubles(Scanner scanner) {
		Objects.requireNonNull(scanner, ()->"scanner must not be null");
		Iterator<Double> iter = new Iterator<Double>() {
            @Override
            public boolean hasNext() {
            	return scanner.hasNextDouble();
            }

            @Override
            public Double next() {
            	return scanner.nextDouble();
            }
		};
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
            iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}

}
