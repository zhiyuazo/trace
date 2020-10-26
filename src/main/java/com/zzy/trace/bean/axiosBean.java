package com.zzy.trace.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//Below for axios ajax.框架
//0.add getter&setter method
//JAVA 转化接收到的JSON到类对象的时候，如果类中有内部类，需要设置为Static 并且提供构造函数...
//1.inner class with [static]
//2.provide a default construct
//if not 1,2, the JsonObject from JS can not be convered to JAVA Object..
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class axiosBean {
	public int id;
	public String name;
	public String alias;
	public String label;
	public int age;
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
