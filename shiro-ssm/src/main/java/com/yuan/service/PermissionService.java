package com.yuan.service;

import com.yuan.model.Permission;

import java.util.List;

/**
 * @author yuan
 * @date 2019/10/13 7:16 下午
 */
public interface PermissionService {

    List<Permission> findByUserId(Integer userId);
}
