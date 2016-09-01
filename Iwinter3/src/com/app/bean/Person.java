package com.app.bean;

public class Person {

	
	
	private boolean name;
	private int age;
	private float sex;
	private Leg leg;
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex=" + sex + ", leg=" + leg + "]";
	}
	public boolean isName() {
		return name;
	}
	public void setName(boolean name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getSex() {
		return sex;
	}
	public void setSex(float sex) {
		this.sex = sex;
	}
	public Leg getLeg() {
		return leg;
	}
	public void setLeg(Leg leg) {
		this.leg = leg;
	}
	
	
}
