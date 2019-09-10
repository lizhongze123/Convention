package com.gz0101.hzwy.main.model;

public class MainModel {

    private static volatile MainModel instance;

    private MainModel() {
    }

    public static MainModel getInstance() {
        if (instance == null) {
            synchronized (MainModel.class) {
                if (instance == null) {
                    instance = new MainModel();
                }
            }
        }
        return instance;
    }
}
