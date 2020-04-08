package com.service;

import com.domain.SysLog;

import java.util.List;

public interface SysLogService {
    /**
     * 保存日志信息
     * @param sysLog
     */
    void saveLog(SysLog sysLog);

    /**
     * 查询所有日志信息
     * @return
     */
    List<SysLog> findAll();
}
