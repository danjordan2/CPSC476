package com.problemset2.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="QUESTIONS")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	private int user_id;
	private String text;

	@Column(name="CREATED_AT")
	private Timestamp createdAt;

	@OneToMany(mappedBy="question")
	private List<Answer> answers;

    @ManyToOne
	private User user;
    public Question(int id, int user_id, String text) {
    	this.id = id;
    	this.user_id = user_id;
    	this.text = text;
    }
	public int getId() {
		return this.id;
	}
	public int getUser_id() {
		return this.user_id;
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
	public List<Answer> getAnswers() {
		return this.answers;
	}
	public User getUser() {
		return this.user;
	}
	
}