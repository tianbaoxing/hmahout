package org.hmahout.movie.entity;

import org.hmahout.annotation.Table;
import org.hmahout.entity.Entity;

@Table(name="movie_preference")
public class Moviepreference extends Entity {
	private Integer userId;
	private Integer movieId;
	private Integer preference;
	private Integer time;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Integer getPreference() {
		return preference;
	}
	public void setPreference(Integer preference) {
		this.preference = preference;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	
	

}
