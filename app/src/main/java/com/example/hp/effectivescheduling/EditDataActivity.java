package com.example.hp.effectivescheduling;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*public class EditDataActivity extends AppCompatActivity {
    private static final String TAG ="EditDataActivity";
    private Button btnSave,btnDelete;
    private EditText editdate;
    private  String selecteddate;
    private  int selectID;
    DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit_data);
        btnSave =(Button )findViewById (R.id.notesavebuttonedit);
        btnDelete= (Button )findViewById (R.id.notedeletebuttonedit);
        editdate=(EditText)findViewById (R.id.editNoteeditText1);
        myDB =new DataBaseHelper (this);
        Intent receivedIntent = getIntent ();
        selectID=receivedIntent.getIntExtra ("id",-1);
        selecteddate=receivedIntent.getStringExtra ("date");
        editdate.setText (selecteddate);
        btnSave.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String item = editdate.getText ().toString ();
                if(!item.equals (" ")){
                    myDB.update (item,selectID,selecteddate);

                }
                else{
                    Toast.makeText (EditDataActivity.this,"you must enter a data",Toast.LENGTH_SHORT).show ();

                }

            }
        });
        btnDelete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                myDB.delete(selectID,selecteddate);
                editdate.setText (" ");

                Toast.makeText (EditDataActivity.this,"data removed",Toast.LENGTH_SHORT).show ();


            }
        });

    }

}*/

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_item;

    DataBaseHelper mDatabaseHelper;
    private String selectedContent;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        btnSave = (Button) findViewById(R.id.notesavebuttonedit);
        btnDelete = (Button) findViewById(R.id.notedeletebuttonedit);
        editable_item = (EditText) findViewById(R.id.editNoteeditText1);
        mDatabaseHelper = new DataBaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedContent = receivedIntent.getStringExtra("Content");

        //set the text to show the current selected name
        editable_item.setText(selectedContent);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.update (item,selectedID,selectedContent);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.delete (selectedID,selectedContent);
                editable_item.setText("");
                toastMessage("removed from database");
            }
        });

    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}

