package com.perso.model.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.perso.model.IRegexParameter;

public class RegexParameter implements IRegexParameter{

	private String regex;
	private String replace;
	
	private Pattern pattern;
	
	public String getRegex() {
		return regex;
	}
	
	public String getReplace() {
		return replace;
	}
	
	public void setRegex(String regex) {
		pattern = Pattern.compile(regex);
		this.regex = regex;
	}
	
	public void setReplace(String replace) {
		this.replace = replace;
	}
	
	@Override
	public String applyRegex(String input) {
		if( input != null ){
			Matcher m = pattern.matcher(input);
			if( m.find()) {

//				System.out.println( this.regex + "/" + this.replace + "/" + input + " => " + m.replaceAll(replace));
				return m.replaceAll(replace);
				
			}else{
				System.err.println(this.regex + "no match to ");
			}
		}else{
			System.err.println("Input is null");
		}
		return input;
	}

}
