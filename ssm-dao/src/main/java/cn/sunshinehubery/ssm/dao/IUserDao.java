package cn.sunshinehubery.ssm.dao;

import cn.sunshinehubery.ssm.pojo.Role;
import cn.sunshinehubery.ssm.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "cn.sunshinehubery.ssm.dao.IRoleDao.findRolesByUsersId"))
    })
    UserInfo loginByUserName(String username)throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll()throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) " +
            "values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo)throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "cn.sunshinehubery.ssm.dao.IRoleDao.findRolesByUsersId"))
    })
    UserInfo findById(String id)throws Exception;

    @Select("select * from role where id not in(select roleId from users_role where userId = #{userId})")
    List<Role> findOthersRoles(String userId)throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
