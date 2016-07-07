package com.bk.loancalculator.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Period implements Parcelable{
    private int periodNumber;
    private Date periodDate;
    private double interest;
    private double insurance;
    private double hocPremium;
    private double principaReduction;
    private double balance;
    private double paymentAmount;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(periodNumber);
        dest.writeDouble(interest);
        dest.writeDouble(paymentAmount);
        dest.writeDouble(principaReduction);
        dest.writeDouble(balance);

    }
    public Period(){

    }

    public Period(Parcel in) {
        this.periodNumber = in.readInt();
        this.interest= in.readDouble();
        this.paymentAmount = in.readDouble();
        this.principaReduction = in.readDouble();
        this.balance = in.readDouble();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Period createFromParcel(Parcel in) {
            return new Period(in);
        }

        public Period[] newArray(int size) {
            return new Period[size];
        }
    };

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(int periodNumber) {
        this.periodNumber = periodNumber;
    }

    public Date getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(Date periodDate)
    {
        this.periodDate = periodDate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public double getHocPremium() {
        return hocPremium;
    }

    public void setHocPremium(double hocPremium) {
        this.hocPremium = hocPremium;
    }

    public double getPrincipaReduction() {
        return principaReduction;
    }

    public void setPrincipaReduction(double principaReduction) {
        this.principaReduction = principaReduction;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Period{" +
                "periodNumber=" + periodNumber +
                ", periodDate=" + periodDate +
                ", interest=" + interest +
                ", insurance=" + insurance +
                ", hocPremium=" + hocPremium +
                ", principaReduction=" + principaReduction +
                ", balance=" + balance +
                ", paymentAmount=" + paymentAmount + "\n"+
                '}';
    }
}
