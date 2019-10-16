package service;

import java.util.List;

/**
 * @author yuan
 * @date 2019/10/6 11:56 下午
 */
public interface PermissionService {
    List<String> findPermissionByUser(Integer userId);
}
