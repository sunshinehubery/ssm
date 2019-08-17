package cn.sunshinehubery.ssm.dao;

import cn.sunshinehubery.ssm.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId = #{usersId})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = java.util.List.class,many = @Many(select = "cn.sunshinehubery.ssm.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findRolesByUsersId(String usersId)throws Exception;

    @Select("select * from role")
    List<Role> findAll()throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);
}
