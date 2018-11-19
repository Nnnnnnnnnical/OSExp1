package com.Service;

import com.Common.Common;
import org.springframework.stereotype.Service;

@Service
public class InitTaskService {

    public void init_task()//初始化进程列表
    {
        for(int i = 0; i< Common.task_num; i++)
        {
            double[] t=new double[4];
            t[0]=i;//进程号
            t[1]=0;//到达时间
            t[2]=0;//响应比
            t[3]=(int)(Math.random()*100)%20+1;//需要运行时间
            Common.task_info.add(t);
        }
    }
}
