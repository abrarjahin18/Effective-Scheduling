package com.example.hp.effectivescheduling;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Reminder_Activity extends AppCompatActivity {

    EditText TimeText;
    EditText TitleText;
    Button SaveButton;
    NotificationCompat.Builder notification;
    private static final int uniqueId=45612;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_);
        TitleText=(EditText)findViewById (R.id.tittle_set);
        TimeText=(EditText)findViewById (R.id.time_set);
        SaveButton = (Button) findViewById (R.id.save_button);
        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        // myDB=new DatabaseHelper();
        AddData ();
        //String system_time= String.valueOf(System.currentTimeMillis());

//        Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                show_notification();
//            }
//        },1000);




        // Create the Handler object (on the main thread by default)
        final Handler handler = new Handler();
// Define the code block to be executed
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread
                //Log.d("Handlers", "Called on main thread");
                show_notification();

                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 59000);
            }
        };
// Start the initial runnable task by posting through the handler
        handler.post(runnableCode);
    }
    public void show_notification()
    {
        Time dtNow = new Time();
        dtNow.setToNow();
        int hours = dtNow.hour;
        String lsNow;
        if ( hours<12   ) {
            lsNow = dtNow.format("%H:%M");
            lsNow = lsNow + "am";
        }
        else {
            dtNow.hour=dtNow.hour-12;
            lsNow = dtNow.format("%H:%M");
            lsNow = lsNow + "pm";

        }

        //String lsYMD = dtNow.toString();    // YYYYMMDDTHHM

        //   String database_time= String.valueOf(myDatabaseHelper.getTime());
        // int compare=system_time.compareTo(database_time);
        //if(compare<0)

        Cursor c = myDatabaseHelper.getTittleByTime(lsNow);
        //Cursor c = myDatabaseHelper.getTittleByTime("12:20pm");
        //Cursor c = myDatabaseHelper.getTittle();

        if (c != null) {
            if (c.getCount() > 0) {
                c.moveToFirst();

                //String s = c.getString(c.getColumnIndex("TITLE"));
                String title = c.getString(0);
                String time = c.getString(1);
                if (title != null) {
                    Notification(title);
                }
            }
        }
    }

    DataBaseHelper myDatabaseHelper = new DataBaseHelper(this);


    public void AddData(){
        SaveButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //System.out.println(TimeText.getText ().toString ());
                //System.out.println(TitleText.getText ().toString ());

                boolean isInserted= myDatabaseHelper.insertData (TimeText.getText ().toString (),TitleText.getText ().toString ());
                if(isInserted==true)
                    Toast.makeText (Reminder_Activity.this,"DATA INSERTED",Toast.LENGTH_SHORT).show ();
                else
                    Toast.makeText (Reminder_Activity.this,"DATA NOT INSERTED",Toast.LENGTH_SHORT).show ();

            }
        });

    }
    public void Notification(String s)
    {
        //building notification
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("this is ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(s);
        notification.setContentText("Notifications");

        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueId,notification.build());

    }

}
