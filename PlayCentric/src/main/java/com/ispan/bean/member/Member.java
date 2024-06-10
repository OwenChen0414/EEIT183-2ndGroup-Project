package com.ispan.bean.member;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "members")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name = "mem_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "passwords")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "nickname")
	private String nickName;
	
	@Column(name = "mem_name")
	private String memName;
	
	@Column(name = "birthday")
	private String birthday;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "addres")
	private String address;
	
	@Column(name = "sso")
	private String sso;
	
	@Column(name = "accom_acnt")
	private String accomAccount;
	
	@Column(name = "consumption")
	private String consumption;
	
	@Column(name = "regist_date")
	private String registDate;
	
	@Column(name = "last_login_time")
	private String lastLogin;
	
	@Column(name = "roles")
	private String role;
	
	@Column(name = "levels", insertable = false, updatable = false)
	private String level;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "level_id")
	private MemLevel memLevel;
	

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Member() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAccomAccount() {
		return accomAccount;
	}

	public void setAccomAccount(String accomAccount) {
		this.accomAccount = accomAccount;
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

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

}
