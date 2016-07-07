package com.bk.loancalculator.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bk.loancalculator.R;
import com.bk.loancalculator.adapter.ListAdapter;
import com.bk.loancalculator.db.DatabaseHelper;
import com.bk.loancalculator.ui.HistoryDetailActivity;

/**
 * Created by anjoroge on 7/5/2016.
 */
public class HistoryFragment extends Fragment {

    DatabaseHelper db;
    Cursor cursor;
    ListView lvItems;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(getContext(), null, null, 1);
        String DATABASE_TABLE = "tableMortgage";
        SQLiteDatabase dbb = db.getWritableDatabase();
        String query = "SELECT * FROM " + DATABASE_TABLE + " WHERE 1";

       cursor= dbb.rawQuery(query, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history, container, false);
        // Find ListView to populate
        lvItems = (ListView) view.findViewById(R.id.listView);
// Setup cursor adapter using cursor from last step
        ListAdapter listAdapter = new ListAdapter(getActivity().getApplicationContext(), cursor,0);
// Attach cursor adapter to the ListView
        lvItems.setAdapter(listAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),HistoryDetailActivity.class);
                Bundle bundle = new Bundle();

                Cursor c=  (Cursor)lvItems.getItemAtPosition(position);
                //passIntent.putExtra("Characters", c.getColumn);
               Toast.makeText(getContext(),""+ String.valueOf(c.getDouble(c.getColumnIndex("Rate"))),Toast.LENGTH_SHORT).show();

                bundle.putString("rate",String.valueOf(c.getDouble(c.getColumnIndex("Rate"))));
                bundle.putString("terms",String.valueOf(c.getDouble(c.getColumnIndex("Years"))));
                bundle.putString("repays",String.valueOf(c.getDouble(c.getColumnIndex("Repayments"))));
                bundle.putString("HOC",String.valueOf(c.getDouble(c.getColumnIndex("HCO"))));
                bundle.putString("lifePrem",String.valueOf(c.getDouble(c.getColumnIndex("Life"))));
                bundle.putString("legal",String.valueOf(c.getDouble(c.getColumnIndex("Legal"))));
                bundle.putString("val",String.valueOf(c.getDouble(c.getColumnIndex("Valuation"))));
                bundle.putString("installment",String.valueOf(c.getDouble(c.getColumnIndex("Install"))));
                bundle.putString("loanValue",String.valueOf(c.getDouble(c.getColumnIndex("LoanValue"))));
                bundle.putString("loan",String.valueOf(c.getDouble(c.getColumnIndex("Loan"))));

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
        return view;
    }

}
