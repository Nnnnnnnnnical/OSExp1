package com.Service;

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
    private static SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");
    public static List<double[]> task_info = new ArrayList<>();//进程信息列表
    public static int task_num = 8;//进程数
    private static List<double[]> execute_time = new ArrayList<>();//进程周转时间列表


    public void HRRN() {

        for (int i = 0; i < task_num; i++) {
            get_ratio();//每次循环时计算一次响应比
            double[] tem = get_a_task();//从进程列表中得到一个最高响应比的任务

            System.out.print(tm.format(new Date()) + "第" + (int) tem[0] + "号进程开始运行==(R)==");
            try {
                Thread.sleep((long) tem[3] * 1000);//模拟进程执行所需要的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tm.format(new Date()) + "进程结束运行(F)=====用时为" + (int) tem[3] + "S");
            double[] exe_t = new double[2];
            exe_t[0] = tem[0];
            exe_t[1] = System.currentTimeMillis() - tem[1];
            execute_time.add(exe_t);

        }

        show_time();//显示每个进程的周转时间

    }

    public static void show_time()//显示每个进程的周转时间
    {
        double sum_time = 0;
        for (int i = 0; i < execute_time.size(); i++) {
            double[] t = execute_time.get(i);
            System.out.println("task:" + t[0] + ":周转时间=" + (int) (t[1] / 1000) + "S");
            sum_time += t[1];
        }
        System.out.println("使用最高响应比的策略，平均周转时间为：" + (int) (sum_time / execute_time.size() / 1000) + "S");

    }

    public static double[] get_a_task()//根据响应比，返回一个最高相应比进程
    {
        double[] rt = new double[4];
        double max_ratio = 0;
        int NO = -1;
        for (int i = 0; i < task_info.size(); i++) {
            if (task_info.get(i)[2] > max_ratio) {
                rt = task_info.get(i);
                max_ratio = task_info.get(i)[2];
                NO = i;
            }
        }
        task_info.remove(NO);//如果一个进程被选中，则在进程列表中删除掉
        return rt;


    }

    public void init_task(List<double[]> in, int tn)//初始化进程列表
    {

        for (int i = 0; i < in.size(); i++) {
            double[] t = in.get(i);
            t[1] = System.currentTimeMillis();//获得进程到达时间
            task_info.add(t);
        }
    }

    public static void get_ratio()//计算每一个进程当前的响应比
    {
        for (int i = 0; i < task_info.size(); i++) {
            double[] t = task_info.get(i);
            task_info.remove(i);
            double ratio = (System.currentTimeMillis() - t[1]) / t[3] + 1;//计算响应比
            t[2] = ratio;
            task_info.add(t);

        }

    }
}
