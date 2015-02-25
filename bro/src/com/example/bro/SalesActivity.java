package com.example.bro;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

public class SalesActivity extends ActionBarActivity implements OnClickListener{

	private EditText date;
	private EditText desc;
	private Spinner s;
	private Calendar cal;
	 private int day;
	 private int month;
	 private int year;
	private TextView ccom;
	private EditText quantity;
	private EditText rate;
	private EditText amount;
	private EditText d200l;
	private EditText d50l;
	private EditText d35l;
	private EditText d20l;
	private EditText d10l;
	private EditText d5l;
	private String array_spinner[];
	private Button b1;
	private ImageButton ib;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sales);
		date=(EditText)findViewById(R.id.etdate);
		desc=(EditText)findViewById(R.id.etdesc);
		ib = (ImageButton) findViewById(R.id.imageButton1);
	       ib.setOnClickListener((new OnClickListener() {
	    	   
	            @Override
	            public void onClick(View v) {
	                 
	                // On button click show datepicker dialog 
	                showDialog(0);
	 
	            }
	 
	        }));
		ccom=(TextView)findViewById(R.id.ccom);
		b1=(Button)findViewById(R.id.button1);
		quantity=(EditText)findViewById(R.id.etquan);
		rate=(EditText)findViewById(R.id.etrate);
		amount=(EditText)findViewById(R.id.etamt);
		d200l=(EditText)findViewById(R.id.et100);
		d50l=(EditText)findViewById(R.id.et101);
		d35l=(EditText)findViewById(R.id.et102);
		d20l=(EditText)findViewById(R.id.et103);
		d10l=(EditText)findViewById(R.id.et104);
		d5l=(EditText)findViewById(R.id.et105);
		HotOrNot v=new HotOrNot(SalesActivity.this);
		v.openr();
		int cnt=v.getcount();
		System.out.println(cnt);
		if(cnt>0)
		{
			Cursor r=v.getcname();
			int d=r.getColumnIndex(v.Com_Name);
			int j=0;
			array_spinner=new String[cnt];
			for(r.moveToFirst();!r.isAfterLast();r.moveToNext()){
				array_spinner[j++]=r.getString(d);
				
			}
			s = (Spinner) findViewById(R.id.spinner1);
	        ArrayAdapter adapter = new ArrayAdapter(this,
	        android.R.layout.simple_spinner_item, array_spinner);
	        s.setAdapter(adapter);
		}
		v.close();
		b1.setOnClickListener(this);
		ccom.setOnClickListener(this);
		cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        String day1,month1;
        
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
        date.setText(day1 + " / " + month1 + " / "
       	     + year); 
        date.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        date.clearFocus();
                        desc.requestFocus();
                        return true;
                  }
                  return false;
            }
        });
        desc.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        desc.clearFocus();
                        quantity.requestFocus();
                        return true;
                  }
                  return false;
            }
        });
        quantity.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        quantity.clearFocus();
                        rate.requestFocus();
                        return true;
                  }
                  return false;
            }
        });
        rate.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        rate.clearFocus();
                        amount.requestFocus();
                        return true;
                  }
                  return false;
            }
        });
        amount.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        amount.clearFocus();
                        d200l.requestFocus();
                        return true;
                  }
                  return false;
            }
        });
        
        d200l.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        d200l.clearFocus();
                        d50l.requestFocus();
                        return true;
                  }
                  return false;
            }
        });
        d50l.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        d50l.clearFocus();
                        d35l.requestFocus();
                        return true;
                  }
                  return false;
            }
        });
        d35l.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        d35l.clearFocus();
                        d20l.requestFocus();
                        return true;
                  }
                  return false;
            }
        });
        d20l.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        d20l.clearFocus();
                        d10l.requestFocus();
                        return true;
                  }
                  return false;
            }
        });
        d10l.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                        // Perform action on Enter key press
                        d10l.clearFocus();
                        d5l.requestFocus();
                        return true;
                  }
                  return false;
            }
        });        
        d5l.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  // If the event is a key-down event on the "enter" button
                  if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                       (keyCode == KeyEvent.KEYCODE_ENTER))
                  {
                	  String n200=d200l.getText().toString();
          			String n50=d50l.getText().toString();
          			String n35=d35l.getText().toString();
          			String n20=d20l.getText().toString();
          			String n10=d10l.getText().toString();
          			String n5=d5l.getText().toString();
          			String date1=date.getText().toString();
          			String cname=(String)s.getSelectedItem();
          			String quan=quantity.getText().toString();
          			String rate1=rate.getText().toString();
          			String amt1=amount.getText().toString();
          			String desc1=desc.getText().toString();
          			
          			HotOrNot ini=new HotOrNot(SalesActivity.this);
          			
          			ini.open();
          			if(Integer.parseInt(n200)>0)
          			{
          				ini.decdrum(n200, "200 ltr");
          				ini.createntry(date1, n200, "200 ltr", "out", cname);
          			}
          			if(Integer.parseInt(n50)>0)
          			{	ini.decdrum(n50, "50 ltr");
          			
          				ini.createntry(date1, n50, "50 ltr", "out", cname);
          			
          			}if(Integer.parseInt(n35)>0){
          				ini.decdrum(n35, "35 ltr");
          				
          				ini.createntry(date1, n35, "35 ltr", "out", cname);
          			}if(Integer.parseInt(n20)>0){
          				ini.decdrum(n20, "20 ltr");
          				
          				ini.createntry(date1, n20, "20 ltr", "out", cname);
          			}if(Integer.parseInt(n10)>0)
          			{	ini.decdrum(n10, "10 ltr");
          			
          				ini.createntry(date1, n10, "10 ltr", "out", cname);
          			}if(Integer.parseInt(n5)>0)
          			{	
          				ini.decdrum(n5, "5 ltr");
          				System.out.println("5 ltr "+n5);
          				ini.createntry(date1, n5, "5 ltr", "out", cname);
          			}	
          			ini.updatebal(cname,amt1);
          			ini.createSales(date1,cname,quan,rate1,amt1,desc1);	
          				
          			ini.close();
          			finish();

                	  return true;
                  }
                  return false;
            }
        });
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
	   date.setText(day1 + " / " + month1 + " / "
	     + selectedYear);
	  }
	 };
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sales, menu);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.ccom:
			Intent in=new Intent("com.example.bro.Ccom");
			startActivity(in);
		
			break;
		case R.id.button1:
		{
			String n200=d200l.getText().toString();
			String n50=d50l.getText().toString();
			String n35=d35l.getText().toString();
			String n20=d20l.getText().toString();
			String n10=d10l.getText().toString();
			String n5=d5l.getText().toString();
			String date1=date.getText().toString();
			String cname=(String)s.getSelectedItem();
			String quan=quantity.getText().toString();
			String rate1=rate.getText().toString();
			String amt1=amount.getText().toString();
			String desc1=desc.getText().toString();
			
			HotOrNot ini=new HotOrNot(SalesActivity.this);
			
			ini.open();
			if(Integer.parseInt(n200)>0)
			{
				ini.decdrum(n200, "200 ltr");
				ini.createntry(date1, n200, "200 ltr", "out", cname);
			}
			if(Integer.parseInt(n50)>0)
			{	ini.decdrum(n50, "50 ltr");
			
				ini.createntry(date1, n50, "50 ltr", "out", cname);
			
			}if(Integer.parseInt(n35)>0){
				ini.decdrum(n35, "35 ltr");
				
				ini.createntry(date1, n35, "35 ltr", "out", cname);
			}if(Integer.parseInt(n20)>0){
				ini.decdrum(n20, "20 ltr");
				
				ini.createntry(date1, n20, "20 ltr", "out", cname);
			}if(Integer.parseInt(n10)>0)
			{	ini.decdrum(n10, "10 ltr");
			
				ini.createntry(date1, n10, "10 ltr", "out", cname);
			}if(Integer.parseInt(n5)>0)
			{	
				ini.decdrum(n5, "5 ltr");
				System.out.println("5 ltr "+n5);
				ini.createntry(date1, n5, "5 ltr", "out", cname);
			}	
			ini.updatebal(cname,amt1);
			ini.createSales(date1,cname,quan,rate1,amt1,desc1);	
				
			ini.close();
			finish();
		}
			break;
		}
	}

	
	
}
