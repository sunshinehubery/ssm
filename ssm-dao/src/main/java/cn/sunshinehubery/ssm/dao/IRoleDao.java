package cn.sunshinehubery.ssm.dao;

import cn.sunshinehubery.ssm.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId = #{usersId})")
    List<Role> findRolesByUsersId(String usersId)throws Exception;
}
