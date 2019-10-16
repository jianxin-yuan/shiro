package com.yuan.service;

import com.yuan.model.Role;

import java.util.List;

/**
 * @author yuan
 * @date 2019/10/13 7:14 下午
 */
public interface RoleService {
    List<Role> findByUserId(Integer userId);
}
