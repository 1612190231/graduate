package com.luck.graduate.dao;

import com.luck.graduate.entity.AuthorModel;
import com.luck.graduate.entity.RoleAuthorModel;
import com.luck.graduate.entity.RoleModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    public boolean insertRole(RoleModel roleModel);

    public boolean deleteRole(RoleModel roleModel);

    public boolean updateRole(RoleModel roleModel);

    public RoleModel selectRole(RoleModel roleModel);

    public List<RoleModel> getRole();

    public boolean insertRoleAuthor(List<RoleAuthorModel> roleAuthorModel);

    public boolean deleteRoleAuthor(RoleModel roleModel);

    public boolean updateRoleAuthor(AuthorModel AuthorModel);

    public List<RoleAuthorModel> selectRoleAuthor(RoleModel roleModel);
}
