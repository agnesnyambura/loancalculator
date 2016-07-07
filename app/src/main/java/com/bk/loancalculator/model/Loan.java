package com.bk.loancalculator.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Loan {
    private int numYears;
    private int type;
    private int repaymentsPerYear;
    private double loanPurchasePrice;
    private double collateralFee;
    private double establishmentFee;
    private double interestRate;
    private Date dateOfLoan;
    private Date startDate;
    private double lifePremium;
    private double hocPremium;
    private double loanTotalAmount;
    private double requetsedLoan;
    private double rateIncLife;
    private double establishmentRate;
    private double collateralRate;
    private double collateralValue;
    private double legalFee;
    private double legalLoanAmount;
    private double valuationAmount;
    private double exiMonPay;
    private double grossIncome;


    public Loan(){

    }

    public Loan(int numYears, int type, int repaymentsPerYear, double requetsedLoan, double interestRate) {
        this.numYears = numYears;
        this.type = type;
        this.repaymentsPerYear = repaymentsPerYear;
        this.requetsedLoan = requetsedLoan;
        this.interestRate = interestRate;
    }

    public void setLegalFee(double legalFee) {
        this.legalFee = legalFee;
    }

    public void setLegalLoanAmount(double legalLoanAmount) {
        this.legalLoanAmount = legalLoanAmount;
    }

    public Date getDateOfLoan() {
        return dateOfLoan;
    }

    public void setDateOfLoan(Date dateOfLoan) {
        this.dateOfLoan = dateOfLoan;
    }

    public void calculateLegalLoanAmount(){

        this.legalLoanAmount =this.requetsedLoan + (this.requetsedLoan * 0.2);
    }

    public void calculateLegalFee(){

        double conveyance =0.0;
        double purchase = this.loanPurchasePrice;

        if(purchase <= 10000  ){
            conveyance = purchase * (6/100);
        }
        else if(purchase > 10000 && purchase <= 250000){
            conveyance = purchase * (6.5/100);
        }
        else if(purchase > 250000 && purchase <= 500000){
            conveyance = purchase * (7.5/100);
        }
        else if(purchase > 500000 && purchase < 1000000){
            conveyance = purchase * (4/100);
        }
        else if(purchase > 1000000 ){
            conveyance = purchase * (4/100);
        }
        double mortaggeBond = this.legalLoanAmount * 0.04;

        System.out.println(this.legalLoanAmount);
        this.legalFee = mortaggeBond + conveyance;
    }

    public double getLegalLoanAmount() {
        return legalLoanAmount;
    }

    public double getLegalFee() {
        return legalFee;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getValuationAmount() {
        return valuationAmount;
    }

    public void calculateValuationAmount(){
        this.valuationAmount = this.establishmentFee + this.collateralFee;
    }

    public double getEstablishmentRate() {
        return establishmentRate;
    }

    public void setEstablishmentRate(double establishmentRate) {
        this.establishmentRate = establishmentRate;
    }

    public double getCollateralValue() {
        return collateralValue;
    }

    public void setCollateralValue(double collateralValue) {
        this.collateralValue = collateralValue;
    }

    public double getRequetsedLoan() {
        return requetsedLoan;
    }

    public void setRequetsedLoan(double requetsedLoan) {
        this.requetsedLoan = requetsedLoan;
    }

    public double getCollateralRate() {
        return collateralRate;
    }

    public void setCollateralRate(double collateralRate) {
        this.collateralRate = collateralRate;
    }

    public int getNumYears() {
        return numYears;
    }

    public void setNumYears(int numYears) {
        this.numYears = numYears;
    }

    public int getRepaymentsPerYear() {
        return repaymentsPerYear;
    }

    public void setRepaymentsPerYear(int repaymentsPerYear) {
        this.repaymentsPerYear = repaymentsPerYear;
    }



    public double getExiMonPay() {
        return exiMonPay;
    }

    public void setExiMonPay(double exiMonPay) {
        this.exiMonPay = exiMonPay;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getLoanPurchasePrice() {
        return loanPurchasePrice;
    }

    public void setLoanPurchasePrice(double loanPurchasePrice) {
        this.loanPurchasePrice = loanPurchasePrice;
    }

    public double getCollateralFee() {
        return collateralFee;
    }

    public void calculateCollateralFee() {
        this.collateralFee = collateralRate * collateralValue;
    }

    public double getEstablishmentFee() {
        return establishmentFee;
    }

    public void calcuateEstablishmentFee() {
        this.establishmentFee = (establishmentRate/100) * requetsedLoan;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Date getStartDate() {

        Calendar c = Calendar.getInstance();
        c.setTime(this.dateOfLoan);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getLifePremium() {
        return lifePremium;
    }

    public void setLifePremium(double lifePremium) {
        this.lifePremium = lifePremium;
    }

    public double getHocPremium() {
        return hocPremium;
    }

    public void setHocPremium(double hocPremium) {
        this.hocPremium = hocPremium;
    }

    public double getLoanTotalAmount() {
        return loanTotalAmount;
    }

    public void setLoanTotalAmount(double loanTotalAmount) {
        this.loanTotalAmount = loanTotalAmount;
    }

    public double getRateIncLife() {
        return this.rateIncLife;
    }

    public void setRateIncLife(double rateIncLife) {
        this.rateIncLife = rateIncLife;
    }

    public void calculateLoanAmountIncGrace(){
        this.loanTotalAmount = this.requetsedLoan + this.valuationAmount;

        double total = this.loanTotalAmount;
        double threeMonths =0.0;
        for(int i=0; i<3; i++){

            double x= Math.pow((1 + ((this.rateIncLife/100.0) / 365.0)),(365.0 /12.0))-1  ;

            double y = total * x;
            total +=y;
            threeMonths += y;

        }

        this.loanTotalAmount += threeMonths + this.hocPremium;
    }
}
