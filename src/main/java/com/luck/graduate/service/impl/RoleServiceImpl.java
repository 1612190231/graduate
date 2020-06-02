package com.luck.graduate.service.impl;

import com.luck.graduate.dao.AuthorDao;
import com.luck.graduate.dao.RoleDao;
import com.luck.graduate.dao.UserDao;
import com.luck.graduate.entity.AuthorModel;
import com.luck.graduate.entity.RoleAuthorModel;
import com.luck.graduate.entity.RoleModel;
import com.luck.graduate.entity.UserModel;
import com.luck.graduate.service.RoleService;
import com.luck.graduate.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    AuthorDao authorDao;

    /*
    * @author luck
    * @Param [RoleModel]
    * @date 2020/4/10
    * @description 添加角色信息*/
    public boolean insertRole(RoleModel roleModel){
        roleModel.setCreateDate(DateUtil.getNowDate());
        roleDao.insertRole(roleModel);
        int roleId = roleDao.selectRole(roleModel).getRoleId();
        List<RoleAuthorModel> roleAuthorModels = authorDao.getAll();
        for(RoleAuthorModel roleAuthorModel : roleAuthorModels){
            roleAuthorModel.setRoleId(roleId);
            roleAuthorModel.setRoleIs(0);
        }
        return roleDao.insertRoleAuthor(roleAuthorModels);
    }

    /*
     * @author luck
     * @Param [RoleAuthorModel]
     * @date 2020/4/10
     * @description 查询角色信息*/
    public List<RoleModel> getRole(){
        return roleDao.getRole();
    }

    /*
     * @author luck
     * @Param [RoleModel]
     * @date 2020/5/3
     * @description 获取用户分组信息*/
    public UserModel getUser(RoleModel roleModel){
        List<UserModel> userModels = userDao.getUserList();
        List<UserModel> iUserModels = new ArrayList<>();
        List<UserModel> oUserModels = new ArrayList<>();
        UserModel result = new UserModel();
        for(UserModel userModel:userModels){
            if(userModel.getRoleId() == roleModel.getRoleId()){
                iUserModels.add(userModel);
            }
            if(userModel.getRoleId() == 0) {
                oUserModels.add(userModel);
            }
        }
        result.setiUserModels(iUserModels);
        result.setoUserModels(oUserModels);
        return result;
    }

    /*
     * @author luck
     * @Param [RoleModel]
     * @date 2020/5/3
     * @description 获取分组权限信息*/
    public AuthorModel getAuthor(RoleModel roleModel){
        List<AuthorModel> authorModels = authorDao.getAuthor(roleModel);
        List<AuthorModel> iAuthorModels = new ArrayList<>();
        List<AuthorModel> oAuthorModels = new ArrayList<>();
        AuthorModel result = new AuthorModel();
        for(AuthorModel authorModel:authorModels){
            if(authorModel.getRoleIs() == 1){
                iAuthorModels.add(authorModel);
            }
            if(authorModel.getRoleIs() == 0){
                oAuthorModels.add(authorModel);
            }
        }
        result.setiAuthorModels(iAuthorModels);
        result.setoAuthorModels(oAuthorModels);
        return result;
    }

    /*
     * @author luck
     * @Param [RoleModel]
     * @date 2020/4/10
     * @description 删除角色信息*/
    public boolean deleteRole(RoleModel roleModel){
        if(roleDao.deleteRole(roleModel)==true){
            UserModel userModel = new UserModel();
            userModel.setRoleId(roleModel.getRoleId());
            userDao.updateUserRoleByRoleId(userModel);
            roleDao.deleteRoleAuthor(roleModel);
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * @author luck
     * @Param [RoleModel]
     * @date 2020/4/10
     * @description 修改系统组信息*/
    public boolean updateRole(RoleModel roleModel){
        roleModel.setUpdateDate(DateUtil.getNowDate());

        if(roleDao.updateRole(roleModel)==true){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateRoleAuthor(AuthorModel authorModel){

        authorModel.setUpdateDate(DateUtil.getNowDate());
        if(roleDao.updateRoleAuthor(authorModel)){
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * @author luck
     * @Param [RoleAuthorModel]
     * @date 2020/4/10
     * @description 添加角色权限信息*/
    public boolean insertRoleAuthor(List<RoleAuthorModel> roleAuthorModel){
        if(roleDao.insertRoleAuthor(roleAuthorModel)==true){
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * @author luck
     * @Param [RoleAuthorModel]
     * @date 2020/4/10
     * @description 查询角色权限信息*/
    public List<RoleAuthorModel> selectRoleAuthor(RoleModel roleModel){
        return roleDao.selectRoleAuthor(roleModel);
    }
}
