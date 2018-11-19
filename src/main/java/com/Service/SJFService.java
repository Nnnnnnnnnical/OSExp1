package com.Service;

import com.Common.Common;
import com.Entity.Process;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SJFService {

    public static List<List<Process>> task_info = new ArrayList<>();//进程信息列表
    private static SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");
    public static int task_num = 8;//进程数

    //短作业优先算法
    public void SJF(List<Process> processe){

        for(int i = 0;i<task_num-1;i++){
            for(int j = i+1;j<task_num;j++){
                if(processe.get(i).getServiceTime()>processe.get(j).getServiceTime()){
                    double temp = processe.get(i).getServiceTime();
                    processe.get(i).setServiceTime(processe.get(j).getServiceTime());
                    processe.get(j).setServiceTime(temp);
                }
            }
        }

        for(int i = 0;i<task_num;i++){
            System.out.print(tm.format(new Date()) + "第" +  (int)processe.get(i).getPid() + "号进程开始运行==(R)==");
            try {
                Thread.sleep((long) processe.get(i).getServiceTime() * 1000);//模拟进程执行所需要的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
            System.out.println(tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) processe.get(i).getServiceTime() + "s");
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
//
//        //当前时间
//        int now=0;
//        //待处理list
//        List<Process> list=new LinkedList<>();
//        //结果list
//        List<Process> res=new LinkedList<>();
//
//
//        //处理第一个进程
//        p[0].finishTime=p[0].arrivalTime+p[0].serviceTime;
//        p[0].WholeTime=p[0].finishTime-p[0].arrivalTime;
//        p[0].weightWholeTime=p[0].WholeTime/p[0].serviceTime;
//        res.add(p[0]);
//
//        now=p[0].finishTime;
//
//        //将剩余进程添加进待处理list
//        for(int i=1;i<p.length;i++){
//            list.add(p[i]);
//        }
//
//        while(list.size()!=0){
//            Process next=FindNextPro(list, now);
//            if(next.arrivalTime>now){
//                next.finishTime=next.arrivalTime+next.serviceTime;
//                next.startTime=next.arrivalTime;
//            }else{
//                next.finishTime=now+next.serviceTime;
//                next.startTime=now;
//            }
//            now=next.finishTime;
//            next.WholeTime=next.finishTime-next.arrivalTime;
//            next.weightWholeTime=(double)next.WholeTime/(double)next.serviceTime;
//            res.add(next);
//        }
//
//        return res.toArray(new Process[0]);
//
//    }
}
