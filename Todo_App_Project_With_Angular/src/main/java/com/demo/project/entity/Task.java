package com.demo.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tasktable")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Task implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long taskId;

	@Column(name = "task_name")
	private String task_name;

	@Column(name = "task_Description")
	private String task_Description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	@JsonIgnore
	private User user;

	public Task() {
		
	}

	public Task(Long taskId, String task_name, String task_Description) {
		super();
		this.taskId = taskId;
		this.task_name = task_name;
		this.task_Description = task_Description;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String gettask_name() {
		return task_name;
	}

	public void settask_name(String task_name) {
		this.task_name = task_name;
	}

	public String gettask_Description() {
		return task_Description;
	}

	public void settask_Description(String task_Description) {
		this.task_Description = task_Description;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", task_name=" + task_name + ", task_Description=" + task_Description + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
