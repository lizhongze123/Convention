package com.gz0101.hzwy.hourglass.model;

public class HourglassSearchModel {
    private static volatile HourglassSearchModel instance;

    private HourglassSearchModel() {

    }

    public static HourglassSearchModel getInstance() {
        if (instance == null) {
            synchronized (HourglassSearchModel.class) {
                if (instance == null) {
                    instance = new HourglassSearchModel();
                }
            }
        }
        return instance;
    }


}
