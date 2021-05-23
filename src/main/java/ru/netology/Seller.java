package ru.netology;

public class Seller {
    private final int timeout = 2000;
    private final CarFactory carFactory;

    public Seller(CarFactory carFactory) {
        this.carFactory = carFactory;
    }

    public synchronized Auto sellAutos() {
        try {
            Thread.sleep(timeout);
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (carFactory.getAuto().isEmpty()) {
                Thread.sleep(timeout);
                System.out.println("Машин нет в наличии");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        return carFactory.getAuto().remove(0);
    }

    public synchronized void makeAuto() {
        try {
            Auto auto = new Auto(" \"Prototype X\" ");
            int amountOfAuto = 10;
            if (carFactory.getAuto().size() < amountOfAuto) {
                for (int i = 0; i < 10; i++) {
                    carFactory.getAuto().add(auto);
                }
            }
                Thread.sleep(timeout);
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто:" + auto.name);
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
