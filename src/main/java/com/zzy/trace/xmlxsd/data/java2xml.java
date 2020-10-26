package com.zzy.trace.xmlxsd.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class java2xml {
    private int id;
    private String name;
    private String gender;
    private String addr;
    private String area;
 
    public java2xml() {
    }
 
    public java2xml(String name, String gender, String addr, String area) {
        this.name = name;
        this.gender = gender;
        this.addr = addr;
        this.area = area;
    }
 
    public int getId() {
        return id;
    }
 
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
 
    public String getGender() {
        return gender;
    }
 
    @XmlElement
    public void setGender(String gender) {
        this.gender = gender;
    }
 
    public String getAddr() {
        return addr;
    }
 
    @XmlElement
    public void setAddr(String addr) {
        this.addr = addr;
    }
 
    public String getArea() {
        return area;
    }
 
    @XmlElement
    public void setArea(String area) {
        this.area = area;
    }
 
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", addr='" + addr + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}