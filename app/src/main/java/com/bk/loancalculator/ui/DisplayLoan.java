package com.bk.loancalculator.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.bk.loancalculator.R;
import com.bk.loancalculator.model.Period;

import java.util.ArrayList;

public class DisplayLoan extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_loan);

        TextView install = (TextView) findViewById(R.id.txtInstallmentPM);
        TextView loanam = (TextView) findViewById(R.id.txtLoan);

        String installment = "";
        String loanAm = "";
        String dbr = "";
        ArrayList<Period> payments = null;

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            installment = (String) bundle.get("install");
            installment = String.format("%.2f", Double.parseDouble(installment));

            loanAm = (String) bundle.get("loanAmount");
            loanAm = String.format("%.2f", Double.parseDouble(loanAm));

           //dbr = (String) bundle.get("DBR");
           // dbr = String.format("%.2f", (Double.parseDouble(dbr) * 100));

        }

        install.setText("$ "+installment);
        loanam.setText("$ " + loanAm);
       // dbR.setText(  dbr + " % ");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_loan, menu);
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
