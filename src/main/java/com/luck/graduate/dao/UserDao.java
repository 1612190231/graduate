package com.luck.graduate.dao;

import com.luck.graduate.entity.UserModel;
import com.luck.graduate.entity.UserRoleModel;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /*
    * @author luck
    * @param UserModel
    * @return boolean
    * @date 2020.4.10
    * */
    public boolean insertUser(UserModel userModel);

    public UserModel login(UserModel userModel);

    public List<UserModel> selectUser(UserModel userModel);

    public boolean changePsd(UserModel userModel);

    public boolean insertUserRole(UserRoleModel userRoleModel);

    public UserRoleModel selectUserRole(UserModel userModel);

    public boolean deleteUserRole(UserRoleModel userRoleModel);

    public boolean updateUserRole(UserModel userModel);

    public boolean updateUserRoleByRoleId(UserModel userModel);

    public UserModel selectUserByDate(UserModel userModel);

    public List<UserModel> getUserList();
}
