package com.problemset2.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ANSWERS")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private int id;
	private int user_id;
	private int question_id;
	private String text;

	@Column(name="CREATED_AT")
	private Timestamp createdAt;

    @ManyToOne
	private Question question;

    @ManyToOne
	private User user;
    public Answer(int user_id, int question_id, String text) {
    	this.user_id = user_id;
    	this.question_id = question_id;
    	this.text = text;
    }
	public int getUser_id() {
		return this.user_id;
	}
	public int getQuestion_id() {
		return this.question_id;
	}
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getText() {
		return this.text;
	}
	public Question getQuestion() {
		return this.question;
	}
	public User getUser() {
		return this.user;
	}
}