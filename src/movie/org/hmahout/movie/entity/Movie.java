package org.hmahout.movie.entity;

import org.hmahout.annotation.Table;
import org.hmahout.entity.Entity;

@Table(name="movie")
public class Movie extends Entity {
	
	private Integer id;
	private String name;
	private String publish_year;
	private String type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublish_year() {
		return publish_year;
	}
	public void setPublish_year(String publish_year) {
		this.publish_year = publish_year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
