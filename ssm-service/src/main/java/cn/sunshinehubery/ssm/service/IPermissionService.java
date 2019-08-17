package cn.sunshinehubery.ssm.service;

import cn.sunshinehubery.ssm.pojo.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll(int page,int size)throws Exception;

    void save(Permission permission)throws Exception;
}
