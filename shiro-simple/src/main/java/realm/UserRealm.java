package realm;

import entity.ActiveUser;
import entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.PermissionService;
import service.PermissionServiceImpl;
import service.RoleService;
import service.RoleServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import java.util.List;

/**
 * @author yuan
 * @date 2019/10/6 10:40 下午
 * 自定义realm
 * 如果需要实现认证功能.则需要继承AuthenticatingRealm
 * 如果需要实现认证和授权.则需要继承AuthorizingRealm
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);


    private UserService userService = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    private PermissionService permissionService = new PermissionServiceImpl();

    private static final String SALT = "yuanjainxin";


    /**
     * 设置密码加密的算法和散列次数
     */
    public UserRealm() {
        //设置加密凭证
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //加密算法md5
        credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        //散列次数
        credentialsMatcher.setHashIterations(2);

        setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 授权方法,每次调用角色和权限相关验证方法时都会调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取doGetAuthenticationInfo存入的activeUser信息
        ActiveUser user = (ActiveUser) principals.getPrimaryPrincipal();
        //返回授权对象,将角色和权限加入
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            info.addRoles(user.getRoles());
        }
        if (!CollectionUtils.isEmpty(user.getPermissions())) {
            info.addStringPermissions(user.getPermissions());
        }

        return info;
    }

    /**
     * 认证方法,只有在未登录时才会调用一次
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.debug("自定义认证");
        //根据用户名查询数据库中的用户
        User user = userService.getUserByName(token.getPrincipal().toString());
        if (user != null) {
            //根据userId查询角色
            List<String> roles = roleService.findRolesByUser(user.getId());
            //根据userId查询权限
            List<String> permissions = permissionService.findPermissionByUser(user.getId());
            //将用户角色权限包装返回,这样可以避免后续授权过程时角色和角色的多次查询
            ActiveUser activeUser = new ActiveUser(user, roles, permissions);

            //返回authorizationInfo
            ByteSource salt = ByteSource.Util.bytes(SALT);
            /**
             * 参数说明:
             * activeUser:后续可拿到的principal对象
             * user.getPassword(): 数据库加密的密码
             * salt:加密算法的盐
             * this.getName(): 类名.随意
             */
            return new SimpleAuthenticationInfo(activeUser, user.getPassword(), salt, this.getName());
        }

        return null;
    }

    /**
     * md5散列算法
     *
     * @param source         元数据
     * @param salt           盐
     * @param hashIterations 散列次数
     * @return
     */
    public static String md5(Object source, Object salt, int hashIterations) {
        return new Md5Hash(source, salt, hashIterations).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("123456",SALT,2));
    }


}
