package cn.sunshinehubery.ssm.dao;

import cn.sunshinehubery.ssm.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findByRoleId(String roleId)throws Exception;
}
