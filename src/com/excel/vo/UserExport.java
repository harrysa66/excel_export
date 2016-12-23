package com.excel.vo;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class UserExport {

    private String userNo;//序号
    private String cardNo;//账号
    private String name;//账户名称
    private String cardType;//账户类型
    private String accountBank;//开户行行别
    private String accountProvince;//账户所在省
    private String accountCity;//账户所在市
    private String accountNetwork;//账户所属网点
    private String mobilePhone;//手机

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountProvince() {
        return accountProvince;
    }

    public void setAccountProvince(String accountProvince) {
        this.accountProvince = accountProvince;
    }

    public String getAccountCity() {
        return accountCity;
    }

    public void setAccountCity(String accountCity) {
        this.accountCity = accountCity;
    }

    public String getAccountNetwork() {
        return accountNetwork;
    }

    public void setAccountNetwork(String accountNetwork) {
        this.accountNetwork = accountNetwork;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString() {
        return "UserExport{" +
                "userNo='" + userNo + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", accountBank='" + accountBank + '\'' +
                ", accountProvince='" + accountProvince + '\'' +
                ", accountCity='" + accountCity + '\'' +
                ", accountNetwork='" + accountNetwork + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                '}';
    }
}
