package com.Service;

import com.Common.Common;
import com.Entity.Process;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class InitTaskService {

    public void init_task(List<Process> processes)//初始化进程列表
    {

        for(int i = 0; i< Common.task_num; i++)
        {
            Common.time = System.nanoTime();
            Process process = new Process();
            process.setPid(i);
            process.setStartTime((double) System.nanoTime());
            process.setArrivalTime((Math.random()*100)%20+1);
            process.setServiceTime((Math.random()*100)%20+1);
            processes.add(process);
        }
    }
}
