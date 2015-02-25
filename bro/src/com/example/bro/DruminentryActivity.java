package com.example.bro;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//@SuppressLint("NewApi")
public class DruminentryActivity extends ActionBarActivity {

	private String array_spinner[];
	String inout[]={"in","out"};
	EditText e1;
	EditText de;
	Button b;
	 private ImageButton ib;
	 private Calendar cal;
	 private int day;
	 private int month;
	 private int year;
	 private EditText et;
	private Spinner s;
	private Spinner s2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_druminentry1);
		array_spinner=new String[6];
        array_spinner[0]="5 ltr";
        array_spinner[1]="10 ltr";
        array_spinner[2]="20 ltr";
        array_spinner[3]="35 ltr";
        array_spinner[4]="50 ltr";
        array_spinner[5]="200 ltr";
        s = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        
        ArrayAdapter adapter = new ArrayAdapter(this,
        android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
        ArrayAdapter adapter1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, inout);
                s2.setAdapter(adapter1);
        e1=(EditText)findViewById(R.id.editTextName);
        de=(EditText)findViewById(R.id.editText6);
        
        b=(Button)findViewById(R.id.button1);
        ib = (ImageButton) findViewById(R.id.imageButton1);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
String day1,month1;
et = (EditText) findViewById(R.id.editText5);
 
        if(day<10)
 	   {
 		   day=Integer.parseInt("0"+Integer.toString(day));
 		   day1=String.format("%02d", day);
 	   }
        else
        	day1=Integer.toString(day);
 	   if(month<9)
 	   {
 		  month =month + 1;
 		 month1=String.format("%02d", month);
 	   }
 	   else
 		  month1=Integer.toString(month);
        et.setText(day1 + " / " + month1 + " / "
       	     + year); 
        
        
        ib.setOnClickListener((new OnClickListener() {
 
            @Override
            public void onClick(View v) {
                 
                // On button click show datepicker dialog 
                showDialog(0);
 
            }
 
        }));
        s.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                	 	String date=et.getText().toString();
            			String quantity1=e1.getText().toString();
            			String capacity=(String) s.getSelectedItem();
            			String iniout=(String) s2.getSelectedItem();
            			
            			String desc=de.getText().toString();
            			System.out.println(date+" "+quantity1+"Capacity: "+capacity+" got or not");
            			HotOrNot entry=new HotOrNot(DruminentryActivity.this);
            			entry.open();
            			entry.createntry(date,quantity1,capacity,iniout,desc);
            			if(iniout.equals("in"))
            			entry.incdrum(quantity1,capacity);
            			else if(iniout.equals("out"))
                			entry.decdrum(quantity1,capacity);
                			
            			entry.close();
            		
                        return true;
                  }
                  return false;
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.druminentry, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	} 

	 @Override
	 protected Dialog onCreateDialog(int id) {
	  return new DatePickerDialog(this, datePickerListener, year, month, day);
	 }

	 private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
	  public void onDateSet(DatePicker view, int selectedYear,
	    int selectedMonth, int selectedDay) {
	       String day1,month1;
	        
	        if(day<10)
	 	   {
	 		   
	 		   day1=String.format("%02d", day);
	 	   }
	        else
	        	day1=Integer.toString(day);
	 	   if(month<9)
	 	   {
	 		  month =month + 1;
	 		 month1=String.format("%02d", month);
	 	   }
	 	   else
	 		  month1=Integer.toString(month);
	 
		  if(selectedDay<10)
	   {
		   selectedDay=Integer.parseInt("0"+Integer.toString(selectedDay));
		   day1=String.format("%02d", selectedDay);
			 
	   }
		  else
		  {
			  day1=Integer.toString(selectedDay);
		  }
	   if(selectedMonth<9)
	   {
		   selectedMonth =selectedMonth + 1;
		   month1=String.format("%02d", selectedMonth);}
	   else
	   {
		   month1=Integer.toString(selectedMonth);
	   }
	   et.setText(day1 + " / " + month1 + " / "
	     + selectedYear);
	  	  }
	 };
	 public void run(View view)
		{
			boolean did=true;
			try{
		 	String date=et.getText().toString();
			String quantity1=e1.getText().toString();
			String capacity=(String) s.getSelectedItem();
			String desc=de.getText().toString();
			String iniout=(String) s2.getSelectedItem();
			
			System.out.println(date+" "+quantity1+"Capacity: "+capacity+" got or not");
			HotOrNot entry=new HotOrNot(DruminentryActivity.this);
			entry.open();
			entry.createntry(date,quantity1,capacity,iniout,desc);
			if(iniout.equals("in"))
				entry.incdrum(quantity1,capacity);
			else if(iniout.equals("out"))
    			entry.decdrum(quantity1,capacity);
    			
			entry.close();
		
			}
			catch(Exception e)
			{
				did=false;
				System.out.println("Entry not made");
				
			}
			finally
			{
				if(did)
				{
					//Context context = getApplicationContext();
					CharSequence text = "Entry Succesful";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(this, text, duration);
					toast.show();
					
				}
				else
				{
					CharSequence text = "Entry UNSuccesful";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(this, text, duration);
					toast.show();
									}
			}
			finish();
		}
		

}
