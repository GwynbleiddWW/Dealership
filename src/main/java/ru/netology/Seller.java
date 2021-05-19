package ru.netology;

public class Seller {
    private final int timeout = 2000;
    private final CarFactory carFactory;

    public Seller(CarFactory carFactory) {
        this.carFactory = carFactory;
    }

    public synchronized Auto sellAutos() {
        System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
        if (carFactory.getAuto().isEmpty()) {
            System.out.println("Машин нет в наличии");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        return carFactory.getAuto().remove(0);
    }

    public synchronized void makeAuto() {
        Auto auto = new Auto(" \"Prototype X\" ");
        int amountOfAuto = 10;
        if (carFactory.getAuto().size() < amountOfAuto) {
            carFactory.getAuto().add(auto);
            notify();
        }
        System.out.println(Thread.currentThread().getName() + " выпустил 1 авто:" + auto.name);
    }
}
