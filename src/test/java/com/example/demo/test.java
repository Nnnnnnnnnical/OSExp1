package com.example.demo;

public class test {
    public static void main(String []args) throws InterruptedException {
        long t = System.currentTimeMillis();
        Thread.sleep(5000);
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t)/1000000000);

    }
}
