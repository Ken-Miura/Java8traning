/**
 * Copyright 2016 Ken Mirua
 */
package ch04.ex03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * 新しくクラスを設計する際、多くのJavaFXプロパティを持つが、ほとんどデフォルト値から変化しないと考えられるクラスの例→
 * デフォルトではない値に設定されたときと要求されたときにプロパティを生成するクラスとしてGreetnigクラスを示す。
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
			textProperty();
		}
		textProperty.set(newValue);	
	}
	
	public final String getText(){
		if (textProperty==null) {
			return text;
		} else {
			return textProperty.get();	
		}
	}
}
