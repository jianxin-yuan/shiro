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
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> roles = activeUser.getRoles();
        if (roles != null && roles.size() > 0) {
            info.addRoles(roles);
        }
        List<String> permissions = activeUser.getPermissions();
        if (permissions != null && permissions.size() > 0) {
            info.addStringPermissions(permissions);
        }

        return info;
    }


    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken ut = (UsernamePasswordToken) token;
        User user = userService.getUserByName(ut.getUsername());
        if (user != null) {
            List<Role> roleList = roleService.findByUserId(user.getId());
            List<String> roles = roleList != null ? roleList.stream().map(Role::getName).collect(Collectors.toList()) : null;
            List<Permission> permissionList = permissionService.findByUserId(user.getId());
            List<String> permissions = permissionList != null ? permissionList.stream().map(Permission::getCode).collect(Collectors.toList()) : null;
            ActiveUser activeUser = new ActiveUser(user, roles, permissions);
            ByteSource salt = ByteSource.Util.bytes(user.getSalt());
            return new SimpleAuthenticationInfo(activeUser, user.getPassword(), salt, this.getName());

        }
        return null;
    }
}
