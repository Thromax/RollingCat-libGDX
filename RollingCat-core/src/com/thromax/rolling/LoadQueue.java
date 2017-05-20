package com.thromax.rolling;

// This is a custom list to store load pending assets
public class LoadQueue {
	
	public String file;
	public Class<?> type;

	public LoadQueue(String file, Class<?> type) {
		this.file = file;
		this.type = type;
	}
}
