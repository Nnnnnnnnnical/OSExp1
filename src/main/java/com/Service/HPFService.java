package com.Service;

import com.Common.Common;
import com.Entity.Process;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *最高优先级算法
 */
@Service
public class HPFService {

    public void HRRN(List<Process> processes) {

        for (int i = 0; i < Common.task_num; i++) {
            get_ratio(processes);//每次循环时计算一次响应比
            Process tem = get_a_task(processes);//从进程列表中得到一个最高响应比的任务
            System.out.print(Common.tm.format(new Date()) + "第" + (int) tem.getPid()+ "号进程开始运行==(R)==");
            try {
                Thread.sleep((long) tem.getServiceTime() * 1000);//模拟进程执行所需要的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Common.tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) tem.getServiceTime() + "S");

        }

        show_time();//显示每个进程的周转时间

    }

    public static void show_time()//显示每个进程的周转时间
    {
        double sum_time = 0;
        for (int i = 0; i < Common.execute_time.size(); i++) {
            double[] t = Common.execute_time.get(i);
            System.out.println("task:" + t[0] + ":周转时间=" + (int) (t[1] / 1000) + "S");
            sum_time += t[1];
        }
        System.out.println("使用最高响应比的策略，平均周转时间为：" + (int) (sum_time / Common.execute_time.size() / 1000) + "S");

    }

    public static Process get_a_task(List<Process> processes)//根据响应比，返回一个最高相应比进程
    {
        Process process = new Process();
        int temp = 0;
        double maxRadio = 0;
        for(int i = 0;i<processes.size();i++){
            if(processes.get(i).getRadio()>maxRadio){
                maxRadio = processes.get(i).getRadio();
                process = processes.get(i);
                temp = i;
            }
        }
        System.out.print("进程号为："+processes.get(temp).getPid()+"的响应比最高，为："+processes.get(temp).getRadio()+"------");
        processes.remove(processes.get(temp));
        return process;

    }

    public static void get_ratio(List<Process> processes)//计算每一个进程当前的响应比
    {

        double[] radio = new double[processes.size()];
        for(int i =0;i<processes.size();i++){
            //响应比=（等待时间+服务时间）/服务时间
            radio[i] = ((System.currentTimeMillis()-processes.get(i).getStartTime())+processes.get(i).getServiceTime())/processes.get(i).getServiceTime();
            processes.get(i).setRadio(radio[i]);
        }
    }
}
