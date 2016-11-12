package com.perso.model.impl;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.api.allocine.model.IMovie;
import com.perso.model.IMachine;

@Entity
public class Machine implements IMachine {

	@Id
	private String ip;
	private String name;
	private String user;
	private String password;
	private int port;
	
	@OneToMany(targetEntity=Movie.class, mappedBy="machine", fetch=FetchType.EAGER, cascade = CascadeType.ALL )
	private Collection<IMovie> movies;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Machine [ip=" + ip + ", name=" + name + ", user=" + user + ", password=" + password + ", movies="
				+ movies + "]";
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
}
