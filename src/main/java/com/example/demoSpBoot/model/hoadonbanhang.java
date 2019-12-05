package com.example.demoSpBoot.model;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "hoadonbanhang")
public class hoadonbanhang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mahoadon;
	private int loaithanhtoan;
	private long tonggia;
	private long giamgia;
	private long khachhangtra;
	private int trangthai;
	private Date createdAt;
	private Date updatedAt;
	private String nguoisua;
	private String nguoitao;
	@OneToMany(
            cascade =  CascadeType.ALL,
            mappedBy = "hoadonbanhang")
	private Set<phieuthu> phieuthu;
	@ManyToOne
    @JoinColumn(name = "makhachhang")
	private khachhang khachhang;
	public khachhang getKhachhang() {
		return khachhang;
	}
	public void setKhachhang(khachhang khachhang) {
		this.khachhang = khachhang;
	}
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


	public hoadonbanhang(int id, String mahoadon,  int loaithanhtoan, long tonggia, long giamgia,
			long khachhangtra, int trangthai, Date createdAt, Date updatedAt, String nguoisua, String nguoitao,
			khachhang khachhang, phieuthu...phieuthus) {
		this.id = id;
		this.mahoadon = mahoadon;
		this.khachhang=khachhang;
		this.loaithanhtoan = loaithanhtoan;
		this.tonggia = tonggia;
		this.giamgia = giamgia;
		this.khachhangtra = khachhangtra;
		this.trangthai = trangthai;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.nguoisua = nguoisua;
		this.nguoitao = nguoitao;
		this.phieuthu=Stream.of(phieuthus).collect(Collectors.toSet());
		this.phieuthu.forEach(x -> x.setHoadonbanhang(this));
	}
	public hoadonbanhang() {

	}	
}
