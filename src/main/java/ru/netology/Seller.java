package ru.netology;

public class Seller {

    private final int sleepTimeout = 1000;
    private final CarFactory carFactory;

    public Seller(CarFactory carFactory) {
        this.carFactory = carFactory;
    }

    public synchronized Auto sellAutos() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (carFactory.getAuto().size() == 0) {
                Thread.sleep(sleepTimeout);
                System.out.println("Машин нет в наличии");
                wait();
            }
            Thread.sleep(sleepTimeout);
            if (carFactory.getAuto().size() != 0) {
                System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return carFactory.getAuto().remove(0);
    }

    public synchronized void makeAuto() {
        try {
            if (carFactory.getAuto().size() == 0) {
                Thread.sleep(sleepTimeout);
                carFactory.getAuto().add(new Auto(" \"Toyota Carina E\" "));
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто:" + carFactory.getAuto().listIterator().next().name);
                notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
