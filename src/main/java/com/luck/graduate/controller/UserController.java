package com.luck.graduate.controller;

import com.google.gson.Gson;
import com.luck.graduate.entity.UserRoleModel;
import com.luck.graduate.service.UserService;

import com.luck.graduate.utils.EmptyUtil;
import com.luck.graduate.utils.JWTToken.PassToken;
import com.luck.graduate.utils.MyException;
import com.luck.graduate.utils.result.Result;
import com.luck.graduate.entity.UserModel;
import com.luck.graduate.utils.result.ResultEnum;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody UserModel userModel){
        if (EmptyUtil.isEmpty(userModel)){
            return Result.error("用户信息为空");
        }
        if (EmptyUtil.isEmpty(userModel.getLoginName())||EmptyUtil.isEmpty(userModel.getPwd())){
            return Result.error("用户名或密码为空");
        }
        try{
            boolean flag = userService.register(userModel);
            if (flag == true){
                return Result.success("用户注册成功");
            }
            else {
                return Result.error("用户注册失败");
            }
        } catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"用户注册Exception");
        }
    }

    /*
    * @Author luck
    * @Date 2020/4/8
    * @Param [UserModel]
    * @Description 用户登录
    * */
    @PostMapping("/login")
    @ResponseBody
    @PassToken
    public Result userLogin(HttpServletResponse response, @RequestBody UserModel userModel){
        //System.out.println(userModel.getLoginName());
        //System.out.println(userModel.getPassWord());
        if (EmptyUtil.isEmpty(userModel)){
            return Result.error("用户信息为空！");
        }
        if (EmptyUtil.isEmpty(userModel.getUserName())){
            return Result.error("用户名为空！");
        }
        if (EmptyUtil.isEmpty(userModel.getPwd())){
            return Result.error("密码为空");
        }
        try{
            HashMap<String, Object> result = userService.selectUser(userModel);
            String msg = result.get("msg").toString();
            if (msg.equals("用户信息匹配失败")){
                return Result.error(msg);
            }
            else {
                //向主服务发送token
                Gson gson = new Gson();
                HashMap<String, Object> sendMsg = new HashMap<String, Object>();
                sendMsg.put("token",result.get("token"));
                sendMsg.put("userName",result.get("userName"));
                sendMsg.put("pwd",result.get("pwd"));
                String url = "http://120.53.27.131:8205/api/user/login";
                Header[] responseHeader = this.sendPostRequest(url, gson.toJson(sendMsg));
                /*if(responseHeader != null && responseHeader.length != 0){
                    for (Header stepHeader : responseHeader){
                        if(stepHeader != null){
                            //System.out.println("--------------");
                            response.addHeader(stepHeader.getName(), stepHeader.getValue());
                            //System.out.println("name: " + stepHeader.getName() + ";value: " + stepHeader.getValue());
                            //System.out.println("--------------");
                        }
                    }
                }
                else{
                    return Result.error("主服务访问失败");
                }*/
                return Result.success(result);
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"用户登录Exception");
        }
    }

    public Header[] sendPostRequest(String url, String data){
        //设置连接配置
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
                .setSocketTimeout(5000).setConnectionRequestTimeout(5000).build();
        //创建HtppClient实例
        HttpClient httpClient = HttpClientBuilder.create().build();
        //创建Post的连接方式实例
        HttpPost httpPost = new HttpPost(url);
        //配置请求头
        httpPost.addHeader("content-type","application/json");
        httpPost.addHeader("Accept","application/json");
        httpPost.setConfig(requestConfig);
        try{
            //添加请求体信息
            StringEntity entity = new StringEntity(data, "UTF-8");
            httpPost.setEntity(entity);
            //接收响应信息
            HttpResponse httpResponse = httpClient.execute(httpPost);
            Header[] responseHeader = httpResponse.getHeaders("Set-Cookie");
            return responseHeader;
        }catch (IOException e){
            return null;
        } finally {
            //释放连接
            httpPost.releaseConnection();
        }
    }

    @PostMapping("/changePsd")
    @ResponseBody
    public Result changPsd(@RequestBody UserModel userModel){
        if (EmptyUtil.isEmpty(userModel)){
            return Result.error("用户信息为空");
        }
        if (EmptyUtil.isEmpty(userModel.getPwd())){
            return Result.error("新密码为空");
        }
        try{
            boolean flag = userService.changePsd(userModel);
            if (flag == true){
                return Result.success("密码更新成功");
            }
            else {
                return Result.error("密码更新失败");
            }
        } catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"密码更新Exception");
        }
    }

    /*
     * @Author luck
     * @Date 2020/4/8
     * @Param [UserRoleModel]
     * @Description 赋予用户角色
     * */
    @PostMapping("/insertUserRole")
    @ResponseBody
    public Result insertUserRole(@RequestBody UserRoleModel userRoleModel){
        if (EmptyUtil.isEmpty(userRoleModel)){
            return Result.error("用户角色信息为空！");
        }
        if (EmptyUtil.isEmpty(userRoleModel.getRoleId())||EmptyUtil.isEmpty(userRoleModel.getUserId())){
            return Result.error("用户id或角色id为空！");
        }
        try{
            boolean flag = userService.insertUserRole(userRoleModel);
            if(flag == true){
                return Result.success("用户角色新增成功！");
            }
            else {
                return Result.error("用户角色新增失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"赋予用户角色Exception");
        }
    }

    /*
     * @Author luck
     * @Date 2020/4/10
     * @Param [UserRoleModel]
     * @Description 删除用户角色
     * */
    @PostMapping("/deleteUserRole")
    @ResponseBody
    public Result deleteUserRole(@RequestBody UserRoleModel userRoleModel){
        if (EmptyUtil.isEmpty(userRoleModel)){
            return Result.error("用户角色信息为空！");
        }
        try{
            boolean flag = userService.deleteUserRole(userRoleModel);
            if(flag == true){
                return Result.success("用户角色信息删除成功！");
            }
            else {
                return Result.error("用户角色信息删除失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"删除用户角色Exception");
        }
    }

    /*
     * @Author luck
     * @Date 2020/4/10
     * @Param [UserRoleModel]
     * @Description 修改用户角色
     * */
    @PostMapping("/updateUserRole")
    @ResponseBody
    public Result updateUserRole(@RequestBody UserModel userModel){
        if (EmptyUtil.isEmpty(userModel)){
            return Result.error("用户角色信息为空！");
        }
        if (EmptyUtil.isEmpty(userModel.getiUsers())&&EmptyUtil.isEmpty(userModel.getUserModels())){
            return Result.error("分组人员为空");
        }
        try{
            boolean flag = userService.updateUserRole(userModel);
            if(flag == true){
                return Result.success("用户角色信息修改成功！");
            }
            else {
                return Result.error("用户角色信息修改失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"修改用户角色Exception");
        }
    }

    /*
     * @Author luck
     * @Date 2020/4/24
     * @Param [UserModel]
     * @Description 查询用户角色
     * */
    @PostMapping("/selectUserRole")
    @ResponseBody
    public Result selectUserRole(@RequestBody UserModel userModel){
        if (EmptyUtil.isEmpty(userModel)){
            return Result.error("用户角色信息为空！");
        }
        try{
            return Result.success(userService.selectUserRole(userModel));
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"修改用户角色Exception");
        }
    }
}
