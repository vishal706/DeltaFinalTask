package com.example.vishalsingh.deltafinaltask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vishal Singh on 09-07-2015.
 */
public class Deletion extends Activity {
    long positionContact;
    String positionString;
    String text;
    SQLController sqc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletion_layout);
        Button button1 = (Button) findViewById(R.id.yesButton);
        Button button2 = (Button) findViewById(R.id.noButton);
        sqc=new SQLController(this);
        sqc.open();
        TextView message=(TextView)findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        positionString = bundle.getString("memberID");
        text = bundle.getString("memberName");
        positionContact = Long.parseLong(positionString);
        String messageString="Are You Sure You Want To Delete "+positionString+" Contact?";
        message.setText(messageString);
    }

    public void onYes(View v){
        sqc.deleteData(positionContact);
        Intent main = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Toast toast = Toast.makeText(getApplicationContext(),"Contact "+text+" Deleted Successfully", Toast.LENGTH_LONG);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(40);
        toast.show();
        startActivity(main);
    }
    public void onNo(View v){
        Toast toast = Toast.makeText(getApplicationContext(),"Contact "+text+" Not Deleted", Toast.LENGTH_LONG);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(40);
        toast.show();
        finish();
    }
    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "No Contact Deleted", Toast.LENGTH_LONG);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(40);
        toast.show();
        return;
    }
}
