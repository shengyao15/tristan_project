package basic;
class ATask implements Runnable{   
  
    private double d = 0.0;   
       
    public void run() {   
           
        //�������Ƿ����ж�   
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
        //��ѭ��ִ�д�ӡ"I am running!" ��������ʱ��ĸ������   
        try {   
            while (true) {   
                System.out.println("I am running!");   
                   
                for (int i = 0; i < 900000; i++) {   
                    d =  d + (Math.PI + Math.E) / d;   
                }   
                //����һ��ʱ��,�ж�ʱ���׳�InterruptedException   
                Thread.sleep(50);   
            }   
        } catch (InterruptedException e) {   
            System.out.println("ATask.run() interrupted!");   
        }   
    }   
}   


public class InterruptDemo {   
       
    public static void main(String[] args) throws Exception{   
        //�����񽻸�һ���߳�ִ��   
        //Thread t = new Thread(new ATask());   
    	Thread t = new Thread(new BTask());   
        t.start();   
           
        //����һ��ʱ���ж��߳�   
        Thread.sleep(50);   
        System.out.println("****************************");   
        System.out.println("Interrupted Thread!");   
        System.out.println("****************************");   
        t.interrupt();   
    }   
} 
