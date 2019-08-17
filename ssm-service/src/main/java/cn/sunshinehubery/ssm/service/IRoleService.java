package cn.sunshinehubery.ssm.service;

import cn.sunshinehubery.ssm.pojo.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll(int page,int size)throws Exception;

    void save(Role role)throws Exception;
}
