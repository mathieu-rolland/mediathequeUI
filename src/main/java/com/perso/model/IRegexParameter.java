package com.perso.model;

public interface IRegexParameter {

	public String getRegex();
	public void setRegex(String regex);
	public String getReplace();
	public void setReplace(String replace);
	public String applyRegex(String input);
}
