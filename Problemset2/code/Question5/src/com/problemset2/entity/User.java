package com.problemset2.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="USERS")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String password;
	private String username;

	@OneToMany(mappedBy="user")
	private List<Answer> answers;

	@OneToMany(mappedBy="user")
	private List<Question> questions;
    public User(int id, String username, String password) {
    	this.id = id;
    	this.username = username;
    	this.password = password;
    }

	public int getId() {
		return this.id;
	}
	public String getPassword() {
		return this.password;
	}
	public String getUsername() {
		return this.username;
	}
	public List<Answer> getAnswers() {
		return this.answers;
	}
	public List<Question> getQuestions() {
		return this.questions;
	}
	
}