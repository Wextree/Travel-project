package com.service.impl;

import com.dao.SysLogDao;
import com.domain.SysLog;
import com.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void saveLog(SysLog sysLog) {
        sysLogDao.saveLog(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
