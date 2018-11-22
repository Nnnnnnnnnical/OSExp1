package com.Common;

import com.Entity.Job;
import com.Param.Response.JobResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Common {
    public static long time = (long) 0.0;
    public static int task_num = 8;//进程数
    public static String[] arr = new String[]{"1", "2", "3", "4", "5", "6", "7", "0"};
    public static SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");
    public static int Circle_size = 4;//定义时间片大小

    public static int compareJob(Object Object1, Object Object2) {
        Job s1 = (Job)Object1;
        Job s2 = (Job)Object2;
        double serviceTime1 = s1.getServiceTime();
        double serviceTime2 = s2.getServiceTime();
        if (serviceTime1 < serviceTime2) {
            return -1;
        } else if (serviceTime1 == serviceTime2) {
            return 0;
        } else {
            return 1;
        }
    }
}
