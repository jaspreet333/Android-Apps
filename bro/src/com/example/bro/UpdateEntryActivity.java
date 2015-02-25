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
import android.widget.Spinner;
import android.widget.TextView;

public class UpdateEntryActivity extends ActionBarActivity implements OnClickListener{

	private EditText etdate;//For sales updatation
	private EditText etdesc;//For sales updatation
	private Calendar cal;

	private EditText etrate;//For sales updatation
	
	private EditText etquan;//For sales updatation
	
	private EditText etamt;//For sales updatation
	
	private EditText s;//For sales updatation
	private Button b1;
	private ImageButton ib;
	String id=null;
	String amt=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent in=getIntent();
		setContentView(R.layout.activity_update_entry);
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
		etdesc=(EditText)findViewById(R.id.etdesc);
		
		etquan=(EditText)findViewById(R.id.etquan);
		
		etrate=(EditText)findViewById(R.id.etrate);
		etamt=(EditText)findViewById(R.id.etamt);
		b1=(Button)findViewById(R.id.button1);
		s=(EditText)findViewById(R.id.spinner1);
		HotOrNot ins=new HotOrNot(UpdateEntryActivity.this);
		ins.openr();
		Cursor c=ins.getSalesEntry(id);
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
		etquan.setText(c.getString(quanti));
		etrate.setText(c.getString(salesrate));
		etamt.setText(amt);
		s.setText(c.getString(salescomp));
		s.setEnabled(false);
		ins.close();
		b1.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_entry, menu);
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
			String rate=etrate.getText().toString();
			String qua=etquan.getText().toString();
			String amit=etamt.getText().toString();
			String cooom=s.getText().toString();
			
			HotOrNot ini=new HotOrNot(UpdateEntryActivity.this);
			ini.open();
			ini.updateSales(date,des,rate,qua,amit,id);
			System.out.println("Till here balle");
			System.out.println("amt"+amt+"amit "+amit);
			
			if(Integer.parseInt(amt)!=Integer.parseInt(amit))
			{
				ini.updatebalforedittedsales((Integer.parseInt(amit)-Integer.parseInt(amt)),cooom);
			}
			System.out.println("After here balle");
			
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
