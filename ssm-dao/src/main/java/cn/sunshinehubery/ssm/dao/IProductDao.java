package cn.sunshinehubery.ssm.dao;

import cn.sunshinehubery.ssm.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.access.method.P;

import java.util.List;

public interface IProductDao {
    @Select("select * from product")
    List<Product> findAll()throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product)throws Exception;

    @Select("select * from product where id = #{id}")
    Product findById(String id)throws Exception;
}
