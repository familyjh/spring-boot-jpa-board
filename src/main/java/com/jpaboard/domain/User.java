package com.jpaboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends AbstractEntity {
	@Column(nullable=false, length=20, unique=true)
	@JsonProperty
    private String userID;
	
	@Column(nullable=false, length=80)
	@JsonIgnore
    private String userPWD;
    
    @Column(nullable=false, length=20)
    @JsonProperty
    private String userName;
    
    @Column(nullable=false, length=50)
    @JsonProperty
    private String userEmail;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public boolean matchPassword(String newPassword) {
    	if (newPassword == null) {
    		return false;
    	}
    	
    	return newPassword.equals(userPWD);
    }    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

	public void update(User newUser) {
		this.userPWD = newUser.userPWD;
		this.userName = newUser.userName;
		this.userEmail = newUser.userEmail;
	}

	public String getUserId() {
		// TODO Auto-generated method stub
		return userID;
	}
    
    public boolean matchId(Long newId) {
    	if (newId == null) {
    		return false;
    	}
    	
    	return newId.equals(getId());
    }    
    
    @Override
    public String toString() {
        return "User [" + super.toString() + "userID=" + userID + ", userPWD=" + userPWD + ", userName=" + userName + ", userEmail=" + userEmail+ "]";
    }

}
