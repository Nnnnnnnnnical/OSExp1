package com.Controller;

import com.Common.Common;
import com.Entity.Job;
import com.Param.Response.JobResponse;
import com.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController{

    @Autowired
    private HPFService hpfService;

    @Autowired
    private RRService rrService;

    @Autowired
    private FCFSService fcfsService;

    @Autowired
    private SJFService sjfService;

    @Autowired
    private InitTaskService initTaskService;

    /**
     * 最高优先权算法
     * @return
     */
    @RequestMapping(value = "/Job/HPF",method = RequestMethod.GET)
    public JobResponse HPF(){
        List<Job> p = new ArrayList<>();
        JobResponse jobResponses = new JobResponse();
        initTaskService.initTask(p,jobResponses);
        return this.hpfService.HRRN(p,jobResponses);
    }

    /**
     * 时间片轮转调度算法
     * @return
     */
    @RequestMapping(value = "/Job/RR",method = RequestMethod.GET)
    public JobResponse RR(){
        List<Job> p = new ArrayList<>();
        JobResponse jobResponses = new JobResponse();
        initTaskService.initTask(p,jobResponses);
        return this.rrService.CircleTime(p,jobResponses);
    }

    /**
     * 先来先服务算法
     * @return
     */
    @RequestMapping(value = "/Job/FCFS",method = RequestMethod.GET)
    public JobResponse FCFS(){
        List<Job> p = new ArrayList<>();
        JobResponse jobResponses = new JobResponse();
        this.fcfsService.initTask(p,jobResponses);
        return this.fcfsService.FCFS(p,jobResponses);
    }

    /**
     * 短作业优先算法
     * @return
     */
    @RequestMapping(value = "/Job/SJF",method = RequestMethod.GET)
    public JobResponse SJF(){
        List<Job> p = new ArrayList<>();
        JobResponse jobResponses = new JobResponse();
        initTaskService.initTask(p,jobResponses);
        return this.sjfService.SJF(p,jobResponses);
    }
}
