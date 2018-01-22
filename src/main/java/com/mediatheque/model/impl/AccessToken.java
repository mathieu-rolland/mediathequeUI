package com.mediatheque.model.impl;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AccessToken {

	@Id
	@GeneratedValue
	private Long id;
	
	private String token;
	private boolean activated;
	
	@ManyToOne
	private User user;
	
	private Date expirationDate;
	
	protected AccessToken(){
		
	}
	
	public AccessToken( User user, String token ){
		this.user = user;
		this.token = token;
	}
	
	public AccessToken( User user, String token , Date expirationDate ){
		this.user = user;
		this.token = token;
		this.expirationDate = expirationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public boolean isExpired()
    {
        if (null == this.expirationDate) {
            return false;
        }

        return this.expirationDate.getTime() < System.currentTimeMillis();
    }

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean isActivated) {
		this.activated = isActivated;
	}

	public boolean isValid() {
		return !isExpired() && isActivated();
	}
	
}
