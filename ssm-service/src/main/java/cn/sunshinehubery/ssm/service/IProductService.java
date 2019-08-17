package cn.sunshinehubery.ssm.service;

import cn.sunshinehubery.ssm.pojo.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll()throws Exception;
    public List<Product> findByPage(int page, int size)throws Exception;
    public void save(Product product)throws Exception;
}
