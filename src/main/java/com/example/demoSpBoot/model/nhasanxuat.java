package com.example.demoSpBoot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class nhasanxuat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tennsx;
	private Date createdAt;
	private Date updatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTennsx() {
		return tennsx;
	}
	public void setTennsx(String tennsx) {
		this.tennsx = tennsx;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public nhasanxuat(int id, String tennsx, Date createdAt, Date updatedAt) {
		this.id = id;
		this.tennsx = tennsx;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public nhasanxuat() {

	}	
}
