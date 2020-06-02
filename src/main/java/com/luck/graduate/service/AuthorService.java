package com.luck.graduate.service;

import com.luck.graduate.entity.AuthorModel;
import com.luck.graduate.entity.RoleModel;

import java.util.List;

public interface AuthorService {
    public boolean insertAuthor(AuthorModel authorModel);

    public boolean deleteAuthor(AuthorModel authorModel);

    public boolean updateAuthor(AuthorModel authorModel);

    public List<AuthorModel> getAuthor(RoleModel roleModel);
}
