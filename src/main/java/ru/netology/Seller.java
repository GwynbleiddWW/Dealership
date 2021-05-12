package ru.netology;

public class Seller {

    private final int sleepTimeout = 1500;
    private final CarFactory carFactory;

    public Seller(CarFactory carFactory) {
        this.carFactory = carFactory;
    }

    public synchronized Auto sellAutos() {
           /*Человек заходит в магазин, продавец ему говорит, что машин нет.
            Человек - это поток. Машинет нет в салоне, и пока список  auto пуст, мы выдаём сообщение, что их нет.
            Поток останавливаем до уведомления от другого потока, что машина создана и добавлена в список auto.
             */
        try {
            Thread.sleep(sleepTimeout);
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (carFactory.getAuto().size() == 0) {
                Thread.sleep(sleepTimeout);
                System.out.println("Машин нет в наличии");
                wait();
            }
            Thread.sleep(sleepTimeout);
            /*Всё, авто добавлено в список, он не пуст - можно покупать-уезжать.
            Если использовать if, то машин никогда не будет потом.
            Если while, то бесконечный список.*/
            if (carFactory.getAuto().size() != 0) {
                Thread.sleep(sleepTimeout);
                System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
                /*wait(); Если здесь ожидаем, то не отрабатывает удаление из списка, если не будет wait(),
                 то будет бесконечный список.*/
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return carFactory.getAuto().remove(0); /*Здесь мы снова создаём ситуацию отсутствия авто, чтобы производитель снова начал выпускать авто.*/
    }

    public synchronized void makeAuto() {
        /*Здесь происходит добавлнение в список новой машины, если список пуст.*/
        try {
            //while (carFactory.getAuto().size() == 0) {
            /*Здесь и без цикла получается добавлять в список. Если его использовать то какое условие?*/
                Thread.sleep(sleepTimeout);
                carFactory.getAuto().add(new Auto(" \"Prototype X\" "));
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто:" + carFactory.getAuto().listIterator().next().name);
                notify();
            //} Выходит что у меня не получается сдеать так, чтобы один поток создания обрабатывал все запросы потоков-покупателей.

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
