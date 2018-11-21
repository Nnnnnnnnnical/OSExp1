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
        List<Process> p = new ArrayList<>();
        initTaskService.init_task(p);
        this.hpfService.HRRN(p);
        return null;
    }

    @RequestMapping(value = "/process/RR",method = RequestMethod.GET)
    public String RR(){
        List<Process> p = new ArrayList<>();
        initTaskService.init_task(p);
        this.rrService.CircleTime(p);
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
        initTaskService.init_task(p);
        this.sjfService.SJF(p);
        return null;
    }
}
