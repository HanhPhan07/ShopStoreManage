package com.example.demoSpBoot.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class hoadonnhaphang {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mahoadon;
	private int nhacungcap;
	private int loaithanhtoan;
	private long tonggia;
	private long giamgia;
	private long datra;
	private int trangthai;
	private Date createdAt;
	private Date updatedAt;
	private String nguoitao;
	private String nguoisua;
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
	public int getNhacungcap() {
		return nhacungcap;
	}
	public void setNhacungcap(int nhacungcap) {
		this.nhacungcap = nhacungcap;
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
	public long getDatra() {
		return datra;
	}
	public void setDatra(long datra) {
		this.datra = datra;
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
	public String getNguoitao() {
		return nguoitao;
	}
	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}
	public String getNguoisua() {
		return nguoisua;
	}
	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}
	public String getChitiethoadon() {
		return chitiethoadon;
	}
	public void setChitiethoadon(String chitiethoadon) {
		this.chitiethoadon = chitiethoadon;
	}
	public hoadonnhaphang(int id, String mahoadon, int nhacungcap, int loaithanhtoan, long tonggia, long giamgia,
			long datra, int trangthai, Date createdAt, Date updatedAt, String nguoitao, String nguoisua,
			String chitiethoadon) {
		this.id = id;
		this.mahoadon = mahoadon;
		this.nhacungcap = nhacungcap;
		this.loaithanhtoan = loaithanhtoan;
		this.tonggia = tonggia;
		this.giamgia = giamgia;
		this.datra = datra;
		this.trangthai = trangthai;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.nguoitao = nguoitao;
		this.nguoisua = nguoisua;
		this.chitiethoadon = chitiethoadon;
	}
	public hoadonnhaphang() {

	}
}
