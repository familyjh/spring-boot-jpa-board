package com.jpaboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, length=20)
    private String userID;
	
    private String userPWD;
    private String userName;
    private String userEmail;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User [userID=" + userID + ", userPWD=" + userPWD + ", userName=" + userName + ", userEmail=" + userEmail+ "]";
    }

	public void update(User newUser) {
		// TODO Auto-generated method stub
		this.userPWD = newUser.userPWD;
		this.userName = newUser.userName;
		this.userEmail = newUser.userEmail;
	}    
}
