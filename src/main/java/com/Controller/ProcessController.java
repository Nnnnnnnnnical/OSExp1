package com.Controller;


import com.Common.Common;
import com.Entity.Process;
import com.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProcessController{

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

    @RequestMapping(value = "/process/HPF",method = RequestMethod.GET)
    public String HPF(){

        initTaskService.init_task();
        this.hpfService.init_task(Common.task_info,Common.task_num);
        this.hpfService.HRRN();
        return null;
    }

    @RequestMapping(value = "/process/RR",method = RequestMethod.GET)
    public String RR(){
       initTaskService.init_task();
       this.rrService.init_task(Common.task_info,Common.task_num);
       this.rrService.CircleTime();
       return null;
    }

    @RequestMapping(value = "/process/FCFS",method = RequestMethod.GET)
    public String FCFS(){
        List<Process> p = new ArrayList<>();
        this.fcfsService.init_task(p);
        this.fcfsService.FCFS(p);
        return null;
    }

    @RequestMapping(value = "/process/SJF",method = RequestMethod.GET)
    public String SJF(){
        List<Process> p = new ArrayList<>();
        this.sjfService.init_task(p);
        this.sjfService.SJF(p);
        return null;
    }
}
