package com.gz0101.hzwy.login.model;

public class VerifyCodeModel {
    private static volatile VerifyCodeModel instance;

    private VerifyCodeModel() {

    }

    public static VerifyCodeModel getInstance() {
        if (instance == null) {
            synchronized (VerifyCodeModel.class) {
                if (instance == null) {
                    instance = new VerifyCodeModel();
                }
            }
        }
        return instance;
    }

}
