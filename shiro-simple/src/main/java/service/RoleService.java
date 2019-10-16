package service;

import java.util.List;

/**
 * @author yuan
 * @date 2019/10/6 11:32 下午
 */
public interface RoleService {
    List<String> findRolesByUser(Integer userId);
}
