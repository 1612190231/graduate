package com.luck.graduate.entity;

import com.luck.graduate.utils.BaseEntity;

import java.util.List;

public class UserModel {
    private int userId;
    private String loginName;
    private String userName;
    private String userPhone;
    private String userStatus;
    private int roleId;
    private String roleName;
    private int authorId;
    private String authorName;
    private String gender;
    private String email;
    private String cardId;
    private String pwd;
    private String remark;
    private String updateDate;
    private List<UserModel> userModels;
    private List<String> authorsName;

    private List<Integer> iUsers;
    private List<UserModel> iUserModels;
    private List<UserModel> oUserModels;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<UserModel> getUserModels() {
        return userModels;
    }

    public void setUserModels(List<UserModel> userModels) {
        this.userModels = userModels;
    }

    public List<String> getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(List<String> authorsName) {
        this.authorsName = authorsName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public List<Integer> getiUsers() {
        return iUsers;
    }

    public void setiUsers(List<Integer> iUsers) {
        this.iUsers = iUsers;
    }

    public List<UserModel> getiUserModels() {
        return iUserModels;
    }

    public void setiUserModels(List<UserModel> iUserModels) {
        this.iUserModels = iUserModels;
    }

    public List<UserModel> getoUserModels() {
        return oUserModels;
    }

    public void setoUserModels(List<UserModel> oUserModels) {
        this.oUserModels = oUserModels;
    }
}
