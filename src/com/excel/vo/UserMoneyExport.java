package com.excel.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class UserMoneyExport implements Comparable<UserMoneyExport>{

    private String money;
    private Integer count;
    private List<UserExport> userExportList;
    public static List<UserMoneyExport> userMoneys=new ArrayList<UserMoneyExport>();

    public UserMoneyExport() {
    }

    public UserMoneyExport(String money, Integer count, List<UserExport> userExportList) {
        this.money = money;
        this.count = count;
        this.userExportList = userExportList;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserExport> getUserExportList() {
        return userExportList;
    }

    public void setUserExportList(List<UserExport> userExportList) {
        this.userExportList = userExportList;
    }

    @Override
    public String toString() {
        return "UserMoneyExport{" +
                "money='" + money + '\'' +
                ", count=" + count +
                ", userExportList=" + userExportList +
                '}';
    }

    @Override
    public int compareTo(UserMoneyExport o) {
        if(this.getCount() < o.getCount()){
            return 1;
        }else if(this.getCount() == o.getCount()){
            return 0;
        }else{
            return -1;
        }
    }
}
