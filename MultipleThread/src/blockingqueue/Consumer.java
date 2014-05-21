package blockingqueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {
    
    private ArrayBlockingQueue<String> queue;
    
    public Consumer(ArrayBlockingQueue<String> queue) {
        this.queue = queue;            
    }

    @Override
    public void run() {
    	
    	int i = 0;
        while(true){
        	
        
            try {
                this.queue.take();
                System.out.println("Number of products in the queue after consumption: " + this.queue.size());
            	i++;
            	if(i>10000){
            		Thread.sleep(Long.MAX_VALUE);
            	}
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


