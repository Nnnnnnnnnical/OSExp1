package com.Service;

import com.Common.Common;
import com.Entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 先来先执行算法
 */
@Service
public class FCFSService {

    @Autowired
    private InitTaskService initTaskService;

    public void init_task(List<Process> processes)//初始化进程列表
    {

        initTaskService.init_task(processes);
        //打乱1到8顺序
        List list = Arrays.asList(Common.arr);
        Collections.shuffle(list);
        System.out.println("进程号先后的顺序"+list);
        int i = 0;
        for(Process p : processes){
            if(i<Common.task_num){
                p.setPid(Integer.parseInt((String) list.get(i)));
                i++;
            }
        }
    }

    //先来先服务算法
    public void FCFS(List<Process> processes){

        Process[] process = new Process[Common.task_num];
        processes.toArray(process);

        for(int i = 0 ;i<Common.task_num;i++){
            System.out.print(Common.tm.format(new Date()) + "第" +  (int)processes.get(i).getPid() + "号进程开始运行==(R)==");
            try {
                Thread.sleep((long) processes.get(i).getServiceTime() * 1000);//模拟进程执行所需要的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(Common.tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) processes .get(i).getServiceTime() + "S-----");

            //获取间隔时间
            double nowTime = System.nanoTime();
            double time = (nowTime-processes.get(i).getStartTime())/1000000000;
            processes.get(i).setWholeTime(time);
            System.out.print("周转时间为："+processes.get(i).getWholeTime()+"S");
            processes.get(i).setWeightWholeTime(processes.get(i).getWholeTime()/processes.get(i).getArrivalTime());
            System.out.println("带权周转时间为："+processes.get(i).getWholeTime()/processes.get(i).getArrivalTime()+"S");

        }
    }

}
