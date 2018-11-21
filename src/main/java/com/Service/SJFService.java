package com.Service;

import com.Common.Common;
import com.Entity.Process;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SJFService {

    //短作业优先算法
    public void SJF(List<Process> processes){

        //按照服务时间长短升序排序所有进程
        Collections.sort(processes, (o1, o2) -> {
            int flag = Common.compare(o1, o2);
            return flag;
        });


        for(int i = 0;i<Common.task_num;i++){
            System.out.print(Common.tm.format(new Date()) + "第" +  (int)processes.get(i).getPid() + "号进程开始运行==(R)==");
            try {
                Thread.sleep((long) processes.get(i).getServiceTime() * 1000);//模拟进程执行所需要的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
            System.out.print(Common.tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) processes.get(i).getServiceTime() + "s-----");


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
