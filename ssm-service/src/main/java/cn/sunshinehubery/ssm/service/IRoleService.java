package cn.sunshinehubery.ssm.service;

import cn.sunshinehubery.ssm.pojo.Permission;
import cn.sunshinehubery.ssm.pojo.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll(int page,int size)throws Exception;

    void save(Role role)throws Exception;

    Role findById(String roleId)throws Exception;

    List<Permission> findOthersPermission(String roleId)throws Exception;

    void addPermissionToRole(String roleId, String[] ids)throws Exception;
}
