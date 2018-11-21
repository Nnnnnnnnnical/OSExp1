package com.Service;

import com.Common.Common;
import com.Entity.Process;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SJFService {
    //短作业优先算法
    public void SJF(List<Process> processe){

        for(int i = 0;i<Common.task_num-1;i++){
            for(int j = i+1;j<Common.task_num;j++){
                if(processe.get(i).getServiceTime()>processe.get(j).getServiceTime()){
                    double temp = processe.get(i).getServiceTime();
                    processe.get(i).setServiceTime(processe.get(j).getServiceTime());
                    processe.get(j).setServiceTime(temp);
                }
            }
        }

        for(int i = 0;i<Common.task_num;i++){
            System.out.print(Common.tm.format(new Date()) + "第" +  (int)processe.get(i).getPid() + "号进程开始运行==(R)==");
            try {
                Thread.sleep((long) processe.get(i).getServiceTime() * 1000);//模拟进程执行所需要的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
            System.out.println(Common.tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) processe.get(i).getServiceTime() + "s");
        }

    }


    public void init_task(List<Process> processes)//初始化进程列表
    {
        for(int i = 0; i< Common.task_num; i++)
        {
            Process process = new Process();
            process.setPid(i);
            process.setServiceTime((Math.random()*100)%20+1);
            processes.add(process);
        }
    }
}
