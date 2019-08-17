package cn.sunshinehubery.ssm.service.impl;

import cn.sunshinehubery.ssm.dao.IPermissionDao;
import cn.sunshinehubery.ssm.pojo.Permission;
import cn.sunshinehubery.ssm.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission)throws Exception {
        permissionDao.save(permission);
    }
}
