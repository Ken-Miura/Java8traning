/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * 新しくクラスを設計する際、多くのJavaFXプロパティを持つだろうと考えられるクラスの例→メディアプレーヤークラス、ブラウザクラス
 * 要求に応じてプロパティを生成するクラスとしてGreetnigクラスを示す。
 */
// Applicationスレッドからのみのアクセスなので排他処理必要なし
public final class Greeting {

	private String text = "";
	private StringProperty textProperty = null;
	
	public final StringProperty textProperty() {
		if (textProperty == null) {
			textProperty = new SimpleStringProperty(text);
		}
		return textProperty;
	}
	
	public final void setText(String newValue){
		if (textProperty==null) {
			text = newValue;
		} else {
			textProperty.set(newValue);	
		}
	}
	
	public final String getText(){
		if (textProperty==null) {
			return text;
		} else {
			return textProperty.get();	
		}
	}
}
