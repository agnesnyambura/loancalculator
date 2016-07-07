package com.bk.loancalculator.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bk.loancalculator.R;
import com.bk.loancalculator.model.Period;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Period>{
    private LayoutInflater mInflate;
    Context c;
    private ArrayList<Period> payments;

    public MyArrayAdapter(Context context, int resource, ArrayList<Period> objects) {
        super(context, resource, objects);
        c=context;

       payments = objects;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.display_list, null);
        }


        Period p = payments.get(position);

        if (p != null) {


            TextView num = (TextView) v.findViewById(R.id.tvNumber);
            TextView inte = (TextView) v.findViewById(R.id.tvInterest);
            TextView bala = (TextView) v.findViewById(R.id.tvBalance);
            TextView insurance = (TextView) v.findViewById(R.id.tvAmount);
            TextView princ = (TextView) v.findViewById(R.id.tvPrinicp);

            num.setText( String.valueOf(p.getPeriodNumber()));
            inte.setText(String.format("%.2f", p.getInterest()));

            Log.v("in adape........" + String.valueOf(p.getInterest()), "");

            bala.setText(String.format("%.2f", p.getBalance()));
            insurance.setText(String.format("%.2f", p.getPaymentAmount()));
            princ.setText(String.format("%.2f", p.getPrincipaReduction()));


        }

        return v;


    }
}
