package com.Service;

import com.Common.Common;
import com.Entity.Job;
import com.Param.Response.JobResponse;
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

    public JobResponse HRRN(List<Job> Jobs, JobResponse jobResponses) {

        List<Job> temp = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < Common.task_num; i++,j++) {
            if(Jobs.size() == 0){
                break;
            }
            getRadio(Jobs,jobResponses);//每次循环时计算一次响应比
            Job tem = getTask(Jobs,jobResponses);//从进程列表中得到一个最高响应比的任务
            System.out.print(Common.tm.format(new Date()) + "第" + (int) tem.getPid()+ "号进程开始运行==(R)==");
//            try {
//                Thread.sleep((long) tem.getServiceTime() * 1000);//模拟进程执行所需要的时间
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.print(Common.tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) tem.getServiceTime() + "S-----");

            //获取间隔时间
            double nowTime = System.nanoTime();
            double time = (nowTime-tem.getStartTime())/1000000000;

            //求周转时间和带权周转时间
            tem.setWholeTime(time);
            System.out.print("周转时间为："+tem.getWholeTime()+"S  ");
            tem.setWeightWholeTime(tem.getWholeTime()/tem.getArrivalTime());
            System.out.println("带权周转时间为："+tem.getWholeTime()/tem.getArrivalTime()+"S");
            temp.add(tem);
            i--;

        }
        jobResponses.setJob(temp);
        return jobResponses;

    }

    public static Job getTask(List<Job> Jobs,JobResponse jobResponse)//根据响应比，返回一个最高相应比进程
    {
        Job Job = new Job();
        int temp = 0;
        double maxRadio = 0;
        for(int i = 0;i<Jobs.size();i++){
            if(Jobs.get(i).getRadio()>maxRadio){
                maxRadio = Jobs.get(i).getRadio();
                Job = Jobs.get(i);
                temp = i;
            }
        }
        System.out.print("进程号为："+Job.getPid()+"的响应比最高，为："+Job.getRadio()+"------");
        Jobs.remove(Jobs.get(temp));
        return Job;

    }

    public static void getRadio(List<Job> Jobs,JobResponse jobResponses)//计算每一个进程当前的响应比
    {

        double[] radio = new double[Jobs.size()];
        for(int i =0;i<Jobs.size();i++){
            //响应比=（等待时间+服务时间）/服务时间
            radio[i] = ((System.nanoTime()-Jobs.get(i).getStartTime())+Jobs.get(i).getServiceTime())/Jobs.get(i).getServiceTime();
            Jobs.get(i).setRadio(radio[i]);
            jobResponses.getJob().get(i).setRadio(radio[i]);

        }
    }
}
