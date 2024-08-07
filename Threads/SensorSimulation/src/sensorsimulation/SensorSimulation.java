package sensorsimulation;

import java.util.Random;

public class SensorSimulation {

    static class Sensor extends Thread {
        private final int sensorId;
        private final Random random;

        public Sensor(int sensorId) {
            this.sensorId = sensorId;
            this.random = new Random();
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) { 
                int data = random.nextInt(100);
                System.out.println("Sensor " + sensorId + " collected data: " + data);
                try {
                    Thread.sleep(200); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int numberOfSensors = 3; 
        Sensor[] sensors = new Sensor[numberOfSensors];

        for (int i = 0; i < numberOfSensors; i++) {
            sensors[i] = new Sensor(i);
            sensors[i].start();
        }

        for (Sensor sensor : sensors) {
            try {
                sensor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All sensor threads have completed.");
    }
}


