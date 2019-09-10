package com.gz0101.hzwy.login.model;

public class CheckPhoneModel {
    private static volatile CheckPhoneModel instance;

    private CheckPhoneModel() {

    }

    public static CheckPhoneModel getInstance() {
        if (instance == null) {
            synchronized (CheckPhoneModel.class) {
                if (instance == null) {
                    instance = new CheckPhoneModel();
                }
            }
        }
        return instance;
    }

}
