package cn.sunshinehubery.ssm.service.impl;

import cn.sunshinehubery.ssm.dao.IRoleDao;
import cn.sunshinehubery.ssm.pojo.Permission;
import cn.sunshinehubery.ssm.pojo.Role;
import cn.sunshinehubery.ssm.service.IRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOthersPermission(String roleId) throws Exception {
        return roleDao.findOthersPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String permissionId:ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
