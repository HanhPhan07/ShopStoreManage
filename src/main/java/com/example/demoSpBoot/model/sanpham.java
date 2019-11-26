package com.example.demoSpBoot.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class sanpham {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String masp;
	private String tensp;
	private long giagoc;
	private long giaban;
	private int soluong;
	private int trangthai;
	private int nhasanxuat;
	private String danhmuc;
	private String anhsp;
	private String motasp;
	private String donvitinh;
	private boolean ishot;
	private boolean isnew;
	private boolean displaywebsite;
	private Date createdAt;
	private Date updatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public long getGiagoc() {
		return giagoc;
	}
	public void setGiagoc(long giagoc) {
		this.giagoc = giagoc;
	}
	public long getGiaban() {
		return giaban;
	}
	public void setGiaban(long giaban) {
		this.giaban = giaban;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	public int getNhasanxuat() {
		return nhasanxuat;
	}
	public void setNhasanxuat(int nhasanxuat) {
		this.nhasanxuat = nhasanxuat;
	}
	public String getDanhmuc() {
		return danhmuc;
	}
	public void setDanhmuc(String danhmuc) {
		this.danhmuc = danhmuc;
	}
	public String getAnhsp() {
		return anhsp;
	}
	public void setAnhsp(String anhsp) {
		this.anhsp = anhsp;
	}
	public String getMotasp() {
		return motasp;
	}
	public void setMotasp(String motasp) {
		this.motasp = motasp;
	}
	public String getDonvitinh() {
		return donvitinh;
	}
	public void setDonvitinh(String donvitinh) {
		this.donvitinh = donvitinh;
	}
	public boolean isIshot() {
		return ishot;
	}
	public void setIshot(boolean ishot) {
		this.ishot = ishot;
	}
	public boolean isIsnew() {
		return isnew;
	}
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}
	public boolean isDisplaywebsite() {
		return displaywebsite;
	}
	public void setDisplaywebsite(boolean displaywebsite) {
		this.displaywebsite = displaywebsite;
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
	public sanpham(int id, String masp, String tensp, long giagoc, long giaban, int soluong, int trangthai,
			int nhasanxuat, String danhmuc, String anhsp, String motasp, String donvitinh, boolean ishot, boolean isnew,
			boolean displaywebsite, Date createdAt, Date updatedAt) {
		this.id = id;
		this.masp = masp;
		this.tensp = tensp;
		this.giagoc = giagoc;
		this.giaban = giaban;
		this.soluong = soluong;
		this.trangthai = trangthai;
		this.nhasanxuat = nhasanxuat;
		this.danhmuc = danhmuc;
		this.anhsp = anhsp;
		this.motasp = motasp;
		this.donvitinh = donvitinh;
		this.ishot = ishot;
		this.isnew = isnew;
		this.displaywebsite = displaywebsite;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public sanpham() {

	}
	
}
