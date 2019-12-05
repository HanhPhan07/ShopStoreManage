package com.example.demoSpBoot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class phieuthu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String maphieuthu;
	@ManyToOne
    @JoinColumn(name = "idhoadon")
	private hoadonbanhang hoadonbanhang;
	private long sotienthu;
	private Date ngaythu;
	private String nguoithu;
	private int hinhthucthu;
	private Date ngaysua;
	private String nguoisua;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaphieuthu() {
		return maphieuthu;
	}
	public void setMaphieuthu(String maphieuthu) {
		this.maphieuthu = maphieuthu;
	}
	public long getSotienthu() {
		return sotienthu;
	}
	public void setSotienthu(long sotienthu) {
		this.sotienthu = sotienthu;
	}
	public Date getNgaythu() {
		return ngaythu;
	}
	public void setNgaythu(Date ngaythu) {
		this.ngaythu = ngaythu;
	}
	public String getNguoithu() {
		return nguoithu;
	}
	public void setNguoithu(String nguoithu) {
		this.nguoithu = nguoithu;
	}
	public int getHinhthucthu() {
		return hinhthucthu;
	}
	public void setHinhthucthu(int hinhthucthu) {
		this.hinhthucthu = hinhthucthu;
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
	public phieuthu(int id, String maphieuthu, long sotienthu, Date ngaythu, String nguoithu,
			int hinhthucthu, Date ngaysua, String nguoisua, hoadonbanhang hoadonbanhang) {
		this.id = id;
		this.maphieuthu = maphieuthu;
		this.sotienthu = sotienthu;
		this.ngaythu = ngaythu;
		this.nguoithu = nguoithu;
		this.hinhthucthu = hinhthucthu;
		this.ngaysua = ngaysua;
		this.nguoisua = nguoisua;
		this.hoadonbanhang=hoadonbanhang;
	}
	public hoadonbanhang getHoadonbanhang() {
		return hoadonbanhang;
	}
	public void setHoadonbanhang(hoadonbanhang hoadonbanhang) {
		this.hoadonbanhang = hoadonbanhang;
	}
	public phieuthu() {
		
	}
	
}
