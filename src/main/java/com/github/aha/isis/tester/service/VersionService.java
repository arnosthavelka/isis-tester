package com.github.aha.isis.tester.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Read application version from config file (filled in by maven).
 */
@Service
public class VersionService {
	
    /**
     * Class logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(VersionService.class);
    
	private String appVersion;

	public String getAppVersion() {
		return this.appVersion;
	}

	@Value("${app.version:N/A}")
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
		LOG.debug("app version={}", this.appVersion);
	}

}
