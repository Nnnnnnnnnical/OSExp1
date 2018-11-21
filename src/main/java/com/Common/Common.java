package com.Common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Common {
    public static long time = (long) 0.0;
    public static List<double[]> task_info = new ArrayList<>();//进程信息列表
    public static int task_num = 8;//进程数
    public static String[] arr = new String[]{"1", "2", "3", "4", "5", "6", "7", "0"};
    public static SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");
    public static List<double[]> execute_time = new ArrayList<>();//进程周转时间列表
    public static int Circle_size=4;//定义时间片大小
    public static ArrayBlockingQueue task_q=new ArrayBlockingQueue(task_num);//进程队列
    public static Date[] wholeTime = new Date[Common.task_num];

    public static double getTimeDifference(String strTime1,String strTime2) {
        long l=0,day=0,hour=0,min=0,s=0;
        //格式日期格式，在此我用的是"2018-01-24 19:49:50"这种格式
        //可以更改为自己使用的格式，例如：HH:mm:ss
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        try {
            Date now = (Date) df.parseObject(strTime1);
            Date date = (Date) df.parseObject(strTime2);
            l = now.getTime() - date.getTime();       //获取时间差
            day = l / (24 * 60 * 60 * 1000);
            hour = (l / (60 * 60 * 1000) - day * 24);
            min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            s = (l / 1000 - day *    24 * 60 * 60 - hour * 60 * 60 - min * 60);
            //System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s+min*60+hour*3600+day*3600*24;
    }


}
