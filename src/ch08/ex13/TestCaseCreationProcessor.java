/**
 * Copyright 2017 Ken Mirua
 */
package ch08.ex13;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import ch08.ex13.TestCase;

public final class TestCaseCreationProcessor extends AbstractProcessor {

	/**以下のような内容が出力される。
	 * package ch08.ex13;
	 * public final class Test {
	 * 	public static void main (String[] args) throws Exception {
	 * 		if(ch08.ex13.TestedClass.factorial(4) != 24) {
	 * 			throw new AssertionError();
	 * 		}
	 * 		if(ch08.ex13.TestedClass.factorial(0) != 1) {
	 * 			throw new AssertionError();
	 * 		}
	 * 		System.out.println("All the tests passed.");
	 * 	}
	 * }
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (final TypeElement annotation: annotations) {
			final Filer filer = processingEnv.getFiler();
			try (OutputStream outputStream = filer.createSourceFile("ch08.ex13.Test").openOutputStream();
					Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);) {
				writer.write("package ch08.ex13;" + System.lineSeparator());
				writer.write("public final class Test {" + System.lineSeparator());
				writer.write("\tpublic static void main (String[] args) throws Exception {" + System.lineSeparator());
				
				for (final Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
					final String method = element.getEnclosingElement() + "." + element.getSimpleName();
					TestCase[] testCases = element.getAnnotationsByType(TestCase.class);
                    for (final TestCase testCase : testCases) {
                    	writer.write("\t\tif(" + method + "(" + testCase.params() + ") != " + testCase.expected() + ") {" + System.lineSeparator());
                    	writer.write("\t\t\tthrow new AssertionError();" + System.lineSeparator());
                    	writer.write("\t\t}" + System.lineSeparator()); // if
                    }

				}
				writer.write("\t\tSystem.out.println(\"All the tests passed.\");" + System.lineSeparator()); // mainメソッド
				writer.write("\t}" + System.lineSeparator()); // mainメソッド
				writer.write("}" + System.lineSeparator()); // Testクラス
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportedAnnotationTypes = new HashSet<>();
        supportedAnnotationTypes.add("*");
        return supportedAnnotationTypes;
    }

	
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }
}
