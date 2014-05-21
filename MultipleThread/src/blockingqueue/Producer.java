package blockingqueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {
	
    private ArrayBlockingQueue<String> queue;
    private String producerName;
    
    public Producer(String producerName, ArrayBlockingQueue<String> queue) {
        this.queue = queue;        
        this.producerName = producerName;
    }

    @Override
    public void run() {
        while(true){
            try {
                this.queue.put("Produced by " + this.producerName);
                System.out.println("producer: " + this.queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


