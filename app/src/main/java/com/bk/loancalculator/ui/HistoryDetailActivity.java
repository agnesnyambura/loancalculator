package com.bk.loancalculator.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.bk.loancalculator.R;


public class HistoryDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    EditText installment, rate, repayment, legal, HOC, lifePrem, valuation, terms, charges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        installment = (EditText) findViewById(R.id.edtLoanAmount);
        installment.setEnabled(false);
        rate = (EditText) findViewById(R.id.edtInterest);
        rate.setEnabled(false);
        repayment = (EditText)findViewById(R.id.edtRepayments);
        valuation = (EditText)findViewById(R.id.edtVal);
        charges = (EditText)findViewById(R.id.edtCharges);
        HOC = (EditText) findViewById(R.id.edtHOC);
        terms = (EditText) findViewById(R.id.edtTerm);
        terms.setEnabled(false);
        lifePrem = (EditText) findViewById(R.id.edtLifePrem);

        if (getIntent().getExtras() !=null) {
            String loan = getIntent().getStringExtra("Loan");
            String rates = getIntent().getStringExtra("Rate");
            String repays = getIntent().getStringExtra("Repayments");
            String charges = getIntent().getStringExtra("Loan");
            String install= getIntent().getStringExtra("Install");
            String HOCs = getIntent().getStringExtra("HOC");
            String life= getIntent().getStringExtra("life");
            String val = getIntent().getStringExtra("Val");
            String years = getIntent().getStringExtra("Years");

            installment.setText(install);
            valuation.setText(val);
            rate.setText(rates);
            terms.setText(years);
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + "");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
