package cn.sunshinehubery.ssm.controller;

import cn.sunshinehubery.ssm.pojo.Product;
import cn.sunshinehubery.ssm.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class IProductController {
    @Autowired
    private IProductService productService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4")Integer pageSize)throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findByPage(page,pageSize);
        PageInfo pageInfo = new PageInfo(productList);
        mv.addObject("productList",pageInfo);
        mv.setViewName("product-list1");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(Product product)throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }
}
