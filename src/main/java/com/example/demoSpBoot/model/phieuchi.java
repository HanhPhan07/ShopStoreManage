package com.example.demoSpBoot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class phieuchi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String maphieuchi;
	private int idhoadon;
	private long sotienchi;
	private Date ngaychi;
	private String nguoichi;
	private int hinhthucchi;
	private Date ngaysua;
	private String nguoisua;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaphieuchi() {
		return maphieuchi;
	}
	public void setMaphieuchi(String maphieuchi) {
		this.maphieuchi = maphieuchi;
	}
	public int getIdhoadon() {
		return idhoadon;
	}
	public void setIdhoadon(int idhoadon) {
		this.idhoadon = idhoadon;
	}
	public long getSotienchi() {
		return sotienchi;
	}
	public void setSotienchi(long sotienchi) {
		this.sotienchi = sotienchi;
	}
	public Date getNgaychi() {
		return ngaychi;
	}
	public void setNgaychi(Date ngaychi) {
		this.ngaychi = ngaychi;
	}
	public String getNguoichi() {
		return nguoichi;
	}
	public void setNguoichi(String nguoichi) {
		this.nguoichi = nguoichi;
	}
	public int getHinhthucchi() {
		return hinhthucchi;
	}
	public void setHinhthucchi(int hinhthucchi) {
		this.hinhthucchi = hinhthucchi;
	}
	public Date getNgaysua() {
		return ngaysua;
	}
	public void setNgaysua(Date ngaysua) {
		this.ngaysua = ngaysua;
	}
	public String getNguoisua() {
		return nguoisua;
	}
	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}
	public phieuchi(int id, String maphieuchi, int idhoadon, long sotienchi, Date ngaychi, String nguoichi,
			int hinhthucchi, Date ngaysua, String nguoisua) {
		this.id = id;
		this.maphieuchi = maphieuchi;
		this.idhoadon = idhoadon;
		this.sotienchi = sotienchi;
		this.ngaychi = ngaychi;
		this.nguoichi = nguoichi;
		this.hinhthucchi = hinhthucchi;
		this.ngaysua = ngaysua;
		this.nguoisua = nguoisua;
	}
	public phieuchi() {
		
	}
	
}
