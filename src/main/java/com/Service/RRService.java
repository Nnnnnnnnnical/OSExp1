package com.Service;

import com.Common.Common;
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

    public void CircleTime()
    {
        try {
            while (true) {
                double[] t = new double[4];
                t = (double[]) Common.task_q.take();
                int current_task_time=(int)t[3];
                int task_NO=(int)t[0];
                System.out.print(Common.tm.format(new Date())+"第" +task_NO+"号进程开始运行==（R）==");
                if(current_task_time<=Common.Circle_size)//如果能够在本时间片中运行完成
                {
                    Thread.sleep((long) current_task_time*1000);//模拟运行所需时间
                    System.out.println(Common.tm.format(new Date())+"结束执行（F）=====本次用时"+current_task_time+"S");
                    double[] exe_t=new double[2];
                    exe_t[0]=task_NO;
                    exe_t[1]=System.currentTimeMillis()-t[1];//计算该进程所用的周转时间
                    Common.execute_time.add(exe_t);//加入到周转时间队列
                }
                else {//如果不能再本次时间片中运行完
                    t[3]=t[3]-Common.Circle_size;
                    Common.task_q.put(t);
                    Thread.sleep(Common.Circle_size*1000);
                    System.out.println(Common.tm.format(new Date())+"本次时间片用完~~~~~进程就绪(W)");
                }


                if(Common.task_q.size()==0)//如果进程队列为空了，就退出循环
                    break;


            }
        }
        catch (Exception e)
        {

        }
        show_time();//显示每个进程的调度时间

    }
    public static  void show_time()//显示每个进程的调度时间
    {
        double sum_time=0;
        for(int i=0;i<Common.execute_time.size();i++)
        {
            double[] t=Common.execute_time.get(i);
            System.out.println("task:"+t[0]+":周转时间="+(int)(t[1]/1000)+"S");
            sum_time+=t[1];
        }
        System.out.println("使用时间片轮转的策略，平均周转时间为："+(int)(sum_time/Common.execute_time.size()/1000)+"S");

    }

    public void init_task(List<double []> in,int tn)//初始化进程列表
    {
        Common.task_num=tn;
        for(int i=0;i<Common.task_num;i++)
        {
            double [] t=in.get(i);
            t[1] = System.currentTimeMillis();//获得进程到达时间
            try {
                Common.task_q.put(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
