package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class CarFactory {
    Seller seller = new Seller(this);
    List<Auto> auto = new ArrayList<>(10);

    public Auto sellAuto() {
        return seller.sellAutos();
    }

    public void createAuto() {
            seller.makeAuto();
    }

    public List<Auto> getAuto() {
        return auto;
    }
}
