package com.gz0101.hzwy.baselibrary.db;

import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;
import com.gz0101.hzwy.baselibrary.base.BaseApplication;

public class DbHelp {

    private static volatile DbHelp instance;

    private DbHelp() {

    }

    public static DbHelp getInstance() {
        if (instance == null) {
            synchronized (DbHelp.class) {
                if (instance == null) {
                    instance = new DbHelp();
                }
            }
        }
        return instance;
    }

    private final static String DB_NAME = "hzwy_db";
    private DaoMaster.DevOpenHelper openHelper;
    private DaoSession readDaoSession;
    private DaoSession writDaoSession;

    public void initDb() {
        openHelper = new DaoMaster.DevOpenHelper(BaseApplication.getInstance(), DB_NAME, null);
        DaoMaster readDaoMaster = new DaoMaster(openHelper.getReadableDatabase());
        readDaoSession = readDaoMaster.newSession();
        DaoMaster writDaoMaster = new DaoMaster(openHelper.getWritableDatabase());
        writDaoSession = writDaoMaster.newSession();
    }

    public DaoSession getReadDaoSession() {
        return readDaoSession;
    }

    public DaoSession getWritDaoSession() {
        return writDaoSession;
    }

}
