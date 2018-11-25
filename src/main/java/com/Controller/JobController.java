package com.Controller;

import com.Common.Common;
import com.Entity.Job;
import com.Param.Response.JobResponse;
import com.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "job/{method}",method = RequestMethod.POST)
    public JobResponse Job(@PathVariable("method") String method){
        List<Job> p = new ArrayList<>();
        JobResponse jobResponses = new JobResponse();
        initTaskService.initTask(p,jobResponses);
        if(method.equals("HRRN")){
            //最高优先权算法
            return this.hpfService.method(p,jobResponses);
        }else if(method.equals("RR")){
            //时间片轮转调度算法
            return this.rrService.method(p,jobResponses);
        }else if(method.equals("FCFS")){
            //先来先服务算法
            return this.fcfsService.method(p,jobResponses);
        }else if(method.equals("SJF")){
            //短作业优先算法
            return this.sjfService.method(p,jobResponses);
        }else{
            return null;
        }
    }
}
