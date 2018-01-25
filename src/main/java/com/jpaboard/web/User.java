package com.jpaboard.web;

public class User {
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
    
}
