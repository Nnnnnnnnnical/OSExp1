package com.Entity;

//进程的数据结构
public class Job{
    private double arrivalTime;//到达时间
    private double serviceTime;//服务时间
    private double finishTime;//结束时间
    private double startTime;//开始时间
    private double WholeTime;//周转时间
    private double weightWholeTime;//带权周转时间
    private int pid;//进程号
    private String status;//状态
    private double radio;//响应比

    public Job() {
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getWholeTime() {
        return WholeTime;
    }

    public void setWholeTime(double wholeTime) {
        WholeTime = wholeTime;
    }

    public double getWeightWholeTime() {
        return weightWholeTime;
    }

    public void setWeightWholeTime(double weightWholeTime) {
        this.weightWholeTime = weightWholeTime;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
}