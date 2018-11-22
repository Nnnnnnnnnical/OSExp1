package com.Service;

import com.Common.Common;
import com.Entity.Job;
import com.Param.Response.JobResponse;
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

    public void initTask(List<Job> Jobs,JobResponse jobResponses)//初始化进程列表
    {

        initTaskService.initTask(Jobs,jobResponses);
        //打乱1到8顺序
        List list = Arrays.asList(Common.arr);
        Collections.shuffle(list);
        System.out.println("进程号先后的顺序"+list);
        jobResponses.setList(list);
        int i = 0;
        for(Job p : Jobs){
            if(i<Common.task_num){
                p.setPid(Integer.parseInt((String) list.get(i)));
                jobResponses.getJob().get(i).setPid(p.getPid());
                i++;
            }
        }
    }

    //先来先服务算法
    public JobResponse FCFS(List<Job> Jobs,JobResponse jobResponses){

        Job[] Job = new Job[Common.task_num];
        Jobs.toArray(Job);

        for(int i = 0 ;i<Common.task_num;i++){
            System.out.print(Common.tm.format(new Date()) + "第" +  (int)Jobs.get(i).getPid() + "号进程开始运行==(R)==");
//            try {
//                Thread.sleep((long) Jobs.get(i).getServiceTime() * 1000);//模拟进程执行所需要的时间
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.print(Common.tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) Jobs .get(i).getServiceTime() + "S-----");

            //获取间隔时间
            double nowTime = System.nanoTime();
            double time = (nowTime-Jobs.get(i).getStartTime())/1000000000;
            Jobs.get(i).setWholeTime(time);
            jobResponses.getJob().get(i).setWholeTime(time);
            System.out.print("周转时间为："+Jobs.get(i).getWholeTime()+"S");
            Jobs.get(i).setWeightWholeTime(Jobs.get(i).getWholeTime()/Jobs.get(i).getArrivalTime());
            jobResponses.getJob().get(i).setWeightWholeTime(Jobs.get(i).getWholeTime()/Jobs.get(i).getArrivalTime());
            System.out.println("带权周转时间为："+Jobs.get(i).getWholeTime()/Jobs.get(i).getArrivalTime()+"S");

        }
        return jobResponses;
    }

}
