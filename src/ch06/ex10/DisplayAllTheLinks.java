/**
 * Copyright 2017 Ken Mirua
 */
package ch06.ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;

public final class DisplayAllTheLinks {

	/**
	 * 指定されたURL上のウェブページにあるリンクをすべて表示する。
	 */
	public static void main(String[] args) {

		CompletableFuture.supplyAsync(DisplayAllTheLinks::getURL)
							.thenApply(DisplayAllTheLinks::blockingReadPage)
							.thenApply(Parser::getLinks)
							.thenAccept(System.out::println);
		ForkJoinPool.commonPool().awaitQuiescence(20, TimeUnit.SECONDS);
	}

	private static URL getURL () {
		System.out.print("URLを入力してください: ");
		try (	InputStreamReader inputStreamReader = new InputStreamReader(System.in);
				BufferedReader bufferredReader = new BufferedReader(inputStreamReader)) {
			return new URL(bufferredReader.readLine());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String blockingReadPage (URL url) {
		try (	InputStream in = url.openStream();
				InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
				BufferedReader bufferredReader = new BufferedReader(inputStreamReader)) {
			final StringBuilder builder = new StringBuilder();
			String line = null;
	        while ((line = bufferredReader.readLine()) != null) {
	            builder.append(line);
	            builder.append(System.getProperty("line.separator"));
	        }
	        return builder.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static class Parser extends ParserCallback {
		
		private final List<String> links = new ArrayList<>();
		
		@Override
		public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {
		    if (tag.equals(HTML.Tag.A)) {
		        links.add((String)attributes.getAttribute(HTML.Attribute.HREF));
		    }
		    super.handleStartTag(tag, attributes, pos);
		}
		
		static List<String> getLinks (String webPage) {
			Parser callback = new Parser();
	        ParserDelegator delegator = new ParserDelegator();
	        try {
	            delegator.parse(new StringReader(webPage), callback, true);
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	        return callback.links;
		}
	}
}
