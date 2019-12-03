package com.example.demoSpBoot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hoadonbanhang")
public class hoadonbanhang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mahoadon;
	private String makhachhang;
	private int loaithanhtoan;
	private long tonggia;
	private long giamgia;
	private long khachhangtra;
	private int trangthai;
	private Date createdAt;
	private Date updatedAt;
	private String nguoisua;
	private String nguoitao;
	private String chitiethoadon;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMahoadon() {
		return mahoadon;
	}
	public void setMahoadon(String mahoadon) {
		this.mahoadon = mahoadon;
	}
	public String getMakhachhang() {
		return makhachhang;
	}
	public void setMakhachhang(String makhachhang) {
		this.makhachhang = makhachhang;
	}
	public int getLoaithanhtoan() {
		return loaithanhtoan;
	}
	public void setLoaithanhtoan(int loaithanhtoan) {
		this.loaithanhtoan = loaithanhtoan;
	}
	public long getTonggia() {
		return tonggia;
	}
	public void setTonggia(long tonggia) {
		this.tonggia = tonggia;
	}
	public long getGiamgia() {
		return giamgia;
	}
	public void setGiamgia(long giamgia) {
		this.giamgia = giamgia;
	}
	public long getKhachhangtra() {
		return khachhangtra;
	}
	public void setKhachhangtra(long khachhangtra) {
		this.khachhangtra = khachhangtra;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
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
	public String getNguoisua() {
		return nguoisua;
	}
	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}
	public String getNguoitao() {
		return nguoitao;
	}
	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}
	public String getChitiethoadon() {
		return chitiethoadon;
	}
	public void setChitiethoadon(String chitiethoadon) {
		this.chitiethoadon = chitiethoadon;
	}
	public hoadonbanhang(int id, String mahoadon, String makhachhang, int loaithanhtoan, long tonggia, long giamgia,
			long khachhangtra, int trangthai, Date createdAt, Date updatedAt, String nguoisua, String nguoitao,
			String chitiethoadon) {
		this.id = id;
		this.mahoadon = mahoadon;
		this.makhachhang = makhachhang;
		this.loaithanhtoan = loaithanhtoan;
		this.tonggia = tonggia;
		this.giamgia = giamgia;
		this.khachhangtra = khachhangtra;
		this.trangthai = trangthai;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.nguoisua = nguoisua;
		this.nguoitao = nguoitao;
		this.chitiethoadon = chitiethoadon;
	}
	public hoadonbanhang() {

	}	
}
