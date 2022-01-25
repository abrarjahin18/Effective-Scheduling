package com.example.hp.effectivescheduling;

import android.app.ListActivity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;


public class NewNoteActivity extends AppCompatActivity{
    private DataBaseHelper myDB;
    private Button mSaveNoteButton;
    private Button mDeleteNoteButton;
    private EditText dateText;
    private  EditText titleText;
    private  EditText contentText;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_note);
        mSaveNoteButton = (Button) findViewById (R.id.notesavebutton);
        mDeleteNoteButton = (Button) findViewById (R.id.notedeletebutton);
        myDB =new DataBaseHelper (this);
        dateText=(EditText)findViewById (R.id.SaveNoteeditText1);
        titleText=(EditText)findViewById (R.id.SaveNoteeditText2);
        contentText=(EditText)findViewById (R.id.SaveNoteeditText3);
        AddData ();


    }
    public void AddData(){
        mSaveNoteButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
               boolean isInserted= myDB.insertData (dateText.getText ().toString ());
               if(isInserted ==true)
                   Toast.makeText (NewNoteActivity.this,"DATA INSERTED",Toast.LENGTH_SHORT).show ();
               else
                   Toast.makeText (NewNoteActivity.this,"DATA NOT INSERTED",Toast.LENGTH_SHORT).show ();



            }
        });

    }

}

