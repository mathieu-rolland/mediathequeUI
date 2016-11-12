package com.perso.model;

public interface IMachine {

	public String getIp();
	public String getUser();
	public String getPassword();
	public void setUser(String user);
	public void setPassword(String password);
	public void setIp(String ip);
	public String getName();
	public void setName(String name);
	public int getPort();
	public void setPort(int port);
}
