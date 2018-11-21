package com.Service;

import com.Common.Common;
import com.Entity.Process;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 先来先执行算法
 */
@Service
public class FCFSService {

    public void init_task(List<Process> processes)//初始化进程列表
    {
        for(int i = 0; i< Common.task_num; i++)
        {
            Process process = new Process();
            processes.add(process);
        }

        //打乱1到8顺序
        List list = Arrays.asList(Common.arr);
        Collections.shuffle(list);
        System.out.println(list);
        int i = 0;
        for(Process p : processes){
            if(i<Common.task_num){
                p.setPid(Integer.parseInt((String) list.get(i)));
                p.setStatus("W");
                p.setServiceTime((Math.random()*100)%20+1);
                i++;
            }
        }
    }

    //先来先服务算法
    public void FCFS(List<Process> p){

        Process[] process = new Process[Common.task_num];
        p.toArray(process);

        for(int i = 0 ;i<Common.task_num;i++){
            System.out.print(Common.tm.format(new Date()) + "第" +  (int)p.get(i).getPid() + "号进程开始运行==(R)==");
            try {
                Thread.sleep((long) p.get(i).getServiceTime() * 1000);//模拟进程执行所需要的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Common.tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) p.get(i).getServiceTime() + "S");

        }
        show_time(p);
    }

    //输出
    public static void show_time(List<Process> p){
//        DecimalFormat df = new DecimalFormat("#.00");
//        float sumWT=0;
//        float sumWWT=0;
//        float AverageWT;
//        float AverageWWT;
//        for(int i=0;i<p.size();i++){
//            System.out.println("时刻"+p.get(i).startTime+": 进程"+p.get(i).pid+"开始运行，完成时间为:"+p.get(i).finishTime);
//        }

    }
}
