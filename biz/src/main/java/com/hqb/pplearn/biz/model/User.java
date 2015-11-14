package com.hqb.pplearn.biz.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity{

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	
    private String email;
    private String password;
    private String userAccount;
    private String nickName;
    private Date birthDate;
    private String gender;
    private String headImageUrl;
    private String offer;
    private String want;
    private String introduce;
    private String rongCloudToken;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHeadImageUrl() {
		return headImageUrl;
	}
	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getWant() {
		return want;
	}
	public void setWant(String want) {
		this.want = want;
	}
	public String getRongCloudToken() {
		return rongCloudToken;
	}
	public void setRongCloudToken(String rongCloudToken) {
		this.rongCloudToken = rongCloudToken;
	}
}