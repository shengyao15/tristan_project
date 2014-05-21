package com.tristan;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

public class Log4jConfig {

	public void enableInfo(String target) {
		LogManager.getLogger(target).setLevel(Level.INFO);
	}

	public void enableWarn(String target) {
		LogManager.getLogger(target).setLevel(Level.WARN);
	}

	public void enableError(String target) {
		LogManager.getLogger(target).setLevel(Level.ERROR);
	}

	public void enableDebug(String target) {
		LogManager.getLogger(target).setLevel(Level.DEBUG);
	}

}
