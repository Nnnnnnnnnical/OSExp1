package com.Common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Common {
    public static List<double[]> task_info = new ArrayList<>();//进程信息列表
    public static int task_num = 8;//进程数
    public static String[] arr = new String[]{"1", "2", "3", "4", "5", "6", "7", "0"};
    public static SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");
    public static List<double[]> execute_time = new ArrayList<>();//进程周转时间列表
    public static int Circle_size=4;//定义时间片大小
    public  static ArrayBlockingQueue task_q=new ArrayBlockingQueue(task_num);//进程队列


}
