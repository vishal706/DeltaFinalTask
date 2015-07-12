package com.example.vishalsingh.deltafinaltask;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Vishal Singh on 09-07-2015.
 */

public class CustomCursorAdapter extends CursorAdapter {
    LayoutInflater inflater;
    private Context context;
    private Cursor c;
    public CustomCursorAdapter(Context context, Cursor c)  {
        super(context,c,0);
        this.context=context;
        this.c=c;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public void bindView(View view,final Context context, Cursor cursor) {
        TextView tv1 = (TextView)view.findViewById(R.id.contact_Name);
        TextView tv2 = (TextView)view.findViewById(R.id.contact_Name_id);//this id textview is not visible but exists
        tv1.setText(cursor.getString(1));//setting tv1 to contact name table of database
        tv2.setText(cursor.getString(0));//setting tv1 to contact id table of database
        final String contact_id = cursor.getString(0);
        final String contact_val=cursor.getString(1);
        Button edit_button=(Button)view.findViewById(R.id.editButton);
        Button delete_button=(Button)view.findViewById(R.id.deleteButton);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG="TAG";//used
                Log.v(TAG, "index=" + contact_id);//for
                Log.v(TAG, "Name=" + contact_val);//debugging
                Intent edit = new Intent(context.getApplicationContext(),Edition.class);
                edit.putExtra("memberName", contact_val);
                edit.putExtra("memberID", contact_id);
                context.startActivity(edit);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG="TAG";
                Log.v(TAG, "index=" + contact_id);
                Log.v(TAG, "Name=" + contact_val);
                Intent delete = new Intent(context.getApplicationContext(),Deletion.class);
                delete.putExtra("memberID", contact_id);
                delete.putExtra("memberName", contact_val);
                context.startActivity(delete);
            }
        });
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.row_layout, parent, false);
    }
}
