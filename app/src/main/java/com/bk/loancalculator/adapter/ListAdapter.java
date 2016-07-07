package com.bk.loancalculator.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bk.loancalculator.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ListAdapter extends CursorAdapter {
    public ListAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.history_list, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvLoan = (TextView) view.findViewById(R.id.tvLoanAmount);
        TextView tvRate = (TextView) view.findViewById(R.id.tvRate);
        TextView tvTerms = (TextView) view.findViewById(R.id.tvTerms);
        TextView tvRepays = (TextView) view.findViewById(R.id.tvRepays);
        // Extract properties from cursor
        long date = (long) cursor.getDouble(cursor.getColumnIndexOrThrow("Date") );
        Double loan = cursor.getDouble(cursor.getColumnIndexOrThrow("Loan"));
        int repays = cursor.getInt(cursor.getColumnIndexOrThrow("Repayments"));
        Double rate = cursor.getDouble(cursor.getColumnIndexOrThrow("Rate"));
        int terms = cursor.getInt(cursor.getColumnIndexOrThrow("Years"));
        // Populate fields with extracted properties

        //Toast.makeText(context, ""+ String.valueOf(date),Toast.LENGTH_SHORT).show();
        String newstring = new SimpleDateFormat("dd-MMM-yy").format(new Date(date));
        tvDate.setText(newstring);
        tvLoan.setText(String.valueOf(loan));
        tvRate.setText(String.valueOf(rate));
        tvTerms.setText(String.valueOf(terms));
        tvRepays.setText(String.valueOf(repays));

    }
}