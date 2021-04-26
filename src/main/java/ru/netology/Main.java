package ru.netology;

public class Main {
    public static void main(String[] args) throws Exception {
        final CarFactory carFactory = new CarFactory();


        ThreadGroup buyers = new ThreadGroup("group1");
        new Thread(buyers, carFactory::sellAuto, "Первый покупатель").start();
        new Thread(buyers, carFactory::sellAuto, "Второй покупатель").start();
        new Thread(buyers, carFactory::sellAuto, "Третий покупатель").start();
        new Thread(buyers, carFactory::sellAuto, "Четвёртый покупатель").start();

        ThreadGroup makers = new ThreadGroup("group2");
        new Thread(makers, carFactory::createAuto, "Производитель Toyota").start();
    }
}
