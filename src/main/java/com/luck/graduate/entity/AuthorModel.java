package com.luck.graduate.entity;

import com.luck.graduate.utils.BaseEntity;

import java.util.List;

public class AuthorModel {
    private int authorId;
    private String authorName;
    private int createId;
    private String createDate;
    private int updateId;
    private String updateDate;
    private String remark;

    private int roleIs;
    private int roleId;
    private String roleName;
    private List<Integer> iAuthors;
    private List<AuthorModel> authorModels;
    private List<AuthorModel> iAuthorModels;
    private List<AuthorModel> oAuthorModels;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRoleIs() {
        return roleIs;
    }

    public void setRoleIs(int roleIs) {
        this.roleIs = roleIs;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Integer> getiAuthors() {
        return iAuthors;
    }

    public void setiAuthors(List<Integer> iAuthors) {
        this.iAuthors = iAuthors;
    }

    public List<AuthorModel> getAuthorModels() {
        return authorModels;
    }

    public void setAuthorModels(List<AuthorModel> authorModels) {
        this.authorModels = authorModels;
    }

    public List<AuthorModel> getiAuthorModels() {
        return iAuthorModels;
    }

    public void setiAuthorModels(List<AuthorModel> iAuthorModels) {
        this.iAuthorModels = iAuthorModels;
    }

    public List<AuthorModel> getoAuthorModels() {
        return oAuthorModels;
    }

    public void setoAuthorModels(List<AuthorModel> oAuthorModels) {
        this.oAuthorModels = oAuthorModels;
    }
}
