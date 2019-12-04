package com.example.demoSpBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chitietdanhmuc")
public class chitietdanhmuc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int id_danhmuc;
	private int id_sanpham;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_danhmuc() {
		return id_danhmuc;
	}
	public void setId_danhmuc(int id_danhmuc) {
		this.id_danhmuc = id_danhmuc;
	}
	public int getId_sanpham() {
		return id_sanpham;
	}
	public void setId_sanpham(int id_sanpham) {
		this.id_sanpham = id_sanpham;
	}
	public chitietdanhmuc(int id, int id_danhmuc, int id_sanpham) {
		this.id = id;
		this.id_danhmuc = id_danhmuc;
		this.id_sanpham = id_sanpham;
	}
	public chitietdanhmuc() {
		
	}
	
}
