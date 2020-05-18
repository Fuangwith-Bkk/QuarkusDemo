package com.demo.data;

/**
 * Person
 */
public class Person {

    private String name;
    private String tel;
    private String city;

    public Person(){}

    public Person(String name, String tel, String city){
        this.name = name;
        this.tel = tel;
        this.city = city;
    }

	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setName(String name) {
		this.name = name;
	}
}