package com.luck.graduate.service;

import com.luck.graduate.entity.UserModel;
import com.luck.graduate.entity.UserRoleModel;

import java.util.HashMap;

public interface UserService {
    public boolean register(UserModel userModel);

    HashMap<String, Object> selectUser(UserModel userModel);

    public boolean changePsd(UserModel userModel);

    boolean insertUserRole(UserRoleModel userRoleModel);

    boolean deleteUserRole(UserRoleModel userRoleModel);

    boolean updateUserRole(UserModel userModel);

    public UserRoleModel selectUserRole (UserModel userModel);
}
