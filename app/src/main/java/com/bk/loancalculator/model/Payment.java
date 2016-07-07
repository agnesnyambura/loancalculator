package com.bk.loancalculator.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Payment {

    private Loan loan;
    private double basicInstallment;
    private double totalInstallment;
    private Date payoffDate;
    private double loanBalance;
    private ArrayList<Period> paymentPeriods;
    private Date installmentStartDate;

    public Payment(Loan loan) {
        this.loan = loan;
        this.paymentPeriods = new ArrayList<Period>();
    }

    public Loan getLoan() {
        return loan;
    }

    public Date getInstallmentStartDate() {


        return installmentStartDate;
    }

    public void setInstallmentStartDate(Date installmentStartDate) {
        Date d;
        try{
        Calendar c = Calendar.getInstance();
        c.setTime(installmentStartDate);
        c.add(Calendar.MONTH, 3);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            d= c.getTime();
            this.installmentStartDate =d;
    } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public double getBasicInstallment() {
        return basicInstallment;
    }

    public void setBasicInstallment(double basicInstallment) {
        this.basicInstallment = basicInstallment;
    }

    public double getTotalInstallment() {
        return this.basicInstallment + loan.getHocPremium();
    }

    public void setTotalInstallment(double totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public Date getPayoffDate() {
        return payoffDate;
    }

    public void setPayoffDate(Date payoffDate) {
        this.payoffDate = payoffDate;
    }

    public double getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(double loanBalance) {
        this.loanBalance = loanBalance;
    }

    public void calculateBasicInstallment() {
        double install = 0.0;

        double perPeriodRate = (loan.getRateIncLife() / 100) / loan.getRepaymentsPerYear();

        int totPayments = (loan.getNumYears() * loan.getRepaymentsPerYear());
        double lo = loan.getLoanTotalAmount();

        install = (lo * perPeriodRate) / (1 - Math.pow(1 + perPeriodRate, (-totPayments)));

        this.basicInstallment = install;
    }

    public double calculateDBR(){
        double totalPy = loan.getExiMonPay() + this.totalInstallment;

        return totalPy / loan.getGrossIncome();
    }

    public ArrayList<Period> calculatePeriods() {

        for (int i = 0; i < (loan.getNumYears() * loan.getRepaymentsPerYear()); i++) {
            Period p = new Period();


            if (i == 0) {

                p.setPaymentAmount(0);
                p.setPeriodDate(this.installmentStartDate);

                double interest = 0;
                interest = (loan.getRequetsedLoan() + loan.getValuationAmount()) * ((Math.pow((1 + (loan.getInterestRate() / 100) / 365), (365 / 12))) - 1);
                p.setInterest(interest);
                p.setInsurance(loan.getLifePremium());

                p.setPrincipaReduction(p.getPaymentAmount() - p.getInterest() - p.getHocPremium() - p.getInsurance());

                p.setBalance((loan.getRequetsedLoan() + loan.getValuationAmount())-p.getPrincipaReduction() );

            } else {

                if (i < 3) {
                    p.setPaymentAmount(0);
                    p.setPeriodNumber(0);
                } else {
                    p.setPaymentAmount(this.basicInstallment + (loan.getHocPremium()/loan.getRepaymentsPerYear()));
                    p.setPeriodNumber(i - 2);
                }
                Calendar c = Calendar.getInstance();
                c.setTime(paymentPeriods.get(i - 1).getPeriodDate());
                c.add(Calendar.MONTH, 1);
                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                p.setPeriodDate(c.getTime());

                double interest = 0;
                interest = (paymentPeriods.get(i - 1).getBalance()) * ((Math.pow((1 + (loan.getInterestRate() / 100) / 365), (365 / 12))) - 1);
                p.setInterest(interest);

                p.setInsurance(loan.getLifePremium());

                p.setPrincipaReduction(p.getPaymentAmount() - p.getInterest() - p.getHocPremium() - p.getInsurance());

                p.setBalance(paymentPeriods.get(i - 1).getBalance() - p.getPrincipaReduction());
            }

            Calendar c = Calendar.getInstance();
            c.setTime(p.getPeriodDate());

            Calendar cs = Calendar.getInstance();
            cs.setTime(loan.getStartDate());

          if(c.get(Calendar.MONTH)== cs.get(Calendar.MONTH) ){
                p.setHocPremium(loan.getHocPremium());
            }
            else{
                p.setHocPremium(0);
            }

            paymentPeriods.add(p);

        }

        return paymentPeriods;
    }


}
