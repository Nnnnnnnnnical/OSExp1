package com.Service;

import com.Common.Common;
import com.Entity.Job;
import com.Param.Response.JobResponse;
import org.apache.tomcat.jni.Proc;
import org.springframework.stereotype.Service;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class InitTaskService {

    public void init_task(List<Job> Jobs, JobResponse jobResponses)//初始化进程列表
    {

        List<Job> temp = new ArrayList<>();
        for(int i = 0; i< Common.task_num; i++)
        {
            Common.time = System.nanoTime();
            Job Job = new Job();
            Job.setPid(i);
            Job.setStartTime((double) System.nanoTime());
            Job.setArrivalTime((Math.random()*100)%20+1);
            Job.setServiceTime((Math.random()*100)%20+1);

            temp.add(Job);
            Jobs.add(Job);
        }
        jobResponses.setJob(temp);
    }
}
