package com.yuan.realm;

import com.yuan.dto.ActiveUser;
import com.yuan.model.Permission;
import com.yuan.model.Role;
import com.yuan.model.User;
import com.yuan.service.PermissionService;
import com.yuan.service.RoleService;
import com.yuan.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuan
 * @date 2019/10/13 6:41 下午
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 授权方法
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //拿到认证成功设置的principal信息
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //用户角色
        List<String> roles = activeUser.getRoles();
        if (roles != null && roles.size() > 0) {
            info.addRoles(roles);
        }
        //用户权限
        List<String> permissions = activeUser.getPermissions();
        if (permissions != null && permissions.size() > 0) {
            info.addStringPermissions(permissions);
        }

        return info;
    }


    /**
     * 认证方法
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken ut = (UsernamePasswordToken) token;
        //根据用户名查询用户
        User user = userService.getUserByName(ut.getUsername());
        if (user != null) {
            //根据用户ID查询角色列表
            List<Role> roleList = roleService.findByUserId(user.getId());
            List<String> roles = roleList != null ? roleList.stream().map(Role::getName).collect(Collectors.toList()) : null;
            //根据用户ID查询权限列表
            List<Permission> permissionList = permissionService.findByUserId(user.getId());
            List<String> permissions = permissionList != null ? permissionList.stream().map(Permission::getCode).collect(Collectors.toList()) : null;
            //构建一个包含了用户信息 & 角色信息 & 权限信息 的实体
            ActiveUser activeUser = new ActiveUser(user, roles, permissions);
            //加密盐值
            ByteSource salt = ByteSource.Util.bytes(user.getSalt());
            /**
             * 参数说明
             * 参数1:活动的用户.用于后续授权
             * 参数2:加密的密码
             * 参数3:加密的盐值
             * 参数4:当前realm类名.不重要
             */
            return new SimpleAuthenticationInfo(activeUser, user.getPassword(), salt, this.getName());

        }
        return null;
    }
}
