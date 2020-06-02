package com.luck.graduate.dao;

import com.luck.graduate.entity.AuthorModel;
import com.luck.graduate.entity.RoleAuthorModel;
import com.luck.graduate.entity.RoleModel;
import com.luck.graduate.entity.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDao {
    public boolean insertAuthor(AuthorModel authorModel);

    public boolean deleteAuthor(AuthorModel authorModel);

    public boolean updateAuthor(AuthorModel authorModel);

    public List<AuthorModel> getAuthor(RoleModel roleModel);

    public List<RoleAuthorModel> getAll();
}
