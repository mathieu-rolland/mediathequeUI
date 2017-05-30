package com.perso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mediatheque")
public class CustomApplicationProperties {
	
	private String downloadPath;
	private String mailSettings;
	
	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getMailSettings() {
		return mailSettings;
	}

	public void setMailSettings(String mailSettings) {
		this.mailSettings = mailSettings;
	}
	
}
