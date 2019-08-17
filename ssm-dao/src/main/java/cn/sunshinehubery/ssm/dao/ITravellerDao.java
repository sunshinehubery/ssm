package cn.sunshinehubery.ssm.dao;

import cn.sunshinehubery.ssm.pojo.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{id})")
    List<Traveller> findByOrdersId(String id)throws Exception;
}
