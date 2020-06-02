package com.luck.graduate.controller;

import com.luck.graduate.entity.AuthorModel;
import com.luck.graduate.entity.RoleAuthorModel;
import com.luck.graduate.entity.RoleModel;
import com.luck.graduate.entity.UserModel;
import com.luck.graduate.service.RoleService;
import com.luck.graduate.utils.EmptyUtil;
import com.luck.graduate.utils.JWTToken.PassToken;
import com.luck.graduate.utils.MyException;
import com.luck.graduate.utils.result.Result;
import com.luck.graduate.utils.result.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @author luck
 * @date 2020.4.10*/
@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    RoleService roleService;

    /*
    * @Author luck
    * @Param RoleModel
    * @date 2020/4/10
    * @description 角色信息添加*/
    @PostMapping("/insertRole")
    @ResponseBody
    public Result insertRole(@RequestBody RoleModel roleModel){
        if (EmptyUtil.isEmpty(roleModel)){
            return Result.error("角色信息为空");
        }
        if (EmptyUtil.isEmpty(roleModel.getRoleName())){
            return  Result.error("角色名为空");
        }
        try{
            boolean flag = roleService.insertRole(roleModel);
            if(flag == true){
                return Result.success("角色新增成功！");
            }
            else {
                return Result.error("角色新增失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"角色添加Exception");
        }
    }

    /*
     * @Author luck
     * @Param [RoleModel]
     * @date 2020/4/10
     * @description 角色信息删除*/
    @PostMapping("/deleteRole")
    @ResponseBody
    public Result deleteRole(@RequestBody RoleModel roleModel){
        if(EmptyUtil.isEmpty(roleModel)){
            return Result.error("角色信息为空！");
        }
        try{
            boolean flag = roleService.deleteRole(roleModel);
            if(flag == true){
                return Result.success("角色删除成功！");
            }
            else {
                return Result.error("角色删除失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"角色删除Exception");
        }
    }

    /*
     * @Author luck
     * @Param [RoleModel]
     * @date 2020/4/10
     * @description 角色信息修改*/
    @PostMapping("/updateRole")
    @ResponseBody
    public Result updateRole(@RequestBody RoleModel roleModel){
        if(EmptyUtil.isEmpty(roleModel)){
            return Result.error("角色信息为空！");
        }
        try{
            boolean flag = roleService.updateRole(roleModel);
            if(flag == true){
                return Result.success("角色修改成功！");
            }
            else {
                return Result.error("角色修改失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"角色信息修改Exception");
        }
    }

    /*
     * @Author luck
     * @Param [RoleModel]
     * @date 2020/4/10
     * @description 角色信息查询*/
    @GetMapping("/getRole")
    @ResponseBody
    public Result getRole(RoleModel roleModel){
        if(EmptyUtil.isEmpty(roleModel)){
            return Result.error("角色信息为空！");
        }
        try{
            return Result.success(roleService.getRole());
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"角色信息查询Exception");
        }
    }

    /*
     * @Author luck
     * @Param [RoleModel]
     * @date 2020/4/10
     * @description 用户分组信息信息查询*/
    @GetMapping("/getUser")
    @ResponseBody
    public Result getUser(RoleModel roleModel){
        if(EmptyUtil.isEmpty(roleModel)){
            return Result.error("角色信息为空！");
        }
        try{
            return Result.success(roleService.getUser(roleModel));
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"用户分组信息查询Exception");
        }
    }

    /*
     * @Author luck
     * @Param [RoleModel]
     * @date 2020/4/10
     * @description 分组权限信息信息查询*/
    @GetMapping("/getAuthor")
    @ResponseBody
    public Result getAuthor( RoleModel roleModel){
        if(EmptyUtil.isEmpty(roleModel)){
            return Result.error("组别信息为空！");
        }
        try{
            return Result.success(roleService.getAuthor(roleModel));
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"分组权限信息查询Exception");
        }
    }

    /*
     * @Author luck
     * @Param [RoleAuthorModel]
     * @date 2020/4/10
     * @description 角色权限信息添加*/
    @PostMapping("/insertRoleAuthor")
    @ResponseBody
    public Result insertRoleAuthor(@RequestBody List<RoleAuthorModel> roleAuthorModel){
        if (EmptyUtil.isEmpty(roleAuthorModel)){
            return Result.error("角色信息为空");
        }
        try{
            boolean flag = roleService.insertRoleAuthor(roleAuthorModel);
            if(flag == true){
                return Result.success("角色权限新增成功！");
            }
            else {
                return Result.error("角色权限新增失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"角色权限添加Exception");
        }
    }

    /*
     * @Author luck
     * @Param [RoleAuthorModel]
     * @date 2020/4/10
     * @description 角色权限信息修改*/
    @PostMapping("/updateRoleAuthor")
    @ResponseBody
    public Result updateRoleAuthor(@RequestBody AuthorModel authorModel){
        if (EmptyUtil.isEmpty(authorModel)){
            return Result.error("角色信息为空！");
        }
        if(EmptyUtil.isEmpty(authorModel.getAuthorId())||EmptyUtil.isEmpty(authorModel.getRoleId())){
            return Result.error("角色id或权限id为空！");
        }
        try{
            boolean flag = roleService.updateRoleAuthor(authorModel);
            if(flag == true){
                return Result.success("角色权限修改成功！");
            }
            else {
                return Result.error("角色权限修改失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"角色权限修改Exception");
        }
    }

    /*
     * @Author luck
     * @Param [RoleAuthorModel]
     * @date 2020/4/10
     * @description 角色权限信息查询*/
    @PostMapping("/selectRoleAuthor")
    @ResponseBody
    public Result selectRoleAuthor(@RequestBody RoleModel roleModel){
        if (EmptyUtil.isEmpty(roleModel)){
            return Result.error("角色信息为空");
        }
        try{
            return Result.success(roleService.selectRoleAuthor(roleModel));
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"角色权限查询Exception");
        }
    }

}
