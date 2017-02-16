/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex13;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import ch08.ex13.TestCase;

// 動作未確認
public final class TestCaseCreationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (final TypeElement annotation: annotations) {
			final Filer filer = processingEnv.getFiler();
			try (OutputStream outputStream = filer.createSourceFile("ch08.ex13.Test").openOutputStream();
					Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);) {
				writer.write("package ch08.ex13;" + System.lineSeparator());
				writer.write("public final class Test {" + System.lineSeparator());
				writer.write("public static void main (String[] args) throws Exception {" + System.lineSeparator());
				
				for (final Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
					final String method = element.getEnclosingElement() + "." + element.getSimpleName();
					TestCase[] testCases = element.getAnnotationsByType(TestCase.class);
                    for (final TestCase testCase : testCases) {
                    	writer.write("if(" + method + "(" + testCase.params() + ") != " + testCase.expected() + ") {");
                    	writer.write("throw new AssertionError();");
                    	writer.write("}"); // if
                    }
					
				}
				
				writer.write("}" + System.lineSeparator()); // mainメソッド
				writer.write("}" + System.lineSeparator()); // Testクラス
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
