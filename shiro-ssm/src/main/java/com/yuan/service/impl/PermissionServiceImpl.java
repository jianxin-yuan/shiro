package com.yuan.service.impl;

import com.yuan.mapper.PermissionMapper;
import com.yuan.model.Permission;
import com.yuan.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuan
 * @date 2019/10/13 7:18 下午
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByUserId(Integer userId) {
        return permissionMapper.findByUserId(userId);
    }
}
