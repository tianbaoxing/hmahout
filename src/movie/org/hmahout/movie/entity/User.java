package org.hmahout.movie.entity;

import org.hmahout.annotation.Table;
import org.hmahout.entity.Entity;

@Table(name="user")
public class User extends Entity {
//用户编号::性别::年龄::职业::Zip-code
	private Integer id;
	private String gender;
	private Integer age;
	private String job;
	private String zipcode ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
