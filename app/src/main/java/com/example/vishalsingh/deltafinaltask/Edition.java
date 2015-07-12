package com.example.vishalsingh.deltafinaltask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vishal Singh on 09-07-2015.
 */
public class Edition extends Activity{

    EditText editText;
    long positionContact;
    String positionstring;
    Button button;
    SQLController sqc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edition_layout);
        editText = (EditText) findViewById(R.id.editionEditText);
        button = (Button) findViewById(R.id.editionButton);
        sqc=new SQLController(this);
        sqc.open();
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("memberName");//getting contactName and id from CustomCursorAdapter
        positionstring=bundle.getString("memberID");
        positionContact = Long.parseLong(positionstring);//conversion of string textview of id to long for the database method
        editText.setText(text);
    }
    public void onEdition(View v){
        String newContactName=editText.getText().toString();
        sqc.updateData(positionContact,newContactName);//updating the contact name see SQLControllerClass
        Intent main = new Intent(getApplicationContext(),MainActivity.class);
        Toast toast = Toast.makeText(getApplicationContext(),"Contact Successfully Edited", Toast.LENGTH_LONG);

        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(40);
        toast.show();
        startActivity(main);
    }
    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "No Contact Edited", Toast.LENGTH_LONG);

        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(40);
        toast.show();
        return;
    }
}
