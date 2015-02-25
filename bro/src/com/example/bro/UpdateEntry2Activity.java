package com.example.bro;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

public class UpdateEntry2Activity extends ActionBarActivity implements OnClickListener{

	private EditText etdate;//For Pay updatation
	private EditText etdesc;//For Pay updatation
	
	
	private EditText etamt;//For sales updatation
	
	private EditText s;//For sales updatation
	private Button b1;
	private ImageButton ib;
	String id=null;
	String amt=null;
	private Calendar cal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_entry2);
		Intent in=getIntent();
		 cal = Calendar.getInstance();
		
		ib = (ImageButton) findViewById(R.id.imageButton1);
	       ib.setOnClickListener((new OnClickListener() {
	    	   
	            @Override
	            public void onClick(View v) {
	                 
	                // On button click show datepicker dialog 
	                showDialog(0);
	 
	            }
	 
	        }));
	
		id=in.getStringExtra("ID");
		etdate=(EditText)findViewById(R.id.etdate);
		etdesc=(EditText)findViewById(R.id.etdes);
		etamt=(EditText)findViewById(R.id.etamt);
		b1=(Button)findViewById(R.id.button1);
		s=(EditText)findViewById(R.id.spinner1);
		HotOrNot ins=new HotOrNot(UpdateEntry2Activity.this);
		ins.openr();
		Cursor c=ins.getPayEntry(id);
		c.moveToFirst();
		int salescomp=c.getColumnIndex(ins.Sales_Cname);
		int salesdate=c.getColumnIndex(ins.Sales_Date);
		int salesdes=c.getColumnIndex(ins.Sales_Des);
		int salesrate=c.getColumnIndex(ins.Sales_Rate);
		int quanti=c.getColumnIndex(ins.Sales_Quan);
		int salesamt=c.getColumnIndex(ins.Sales_Amt);
		 amt=c.getString(salesamt);
		etdate.setText(c.getString(salesdate));
		etdesc.setText(c.getString(salesdes));
		etamt.setText(amt);
		s.setText(c.getString(salescomp));
		s.setEnabled(false);
		ins.close();
		b1.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_entry2, menu);
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
		if(v.getId()==R.id.button1)
		{
			String date=etdate.getText().toString();
			String des=etdesc.getText().toString();
			String amit=etamt.getText().toString();
			String cooom=s.getText().toString();
			
			HotOrNot ini=new HotOrNot(UpdateEntry2Activity.this);
			ini.open();
			ini.updatePay(date,des,amit,id);
			if(Integer.parseInt(amt)!=Integer.parseInt(amit))
			{
				ini.updatebalforedittedPay(Integer.parseInt(amit)-Integer.parseInt(amt),cooom);
			}
			ini.close();
			finish();
			LedgerActivity.fa.finish();
			Intent intent3=new Intent("com.example.bro.LedgerActivity");
			 startActivity(intent3);
		}
	}
	 @Override
	 protected Dialog onCreateDialog(int id) {
	  return new DatePickerDialog(this, datePickerListener,
      cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
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
	   etdate.setText(day1 + " / " + month1 + " / "
	     + selectedYear);
	   }
	 };

}
