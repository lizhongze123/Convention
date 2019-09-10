package com.gz0101.hzwy.baselibrary.db.dao;

import com.greendao.gen.DaoSession;
import com.greendao.gen.GoodsEntityDao;
import com.gz0101.hzwy.baselibrary.db.DbHelp;
import com.gz0101.hzwy.baselibrary.db.entity.GoodsEntity;

import java.util.List;

public class GoodsDao {

    public static Long saveMessage(GoodsEntity entity) {
        DaoSession writDaoSession = DbHelp.getInstance().getWritDaoSession();
        GoodsEntityDao dao = writDaoSession.getGoodsEntityDao();
        return dao.insert(entity);
    }

    public static void update(GoodsEntity entity) {
        DaoSession writDaoSession = DbHelp.getInstance().getWritDaoSession();
        GoodsEntityDao dao = writDaoSession.getGoodsEntityDao();
        dao.update(entity);
    }


    public static List<GoodsEntity> queryRecord(int shopId) {
        DaoSession readDaoSession = DbHelp.getInstance().getReadDaoSession();
        GoodsEntityDao dao = readDaoSession.getGoodsEntityDao();
        List<GoodsEntity> list = dao.queryBuilder()
                .where(GoodsEntityDao.Properties.ShopId.eq(shopId))
                .list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    public static void clear() {
        DaoSession writDaoSession = DbHelp.getInstance().getWritDaoSession();
        GoodsEntityDao dao = writDaoSession.getGoodsEntityDao();
        List<GoodsEntity> entities = dao.queryBuilder()
                .list();
        if (null != entities) {
            for (int i = 0; i < entities.size(); i++) {
                GoodsEntity entity = entities.get(i);
                if (null != entity)
                    dao.deleteByKey(entity.getId());
            }
        }
    }
}
