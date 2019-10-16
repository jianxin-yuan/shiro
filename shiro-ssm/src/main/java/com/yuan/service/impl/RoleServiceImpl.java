package com.yuan.service.impl;

import com.yuan.mapper.RoleMapper;
import com.yuan.model.Role;
import com.yuan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuan
 * @date 2019/10/13 7:17 下午
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findByUserId(Integer userId) {
        return roleMapper.findRoleByUserId(userId);
    }
}
