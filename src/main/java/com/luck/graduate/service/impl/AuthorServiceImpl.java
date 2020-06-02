package com.luck.graduate.service.impl;

import com.luck.graduate.dao.AuthorDao;
import com.luck.graduate.entity.AuthorModel;
import com.luck.graduate.entity.RoleModel;
import com.luck.graduate.service.AuthorService;
import com.luck.graduate.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDao authorDao;

    /*
     * @author luck
     * @Param [AuthorModel]
     * @date 2020/4/10
     * @description 添加权限信息*/
    public boolean insertAuthor(AuthorModel authorModel){
        authorModel.setCreateDate(DateUtil.getNowDate());
        if(authorDao.insertAuthor(authorModel)==true){
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * @author luck
     * @Param [AuthorModel]
     * @date 2020/4/10
     * @description 删除权限信息*/
    public boolean deleteAuthor(AuthorModel authorModel){
        if(authorDao.deleteAuthor(authorModel)==true){
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * @author luck
     * @Param [AuthorModel]
     * @date 2020/4/10
     * @description 修改权限信息*/
    public boolean updateAuthor(AuthorModel authorModel){
        authorModel.setUpdateDate(DateUtil.getNowDate());
        if(authorDao.updateAuthor(authorModel)==true){
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * @author luck
     * @Param [AuthorModel]
     * @date 2020/4/10
     * @description 查询权限列表*/
    public List<AuthorModel> getAuthor(RoleModel roleModel){
        return authorDao.getAuthor(roleModel);
    }
}
