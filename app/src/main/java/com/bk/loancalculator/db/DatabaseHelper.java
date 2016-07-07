package com.bk.loancalculator.db;


import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import com.bk.loancalculator.model.Loan;
import com.bk.loancalculator.model.Payment;


public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * The Mortgage class has the input attributes that define a particular mortgage as shown
     */
    SQLiteDatabase db;

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Loans.db";

    private static final String DATABASE_TABLE = "tableMortgage";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "Date";
    private static final String COLUMN_LOAN = "Loan";
    private static final String COLUMN_REPAYMENTS = "Repayments";
    private static final String COLUMN_TERMS = "Years";
    private static final String COLUMN_RATE = "Rate";
    private static final String COLUMN_CHARGES = "Charges";
    private static final String COLUMN_LEGAL = "Legal";
    private static final String COLUMN_LIFE_PREMIUM = "Life";
    private static final String COLUMN_HOC_PREMIUM = "HCO";

    private static final String COLUMN_INSTALLMENT = "Install";
    private static final String COLUMN_LOAN_VALUE = "LoanValue";
    private static final String COLUMN_VALUATION = "Valuation";
    /*
    *
    * The Constructor for the Database Helper Class
     */
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    /**
     * This method overrides the onUpgrade method inherited
     * It defines the behaviour of the database once the version of the database changes
     *
     * @param db         SQLiteDatabase attribute that references the database
     * @param oldVersion the old version number of the database
     * @param newVersion the new version number of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    /**
     * This method defines the creation of the table in the database
     * @param db SQLiteDatabase attribute that references the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + DATABASE_TABLE
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_LOAN + " REAL,"
                + COLUMN_REPAYMENTS + " INTEGER,"
                + COLUMN_DATE+ " REAL,"
                + COLUMN_RATE + " REAL,"
                + COLUMN_CHARGES + " REAL,"
                + COLUMN_TERMS + " INTEGER,"
                + COLUMN_LIFE_PREMIUM + " REAL,"
                + COLUMN_LOAN_VALUE + " REAL,"
                + COLUMN_INSTALLMENT + " REAL,"

                + COLUMN_HOC_PREMIUM + " REAL,"
                + COLUMN_LEGAL + " REAL,"
                + COLUMN_VALUATION + " REAL" +
                " );";
        db.execSQL(query);
    }

    /**
     * This method inserts the attributes of a Mortgage object to the database
     * @param loan The Loan object to be saved in the database
     */
    public void addLoan(Loan loan,Payment payment) {

        ContentValues newContact = new ContentValues();

        newContact.put(COLUMN_LOAN, loan.getLoanTotalAmount());
        newContact.put(COLUMN_REPAYMENTS, loan.getRepaymentsPerYear());
        newContact.put(COLUMN_RATE, loan.getInterestRate());
        newContact.put(COLUMN_CHARGES, loan.getEstablishmentFee());
        newContact.put(COLUMN_TERMS, loan.getNumYears());
        newContact.put(COLUMN_LIFE_PREMIUM, loan.getLifePremium());
        newContact.put(COLUMN_HOC_PREMIUM, loan.getHocPremium());
        newContact.put(COLUMN_LEGAL, loan.getLegalFee());
        newContact.put(COLUMN_VALUATION, loan.getValuationAmount());
        newContact.put(COLUMN_DATE, loan.getDateOfLoan().getTime());
        newContact.put(COLUMN_INSTALLMENT,payment.getTotalInstallment());

        db= getWritableDatabase();

        db.insert(DATABASE_TABLE, null, newContact);
        db.close();
    }

    public boolean updateContact(double loanAmount, int rowId) {
        ContentValues args = new ContentValues();
        args.put(COLUMN_LOAN_VALUE, loanAmount);

        db= getWritableDatabase();

        return db.update(DATABASE_TABLE, args,COLUMN_ID + "=" + rowId, null) > 0;
    }

    /**
     * This method reads the database and returns the attributes as a string
     * @return String that defines the values in the database
     */
    public String databaseToString() {

        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + DATABASE_TABLE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            if (c.getString(c.getColumnIndex("Loan")) != null) {
                dbString += ("\t\t\t$\t\t" + c.getString(c.getColumnIndex("Loan")) + "\t\t\t\t\t\t\t");
                dbString += (c.getString(c.getColumnIndex("Repayments")) + " %\t\t\t\t\t\t\t");
                dbString += (c.getString(c.getColumnIndex("Rate")) + " %\t\t\t\t\t\t\t");
                dbString += (c.getString(c.getColumnIndex("Years")) + "  years\t\t\t\t\t\t\t");
                dbString += "\n";
            }
            c.moveToNext();
        }

        db.close();
        return dbString;

    }
}

