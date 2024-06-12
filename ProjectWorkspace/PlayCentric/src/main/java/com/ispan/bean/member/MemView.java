package com.ispan.bean.member;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "memview")
public class MemView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String memId;
	private String account;
	@Column(name = "passwords")
	private String password;
	private String email;
	private String nickName;
	private String memName;
	private String birthday;
	private String phone;
	@Column(name = "addres")
	private String address;
	private String sso;
	private String accomAcnt;
	private String consumption;
	private String registDate;
	private String lastLoginTime;
	@Column(name = "roles")
	private String role;
	private String levels;
	
	public MemView() { }
	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSso() {
		return sso;
	}

	public void setSso(String sso) {
		this.sso = sso;
	}

	public String getAccomAcnt() {
		return accomAcnt;
	}

	public void setAccomAcnt(String accomAcnt) {
		this.accomAcnt = accomAcnt;
	}

	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String level) {
		this.levels = level;
	}

}
