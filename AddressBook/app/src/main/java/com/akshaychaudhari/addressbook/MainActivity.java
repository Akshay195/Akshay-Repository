package com.akshaychaudhari.addressbook;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {
    private EditText editName,editCont;
    private Button btnSave;
    private Button btndel;
    SQLiteDatabase record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        record = openOrCreateDatabase("Record",Context.MODE_PRIVATE,null);
        record.execSQL("CREATE TABLE IF NOT EXISTS record(name VARCHAR,cont VARCHAR);");
        //Getting objects
        editName = (EditText) findViewById(R.id.edit_name);
        editCont = (EditText)findViewById(R.id.edit_cont);
        btnSave = (Button)findViewById(R.id.button1);
        btndel = (Button)findViewById(R.id.button2);
        //Registering event Handlers
        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editName.getText().toString().trim().length() == 0 || editCont.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Insufficient Input", Toast.LENGTH_LONG).show();
                }
                else {
                    record.execSQL("INSERT INTO record VALUES('" + editName.getText() + "','" + editCont.getText() + "');");
                    Toast.makeText(getApplicationContext(), "Contact Saved Successfully", Toast.LENGTH_LONG).show();
                    clrbox();
                }
            }
        });
        btndel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editName.getText().toString().trim().length()==0){
                    Toast.makeText(getApplicationContext(),"Please enter Name",Toast.LENGTH_LONG).show();
                }
                else{
                    record.execSQL("DELETE FROM record WHERE name = '"+editName.getText()+"'");
                    Toast.makeText(getApplicationContext(),"Contact Deleted Successfully",Toast.LENGTH_LONG).show();
                    clrbox();
                }
            }
        });
    }

    private void clrbox() {
        editName.setText("");
        editCont.setText("");
        editName.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if(id==R.id.action_credits){
            openCredits();
        }
        else if(id==R.id.action_record){
            openRecords();
        }

        return super.onOptionsItemSelected(item);
    }

    private void openCredits() {
        Intent newIntent = new Intent(this,Credits.class);
        startActivity(newIntent);
    }


    private void openRecords() {
        Intent intent = new Intent(this,DisplayContacts.class);
        startActivity(intent);
    }


}
