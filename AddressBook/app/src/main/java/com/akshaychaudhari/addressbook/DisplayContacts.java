package com.akshaychaudhari.addressbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayContacts extends ActionBarActivity {
    SQLiteDatabase record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        record = openOrCreateDatabase("Record", Context.MODE_PRIVATE,null);
        Cursor c=record.rawQuery("SELECT * FROM record", null);
        StringBuffer buffer=new StringBuffer();
        // Checking if no records found
        if(c.getCount()==0)
        {
            buffer.append("No contacts found!");
            return;
        }
        // Appending records to a string buffer

        while(c.moveToNext())
        {
            buffer.append("Name: "+c.getString(0)+"\n");
            buffer.append("Contact No: "+c.getString(1)+"\n\n");
        }
        TextView textView = new TextView(this);
        textView.setText(buffer);
        textView.setTextSize(25);
        textView.setTextColor(Color.BLUE);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        setContentView(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_contacts, menu);
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
