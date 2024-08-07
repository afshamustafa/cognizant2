package threadstatelogger;

public class ThreadStateLogger {

    static class SimpleThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Number: " + i);
                try {
                    // Sleep for a very short duration
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SimpleThread thread = new SimpleThread();

        System.out.println("Thread state before starting: " + thread.getState());

        thread.start();

        System.out.println("Thread state after starting: " + thread.getState());

        while (thread.isAlive()) {
            System.out.println("Thread state during execution: " + thread.getState());

            Thread.sleep(5);
        }

        System.out.println("Thread state after completion: " + thread.getState());
    }
}



