package blockingqueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerTest {

    public static void main(String[] args) throws InterruptedException {
        
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        
        Producer producer1 = new Producer("producer1", queue);
        Producer producer2 = new Producer("producer2", queue);
        Producer producer3 = new Producer("producer2", queue);
        
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);
        
        new Thread(producer1).start();
        new Thread(producer2).start();
        new Thread(producer3).start();
        
        Thread.sleep(1000*10);
        new Thread(consumer1).start();
//        new Thread(consumer2).start();
//        new Thread(consumer3).start();            
    }
}

