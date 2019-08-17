package cn.sunshinehubery.ssm.service.impl;

import cn.sunshinehubery.ssm.dao.IProductDao;
import cn.sunshinehubery.ssm.pojo.Product;
import cn.sunshinehubery.ssm.service.IProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    @Override
    public List<Product> findByPage(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }


    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
