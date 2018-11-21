package com.Service;

import com.Common.Common;
import com.Entity.Process;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 时间片轮转调度算法
 */
@Service
public class RRService {

    public void CircleTime(List<Process> processes) {
        int temp = 0;
        try {
            while (true){
                for (int i = 0; i < processes.size(); i++) {
                    System.out.print(Common.tm.format(new Date()) + "第" + processes.get(i).getPid() + "号进程开始运行==（R）==");
                    if (processes.get(i).getServiceTime() <= Common.Circle_size) {//如果能够在本时间片中运行完成
                        Thread.sleep((long) processes.get(i).getServiceTime() * 1000);//模拟运行所需时间
                        System.out.print(Common.tm.format(new Date()) + "结束执行（F）=====本次用时" + (int)processes.get(i).getServiceTime() + "S——————");

                        //获取间隔时间
                        double nowTime = System.nanoTime();
                        double time = (nowTime-processes.get(i).getStartTime())/1000000000;
                        processes.get(i).setWholeTime(time);
                        System.out.print("周转时间为："+processes.get(i).getWholeTime()+"S");
                        processes.get(i).setWeightWholeTime(processes.get(i).getWholeTime()/processes.get(i).getArrivalTime());
                        System.out.println("带权周转时间为："+processes.get(i).getWholeTime()/processes.get(i).getArrivalTime()+"S");
                        temp++;

                        //用来清除结束的进程
                        Process process = processes.get(i);
                        processes.remove(process);
                        i--;
                    } else {
                        Thread.sleep((long) Common.Circle_size * 1000);//模拟运行所需时间
                        System.out.println(Common.tm.format(new Date())+"本次时间片用完~~~~~进程就绪(W)");
                        processes.get(i).setServiceTime(processes.get(i).getServiceTime() - Common.Circle_size);
                    }
                }
                if (processes.size()==0){
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
