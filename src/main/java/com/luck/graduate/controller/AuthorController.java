package com.luck.graduate.controller;

import com.luck.graduate.entity.AuthorModel;
import com.luck.graduate.entity.RoleModel;
import com.luck.graduate.service.AuthorService;
import com.luck.graduate.utils.EmptyUtil;
import com.luck.graduate.utils.MyException;
import com.luck.graduate.utils.result.Result;
import com.luck.graduate.utils.result.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@CrossOrigin
public class AuthorController {
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private AuthorService authorService;

    /*
     * @Author luck
     * @Param [AuthorModel]
     * @date 2020/4/10
     * @description 权限信息添加*/
    @PostMapping("/insertAuthor")
    @ResponseBody
    public Result insertAuthor(AuthorModel authorModel){
        if (EmptyUtil.isEmpty(authorModel)){
            return Result.error("权限信息为空");
        }
        if (EmptyUtil.isEmpty(authorModel.getAuthorName())){
            return  Result.error("权限名为空");
        }
        try{
            boolean flag = authorService.insertAuthor(authorModel);
            if(flag == true){
                return Result.success("权限新增成功！");
            }
            else {
                return Result.error("权限新增失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"权限添加Exception");
        }
    }

    /*
     * @Author luck
     * @Param [AuthorModel]
     * @date 2020/4/10
     * @description 权限信息删除*/
    @PostMapping("/deleteAuthor")
    @ResponseBody
    public Result deleteAuthor(AuthorModel authorModel){
        if(EmptyUtil.isEmpty(authorModel)){
            return Result.error("权限信息为空！");
        }
        try {
            boolean flag = authorService.deleteAuthor(authorModel);
            if(flag == true){
                return Result.success("权限删除成功！");
            }
            else {
                return Result.error("权限删除失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"权限删除Exception");
        }
    }

    /*
     * @Author luck
     * @Param AuthorModel
     * @date 2020/4/10
     * @description 权限信息修改*/
    @PostMapping("/updateAuthor")
    @ResponseBody
    public Result updateAuthor(@RequestBody AuthorModel authorModel){
        if(EmptyUtil.isEmpty(authorModel)){
            return Result.error("权限信息为空！");
        }
        try {
            boolean flag = authorService.updateAuthor(authorModel);
            if(flag == true){
                return Result.success("权限修改成功！");
            }
            else {
                return Result.error("权限修改失败！");
            }
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"权限修改Exception");
        }
    }

    /*
     * @Author luck
     * @Param null
     * @date 2020/4/10
     * @description 查询权限列表*/
    @GetMapping("/getAuthor")
    @ResponseBody
    public Result getAuthor(@RequestBody RoleModel roleModel){
        try {
            return Result.success(authorService.getAuthor(roleModel));
        }catch(MyException e){
            return Result.error(e.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(),"权限修改Exception");
        }
    }
}
