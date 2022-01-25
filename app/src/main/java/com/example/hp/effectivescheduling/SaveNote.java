package com.example.hp.effectivescheduling;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SaveNote extends AppCompatActivity {
    ListView listView;
    DataBaseHelper myDB;
    private  static final  String TAG="ListDataActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_save_note);
        listView = findViewById (R.id.save_note_listview);
        myDB = new DataBaseHelper (this);
        viewAll ();




    }

   public void viewAll() {
        ArrayList<String>listData =new ArrayList<> ();
        Cursor cursor = myDB.getAllData ();
        if (cursor.getCount () == 0) {
            Toast.makeText (getApplicationContext (), "There is no data to show", Toast.LENGTH_LONG).show ();
        } else {
            while (cursor.moveToNext ()) {
               listData .add ("ID: " + cursor.getString (0) + "\n" + "Date " + cursor.getString (1) +
                       "\n" );
            }

        }
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.t_view,listData);
       listView.setAdapter(adapter);
       listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String date = parent.getItemAtPosition (position).toString ();

               Cursor data=myDB.getItemID (date);
               int itemID=1;
               while(data.moveToNext ()){
                   itemID = data.getInt (0);
               }
               if(itemID > -1) {
                   Log.d (TAG, "on ItemClick: The ID us:" + itemID);
                   Intent editScreenIntent = new Intent (SaveNote.this,EditDataActivity.class);
                   editScreenIntent.putExtra ("id",itemID);
                   editScreenIntent.putExtra ("title",date);

                   startActivity (editScreenIntent);

               }
                   else{
                       Toast.makeText (getApplicationContext (), "No ID associated with that name", Toast.LENGTH_LONG).show ();



               }


           }
       });
   }



}
