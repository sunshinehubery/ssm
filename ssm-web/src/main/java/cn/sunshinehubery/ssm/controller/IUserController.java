package cn.sunshinehubery.ssm.controller;

import cn.sunshinehubery.ssm.pojo.Role;
import cn.sunshinehubery.ssm.pojo.UserInfo;
import cn.sunshinehubery.ssm.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class IUserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "1")Integer pageSize) throws Exception {
        List<UserInfo> userInfoList  = userService.findAll(page,pageSize);
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = new PageInfo(userInfoList);
        mv.addObject("userList",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("save.do")
    @PreAuthorize("authentication.principal.username == 'sunshine'")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("findById.do")
    public ModelAndView findById(String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> roleList = userService.findOthersRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,
                                @RequestParam(name = "ids",required = true)String[] ids) throws Exception {
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }
}
