package cn.sunshinehubery.ssm.dao;

import cn.sunshinehubery.ssm.pojo.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {
    @Select("select * from member where id = #{id}")
    Member findById(String Id)throws Exception;
}
