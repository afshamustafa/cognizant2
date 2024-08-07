package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    static class DataQueue {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity;

        public DataQueue(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void produce(int data) throws InterruptedException {
            while (queue.size() == capacity) {
                wait(); 
            }
            queue.add(data);
            System.out.println("Produced: " + data);
            notifyAll(); 
        }

        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait(); // Wait if the queue is empty
            }
            int data = queue.poll();
            System.out.println("Consumed: " + data);
            notifyAll(); 
            return data;
        }
    }

    static class Producer extends Thread {
        private final DataQueue queue;

        public Producer(DataQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) { 
                    queue.produce(i);
                    Thread.sleep((int) (Math.random() * 1000)); 
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread {
        private final DataQueue queue;

        public Consumer(DataQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) { 
                    queue.consume();
                    Thread.sleep((int) (Math.random() * 1500)); 
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DataQueue queue = new DataQueue(5); 

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All producers and consumers have completed.");
    }
}


