package com.gz0101.hzwy.baselibrary.base;

public class BaseEvent<T> {

    private String flag;
    private int event;
    private T t;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "flag='" + flag + '\'' +
                ", event=" + event +
                ", t=" + t +
                '}';
    }

}
