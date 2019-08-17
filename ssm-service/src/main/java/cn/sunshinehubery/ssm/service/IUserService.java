package cn.sunshinehubery.ssm.service;

import cn.sunshinehubery.ssm.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(int page,int size)throws Exception;

    void save(UserInfo userInfo)throws Exception;
}
