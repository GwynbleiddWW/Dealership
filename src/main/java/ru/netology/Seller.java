package ru.netology;

public class Seller {

    private final CarFactory carFactory;

    public Seller(CarFactory carFactory) {
        this.carFactory = carFactory;
    }

    public synchronized Auto sellAutos() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (carFactory.getAuto().size() == 0) {
                Thread.sleep(2000);
                System.out.println("Машин нет в наличии");
                wait();
            }
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return carFactory.getAuto().remove(0);
    }

    public synchronized void makeAuto() {
        try {
            Thread.sleep(1000);
            carFactory.getAuto().add(new Auto(" \"Toyota Carina E\" "));
            System.out.println(Thread.currentThread().getName() + " выпустил 1 авто:" + carFactory.getAuto().listIterator().next().name);
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
