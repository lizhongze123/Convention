package com.gz0101.hzwy.baselibrary.cache;


import android.content.Context;
import android.content.SharedPreferences;


import com.gz0101.hzwy.baselibrary.base.BaseApplication;

import java.util.Map;

public class SpHelp {

    private static volatile SpHelp instance;

    private SpHelp() {

    }

    public static SpHelp getInstance() {
        if (instance == null) {
            synchronized (SpHelp.class) {
                if (instance == null) {
                    instance = new SpHelp();
                }
            }
        }
        return instance;
    }

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final String tag = "share_preference_cache";

    public void init() {
        sp = BaseApplication.getInstance().getSharedPreferences(tag, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void save(String key, Object object) {
        if (editor == null) {
            return;
        }
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        String value = defaultValue;
        if (sp != null) {
            value = sp.getString(key, defaultValue);
        }
        return value;
    }

    public int getInt(String key) {
        return getInt(key, -1);
    }

    public int getInt(String key, int defaultValue) {
        int value = defaultValue;
        if (sp != null) {
            value = sp.getInt(key, defaultValue);
        }
        return value;
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (sp != null) {
            value = sp.getBoolean(key, defaultValue);
        }
        return value;
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        long value = defaultValue;
        if (sp != null) {
            value = sp.getLong(key, 0);
        }
        return value;
    }

    public void remove(String key) {
        if (editor == null) {
            return;
        }
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        if (editor == null) {
            return;
        }
        editor.clear();
        editor.commit();
    }

    public Boolean contain(String key) {
        if (sp == null) {
            return false;
        }
        return sp.contains(key);
    }

    public Map<String, ?> getAll() {
        if (sp == null) {
            return null;
        }
        return sp.getAll();
    }

}
