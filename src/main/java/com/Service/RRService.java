package com.Service;

import com.Common.Common;
import com.Entity.Job;
import com.Param.Response.JobResponse;
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
public class RRService implements MethodService{


    @Override
    public JobResponse method(List<Job> Jobs, JobResponse jobResponses) {
        List<Job> temp = new ArrayList<>();
        int tem = 0;
        // try {
        while (true){
            for (int i = 0; i < Jobs.size(); i++) {
                System.out.print(Common.tm.format(new Date()) + "第" + Jobs.get(i).getPid() + "号进程开始运行==（R）==");
                if (Jobs.get(i).getServiceTime() <= Common.Circle_size) {//如果能够在本时间片中运行完成
                    //Thread.sleep((long) Jobs.get(i).getServiceTime() * 1000);//模拟运行所需时间
                    System.out.print(Common.tm.format(new Date()) + "结束执行（F）=====本次用时" + (int)Jobs.get(i).getServiceTime() + "S——————");

                    Job Job = Jobs.get(i);
                    //获取间隔时间
                    double nowTime = System.nanoTime();
                    double time = (nowTime-Jobs.get(i).getStartTime())/1000000000;
                    Jobs.get(i).setWholeTime(time);
                    System.out.print("周转时间为："+Jobs.get(i).getWholeTime()+"S");
                    Job.setWholeTime(Jobs.get(i).getWholeTime());
                    Jobs.get(i).setWeightWholeTime(Jobs.get(i).getWholeTime()/Jobs.get(i).getArrivalTime());
                    System.out.println("带权周转时间为："+Jobs.get(i).getWholeTime()/Jobs.get(i).getArrivalTime()+"S");
                    Job.setWeightWholeTime(Jobs.get(i).getWeightWholeTime());
                    tem++;

                    //用来清除结束的进程
                    temp.add(Job);
                    Jobs.remove(Job);
                    i--;
                } else {
                    //Thread.sleep((long) Common.Circle_size * 1000);//模拟运行所需时间
                    System.out.println(Common.tm.format(new Date())+"本次时间片用完~~~~~进程就绪(W)");
                    Jobs.get(i).setServiceTime(Jobs.get(i).getServiceTime() - Common.Circle_size);
                }
            }
            if (Jobs.size()==0){
                break;
            }
        }
        jobResponses.setJob(temp);
        return jobResponses;
    }//catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //}
}
