package com.example.demo0.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by DELL on 2017/10/9.
 */
public class DemoCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        Integer number=0;

        for (Integer i=0;i<100000;i++ ){
            number+=i;
        }

        System.out.println("call方法计算结果："+number);
        return number;
    }

    public static void main(String[] args) {
        DemoCallable demo=new DemoCallable();



        FutureTask<Integer> futureTask=new FutureTask<Integer>(demo);

        new Thread(futureTask).start();


        try {
            Integer count=futureTask.get();

            System.out.println("count的值："+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
