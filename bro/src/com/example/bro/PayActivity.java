package com.example.bro;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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

public class PayActivity extends ActionBarActivity implements OnClickListener{

	private EditText edate;
	private EditText eamt;
	private EditText edesc;
	private Spinner s;
	private String array_spinner[];
	private Button b1;
	private ImageButton ib;
	private Calendar cal;
	 private int day;
	 private int month;
	 private int year;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		edate=(EditText)findViewById(R.id.etdate);
		eamt=(EditText)findViewById(R.id.etamt);
		edesc=(EditText)findViewById(R.id.etdes);
		cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        String day1,month1;
        //et = (EditText) findViewById(R.id.editText5);
         
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
                edate.setText(day1 + " / " + month1 + " / "
               	     + year); 
                
    	ib = (ImageButton) findViewById(R.id.imageButton1);
	       ib.setOnClickListener((new OnClickListener() {
	    	   
	            @Override
	            public void onClick(View v) {
	                 
	                // On button click show datepicker dialog 
	                showDialog(0);
	 
	            }
	 
	        }));
		
		b1=(Button)findViewById(R.id.button1);
		HotOrNot v=new HotOrNot(PayActivity.this);
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
		b1.setOnClickListener(this);
		edate.setOnKeyListener(new OnKeyListener() {

		    public boolean onKey(View v, int keyCode, KeyEvent event) {
		          // If the event is a key-down event on the "enter" button
		          if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		               (keyCode == KeyEvent.KEYCODE_ENTER))
		          {
		                // Perform action on Enter key press
		                edate.clearFocus();
		                s.requestFocus();
		                return true;
		          }
		          return false;
		    }
		});
		s.setOnKeyListener(new OnKeyListener() {

		    public boolean onKey(View v, int keyCode, KeyEvent event) {
		          // If the event is a key-down event on the "enter" button
		          if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		               (keyCode == KeyEvent.KEYCODE_ENTER))
		          {
		                // Perform action on Enter key press
		                s.clearFocus();
		                eamt.requestFocus();
		                return true;
		          }
		          return false;
		    }
		});
		eamt.setOnKeyListener(new OnKeyListener() {

		    public boolean onKey(View v, int keyCode, KeyEvent event) {
		          // If the event is a key-down event on the "enter" button
		          if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		               (keyCode == KeyEvent.KEYCODE_ENTER))
		          {
		                // Perform action on Enter key press
		                eamt.clearFocus();
		                edesc.requestFocus();
		                return true;
		          }
		          return false;
		    }
		});
		edesc.setOnKeyListener(new OnKeyListener() {

		    public boolean onKey(View v, int keyCode, KeyEvent event) {
		          // If the event is a key-down event on the "enter" button
		          if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		               (keyCode == KeyEvent.KEYCODE_ENTER))
		          {
		                // Perform action on Enter key press
		        	  String date1=edate.getText().toString();
						String amt=eamt.getText().toString();
						String desci=edesc.getText().toString();
						String cname=(String) s.getSelectedItem();
						HotOrNot ins=new HotOrNot(PayActivity.this);
						ins.open();
						ins.createpay(date1,cname,amt,desci);
						ins.close();
						HotOrNot ins1=new HotOrNot(PayActivity.this);
						ins1.openr();
						
						ins1.redbal(cname,amt);
						ins.close();
						finish();
 
		        	  return true;
		          }
		          return false;
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pay, menu);
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
		String date1=edate.getText().toString();
				String amt=eamt.getText().toString();
				String desci=edesc.getText().toString();
				String cname=(String) s.getSelectedItem();
				HotOrNot ins=new HotOrNot(PayActivity.this);
				ins.open();
				ins.createpay(date1,cname,amt,desci);
				ins.close();
				HotOrNot ins1=new HotOrNot(PayActivity.this);
				ins1.openr();
				
				ins1.redbal(cname,amt);
				ins.close();
				finish();
	}
	
	 @Override
	 protected Dialog onCreateDialog(int id) {
	  return new DatePickerDialog(this, datePickerListener, year, month, day);
	 }

	 private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
	  public void onDateSet(DatePicker view, int selectedYear,
	    int selectedMonth, int selectedDay) {
	       String day1,month1;
	        

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
	   edate.setText(day1 + " / " + month1 + " / "
	     + selectedYear);
	   }
	 };
}
