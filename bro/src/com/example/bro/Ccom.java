package com.example.bro;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Ccom extends ActionBarActivity implements OnClickListener{

	private EditText e1;
	private EditText e2;
	private Button b1;
	private Calendar cal;
	 private int day;
	 private int month;
	 private int year;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ccom);
		 e1=(EditText)findViewById(R.id.editText1);
		 e2=(EditText)findViewById(R.id.editText2);
		 b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ccom, menu);
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
		HotOrNot in=new HotOrNot(Ccom.this);
		String name=e1.getText().toString();
		String bal=e2.getText().toString();
		
		in.open();
		in.createcomp(name,bal);
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
        String cdate=day1 + " / " + month1 + " / " + year; 

		if(Integer.parseInt(bal)>0)
		in.createforwadbal(name,bal,"Forwaded Balance",cdate);
		
		in.close();
		 Intent intent1=new Intent("com.example.bro.SalesActivity");
		 startActivity(intent1);
		 finish();
	}
}
