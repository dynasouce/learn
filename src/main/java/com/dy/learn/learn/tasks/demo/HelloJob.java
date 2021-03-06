package com.dy.learn.learn.tasks.demo;

import com.dy.learn.learn.tasks.AbstractJob;
import com.dy.learn.learn.service.TaskService;
import com.dy.learn.learn.service.UserInfoService;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloJob extends AbstractJob {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public void run(JobExecutionContext jobExecutionContext){

        LOGGER.info("getTaskService().getTaskById(1).getTaskName():" + getTaskService().getTaskById(1).getTaskName());

        LOGGER.info("userInfoService info:" + userInfoService.getUserInfoById(1));

    }

}
