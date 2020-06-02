package com.luck.graduate.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.luck.graduate.dao.UserDao;
import com.luck.graduate.entity.RoleModel;
import com.luck.graduate.entity.UserModel;
import com.luck.graduate.entity.UserRoleModel;
import com.luck.graduate.service.UserService;
import com.luck.graduate.utils.DateUtil;
import com.luck.graduate.utils.EmptyUtil;
import org.apache.catalina.User;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public boolean register(UserModel userModel){
        userModel.setUpdateDate(DateUtil.getNowDate());
        if(userDao.insertUser(userModel)==false){
            return false;
        }
        int userId = userDao.selectUserByDate(userModel).getUserId();
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setUserId(userId);
        userRoleModel.setRoleId(0);
        userRoleModel.setCreateDate(userModel.getUpdateDate());
        if(userDao.insertUserRole(userRoleModel)==false){
            return false;
        }
        return true;
    }

    public HashMap<String,Object> selectUser (UserModel userModel){
        //调刚刚上面定义的userService中的制造token的方法得到token
        UserModel user = userDao.login(userModel);
        HashMap<String, Object> data = new HashMap<String, Object>();
        if (EmptyUtil.isEmpty(user)){
            data.put("msg","用户login信息匹配失败");
            return data;
        }
        List<UserModel> item = userDao.selectUser(user);
        if(EmptyUtil.isEmpty(item)){
            data.put("msg","登录成功，用户无权限");
            return data;
        }
        else{

            //生成JWTtoken
            String token="";
            Date date = new Date(System.currentTimeMillis()+1000*60*2*24);
            token= JWT.create()
                    .withAudience("" + user.getUserId())
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC256("iiiii"));
            //System.out.println(token);

            List<String> authors = new ArrayList<>();
            for (UserModel u : item) {
                authors.add(u.getAuthorName());
            }
            user.setAuthorsName(authors);

            data.put("token",token);
            data.put("userModel",user);
            data.put("msg","登录成功！");

            return data;
        }
    }

    public boolean changePsd (UserModel userModel){
        return userDao.changePsd(userModel);
    }

    /*
     * @author luck
     * @Param [UserRoleModel]
     * @date 2020/4/10
     * @description 添加用户角色信息*/
    public boolean insertUserRole (UserRoleModel userRoleModel){
        userRoleModel.setCreateDate(DateUtil.getNowDate());
        userRoleModel.setUpdateDate(DateUtil.getNowDate());
        if(!EmptyUtil.isEmpty(userRoleModel.getCreateId())){
            userRoleModel.setUpdateId(userRoleModel.getCreateId());
        }
        if(userDao.insertUserRole(userRoleModel)==true){
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * @author luck
     * @Param [UserRoleModel]
     * @date 2020/4/10
     * @description 删除用户角色信息*/
    public boolean deleteUserRole (UserRoleModel userRoleModel){

        if(userDao.deleteUserRole(userRoleModel)==true){
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * @author luck
     * @Param [UserRoleModel]
     * @date 2020/4/10
     * @description 更新用户角色信息*/
    public boolean updateUserRole (UserModel userModel){
        if(userDao.updateUserRole(userModel)==true){
            return true;
        }
        else {
            return false;
        }
    }

    public UserRoleModel selectUserRole (UserModel userModel) {
        return userDao.selectUserRole(userModel);
    }
}
