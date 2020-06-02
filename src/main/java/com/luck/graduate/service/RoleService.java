package com.luck.graduate.service;

import com.luck.graduate.entity.AuthorModel;
import com.luck.graduate.entity.RoleAuthorModel;
import com.luck.graduate.entity.RoleModel;
import com.luck.graduate.entity.UserModel;

import java.util.List;

public interface RoleService {
    public boolean insertRole(RoleModel roleModel);

    public boolean deleteRole(RoleModel roleModel);

    public boolean updateRole(RoleModel roleModel);

    public boolean updateRoleAuthor(AuthorModel authorModel);

    public List<RoleModel> getRole();

    public UserModel getUser(RoleModel roleModel);

    public AuthorModel getAuthor(RoleModel roleModel);

    public boolean insertRoleAuthor(List<RoleAuthorModel> roleAuthorModel);

    public List<RoleAuthorModel> selectRoleAuthor(RoleModel roleModel);
}
