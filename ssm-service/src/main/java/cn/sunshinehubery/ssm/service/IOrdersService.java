package cn.sunshinehubery.ssm.service;

import cn.sunshinehubery.ssm.pojo.Orders;

import java.util.List;

public interface IOrdersService {
    public List<Orders> findAll()throws Exception;
    public List<Orders> findAllByPage(int page,int size)throws Exception;
    public Orders findById(String id)throws Exception;
}
