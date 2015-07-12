package com.example.vishalsingh.deltafinaltask;

import android.database.Cursor;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ListView listView;
    ImageButton imageButton;
    SQLController sqc;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqc = new SQLController(this);
        sqc.open();//method used to access the methods of database
        listView = (ListView) findViewById(R.id.listview);
        imageButton=(ImageButton)findViewById(R.id.imageButton);
        DBhelper handler = new DBhelper(this);
        SQLiteDatabase db = handler.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM member ", null);//cursor pints to table created
        CustomCursorAdapter adapter=new CustomCursorAdapter(this,cursor);
        listView.setAdapter(adapter);

        //Toast of increased font size
        Toast toast = Toast.makeText(getApplicationContext(), "Welcome To The Contact App", Toast.LENGTH_LONG);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(40);
        toast.show();

    }
    public void onAdding(View v){
        Intent add=new Intent(this,Addition.class);
        startActivity(add);
        CustomCursorAdapter adapter=new CustomCursorAdapter(this,cursor);
        listView.setAdapter(adapter);
    }
}
