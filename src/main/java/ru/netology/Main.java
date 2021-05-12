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
        new Thread(makers, carFactory::createAuto, "Производитель Subaru").start();
        new Thread(makers, carFactory::createAuto, "Производитель Nissan").start();
        new Thread(makers, carFactory::createAuto, "Производитель BMW").start();

        /*Если создать равное количество потоков покупателей-создателей и убрать цикл, то программа худо-бедно работает.
        Но это опять же абсолютно не то.
        Вопрос: как сделать так, чтобы авто выпускались постоянно и при этом только только при пустом магазине?*/
    }
}
