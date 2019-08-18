package cn.sunshinehubery.ssm.service;

import cn.sunshinehubery.ssm.pojo.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLog)throws Exception;

    List<SysLog> findAll(int page,int size)throws Exception;
}
