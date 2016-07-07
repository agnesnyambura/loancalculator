package com.bk.loancalculator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bk.loancalculator.R;
import com.bk.loancalculator.db.DatabaseHelper;
import com.bk.loancalculator.model.Payment;
import com.bk.loancalculator.ui.DisplayLoan;

import java.util.Date;


public class LoanFragment extends Fragment {

    String loanType="";
    DatabaseHelper db;

    public LoanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(getContext(), null, null, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mortgage, container, false);
        final EditText loanAmount = (EditText) view.findViewById(R.id.edtLoanAmount);

        final EditText loanRate = (EditText) view.findViewById(R.id.edtInterest);

        final EditText terms = (EditText) view.findViewById(R.id.edtTerm);

        final EditText repayFreq = (EditText) view.findViewById(R.id.edtRepayments);

        final EditText charges = (EditText) view.findViewById(R.id.edtCharges);

        final EditText lifePrem = (EditText) view.findViewById(R.id.edtLifePrem);

        final EditText HOC = (EditText) view.findViewById(R.id.edtHOC);

        final Spinner type = (Spinner) view.findViewById(R.id.spType);

        final EditText legal = (EditText) view.findViewById(R.id.edtLegal);

        final EditText valuation = (EditText) view.findViewById(R.id.edtVal);


        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                loanType = type.getSelectedItem().toString();
                if (loanType.equalsIgnoreCase("Personal")) {

                    legal.setEnabled(false);
                    valuation.setEnabled(false);

                } else {
                    legal.setEnabled(true);
                    valuation.setEnabled(true);

                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        Button calculate = (Button) view.findViewById(R.id.btnCalc);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double laAm= Double.parseDouble(loanAmount.getText().toString());
                double rate =Double.parseDouble(loanRate.getText().toString());
                int years= Integer.parseInt(terms.getText().toString());
                int repay= Integer.parseInt(repayFreq.getText().toString());
                double etsablish= Double.parseDouble(charges.getText().toString());
                double lifePre= Double.parseDouble(lifePrem.getText().toString());
                double hocPrem =Double.parseDouble(HOC.getText().toString());

                com.bk.loancalculator.model.Loan loan = new com.bk.loancalculator.model.Loan();
                loan.setDateOfLoan(new Date());
                loan.setRateIncLife(rate +1.1);
                loan.setInterestRate(rate);
                loan.setRepaymentsPerYear(repay);
                loan.setNumYears(years);
                //loan.setRequetsedLoan(laAm);
                loan.setEstablishmentRate(etsablish);
                loan.setLifePremium(lifePre);
                loan.setHocPremium(hocPrem);
                loan.getStartDate();

                loan.calcuateEstablishmentFee();
               // loan.calculateCollateralFee();
                //loan.calculateValuationAmount();
                //loan.calculateLoanAmountIncGrace();

                if (loanType.equalsIgnoreCase("Mortgage")) {
                    loan.setLoanPurchasePrice(loan.getRequetsedLoan());
                    //loan.calculateLegalLoanAmount();
                    //loan.calculateLegalFee();

                    loan.setLoanTotalAmount(loan.getLoanTotalAmount() + loan.getLegalFee());
                }
                Payment payment = new Payment(loan);

                //payment.calculateBasicInstallment();
                payment.setBasicInstallment(laAm);
                payment.setTotalInstallment(laAm + hocPrem/repay);

                payment.setInstallmentStartDate(loan.getStartDate());

                db.addLoan(loan, payment);

               Intent i= new Intent(getContext(), DisplayLoan.class);
                i.putExtra("install",String.valueOf(payment.getTotalInstallment()));
                i.putExtra("loanAmount", String.valueOf(calculateLoanAmount(loan, payment)));

              //  i.putExtra("DBR", String.valueOf(payment.calculateDBR()));

                startActivity(i);

            }
        });


        return view;
    }

    public static double calculateLoanAmount(com.bk.loancalculator.model.Loan loan, Payment p){
        double perPeriodRate = (loan.getRateIncLife() / 100) / loan.getRepaymentsPerYear();

        int totPayments = (loan.getNumYears() * loan.getRepaymentsPerYear());


        double r = (1 - Math.pow(1 + perPeriodRate, (-totPayments)) * p.getBasicInstallment());

        double value = r/perPeriodRate;

        value -= (loan.getHocPremium());
        value = value / (loan.getEstablishmentRate());

        return -value;
    }
}