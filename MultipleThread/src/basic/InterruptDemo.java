package basic;
class ATask implements Runnable{   
  
    private double d = 0.0;   
       
    public void run() {   
           
        //检查程序是否发生中断   
        while (!Thread.interrupted()) {   
            System.out.println("I am running!");   
  
            for (int i = 0; i < 900000; i++) {   
                d = d + (Math.PI + Math.E) / d;   
            }   
        }   
  
        System.out.println("ATask.run() interrupted!");   
    }   
}  
   
class BTask implements Runnable{   
	  
    private double d = 0.0;   
       
    public void run() {   
        //死循环执行打印"I am running!" 和做消耗时间的浮点计算   
        try {   
            while (true) {   
                System.out.println("I am running!");   
                   
                for (int i = 0; i < 900000; i++) {   
                    d =  d + (Math.PI + Math.E) / d;   
                }   
                //休眠一断时间,中断时会抛出InterruptedException   
                Thread.sleep(50);   
            }   
        } catch (InterruptedException e) {   
            System.out.println("ATask.run() interrupted!");   
        }   
    }   
}   


public class InterruptDemo {   
       
    public static void main(String[] args) throws Exception{   
        //将任务交给一个线程执行   
        //Thread t = new Thread(new ATask());   
    	Thread t = new Thread(new BTask());   
        t.start();   
           
        //运行一断时间中断线程   
        Thread.sleep(50);   
        System.out.println("****************************");   
        System.out.println("Interrupted Thread!");   
        System.out.println("****************************");   
        t.interrupt();   
    }   
} 
