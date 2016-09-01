package com.app.bean;

public class Leg {

	private String thumb;
	private int num;
	@Override
	public String toString() {
		return "Leg [thumb=" + thumb + ", num=" + num + "]";
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
