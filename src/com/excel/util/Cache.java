package com.excel.util;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class Cache {
    private String key;//缓存ID
    private Object value;//缓存数据
    private long timeOut;//更新时间
    private boolean expired; //是否终止
    public Cache() {
        super();
    }

    public Cache(String key, Object value, long timeOut, boolean expired) {
        this.key = key;
        this.value = value;
        this.timeOut = timeOut;
        this.expired = expired;
    }

    public Cache(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public Object getValue() {
        return value;
    }

    public void setKey(String string) {
        key = string;
    }

    public void setTimeOut(long l) {
        timeOut = l;
    }

    public void setValue(Object object) {
        value = object;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean b) {
        expired = b;
    }

    @Override
    public String toString() {
        return "Cache{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", timeOut=" + timeOut +
                ", expired=" + expired +
                '}';
    }
}
