package com.example.demoSpBoot.model;

import java.util.Date;

public class khachhang {
	private String makhachhang;
	private String ten;
	private String sdt;
	private String email;
	private String diachi;
	private Date ngaysinh;
	private boolean gioitinh;
	public String getMakhachhang() {
		return makhachhang;
	}
	public void setMakhachhang(String makhachhang) {
		this.makhachhang = makhachhang;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public Date getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public boolean isGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}
	public khachhang(String makhachhang, String ten, String sdt, String email, String diachi, Date ngaysinh,
			boolean gioitinh) {
		this.makhachhang = makhachhang;
		this.ten = ten;
		this.sdt = sdt;
		this.email = email;
		this.diachi = diachi;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
	}
	public khachhang() {

	}
}
