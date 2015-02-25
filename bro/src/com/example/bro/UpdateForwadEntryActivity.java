package com.example.bro;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UpdateForwadEntryActivity extends ActionBarActivity implements OnClickListener{

	String id=null;
	EditText et;
	Button b1;
	String amt;
	String cooom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_forwad_entry);
		Intent in=getIntent();
		id=in.getStringExtra("ID");
		et=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		HotOrNot ins=new HotOrNot(UpdateForwadEntryActivity.this);
		ins.openr();
		Cursor c=ins.getForwadEntry(id);
		
		c.moveToFirst();
		int salesamt=c.getColumnIndex(ins.Sales_Amt);
		int cint=c.getColumnIndex(ins.Sales_Cname);
		 amt=c.getString(salesamt);
		 cooom=c.getString(cint);
		et.setText(amt);
		ins.close();
		b1.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_forwad_entry, menu);
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
			String amit=et.getText().toString();
			HotOrNot ini=new HotOrNot(UpdateForwadEntryActivity.this);
			ini.open();
			if(Integer.parseInt(amt)!=Integer.parseInt(amit))
			{
				ini.updatebalforedittedsales(Integer.parseInt(amit)-Integer.parseInt(amt), cooom);
				ini.updateforwadentry(amit,id);
				
			}
			ini.close();
			finish();
			LedgerActivity.fa.finish();
			Intent intent3=new Intent("com.example.bro.LedgerActivity");
			 startActivity(intent3);
		}
	}
}
