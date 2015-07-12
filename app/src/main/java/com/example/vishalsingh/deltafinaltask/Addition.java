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

import com.example.vishalsingh.deltafinaltask.MainActivity;
import com.example.vishalsingh.deltafinaltask.SQLController;

/**
 * Created by Vishal Singh on 09-07-2015.
 */

public class Addition extends Activity {

    EditText editText;
    Button button;
    SQLController sqc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addition_layout);
        editText = (EditText) findViewById(R.id.additionEditText);
        button = (Button) findViewById(R.id.additionButton);
        sqc = new SQLController(this);
        sqc.open();
    }

    public void onAdding(View v) {
        String name = editText.getText().toString();
        sqc.insertData(name);
        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        Toast toast = Toast.makeText(getApplicationContext(), "Contact " + name + " Added Successfully", Toast.LENGTH_LONG);

        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(40);
        toast.show();
        startActivity(main);
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "No New Contact Added", Toast.LENGTH_LONG);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(40);
        toast.show();
        return;
    }

}