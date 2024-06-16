package com.github.pireba.utils;

public class Stopwatch {
	
	private long startTime;
	private long resetTime;
	
	public Stopwatch() {
		long currentTime = System.currentTimeMillis();
		this.startTime = currentTime;
		this.resetTime = currentTime;
	}
	
	public long reset() {
		long currentTime = System.currentTimeMillis();
		long difference = currentTime - this.resetTime;
		this.resetTime = currentTime;
		return difference;
	}
	
	public long getTimeSinceLastReset() {
		long currentTime = System.currentTimeMillis();
		return (currentTime - this.resetTime);
	}
	
	public String getTimeSinceLastResetFormatted() {
		return this.formatTime(this.getTimeSinceLastReset());
	}
	
	public long getTotalTime() {
		long currentTime = System.currentTimeMillis();
		return (currentTime - this.startTime);
	}
	
	public String getTotalTimeFormatted() {
		return this.formatTime(this.getTotalTime());
	}
	
	private String formatTime(long time) {
		if ( time > 1000 ) {
			return (time / 1000) + "." + (time % 1000) + " s";
		} else {
			return time + " ms";
		}
	}
}