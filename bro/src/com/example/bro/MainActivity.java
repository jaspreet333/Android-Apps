package com.example.bro;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 listView = (ListView) findViewById(R.id.list);
		 
		 String[] values = new String[] { "Drum In Entry", 
                 "Sales Entry",
                 "Payment Received Entry",
                 "Ledger of Party", 
                 "Drum Stock"
		 };
		 

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
           android.R.layout.simple_list_item_1, android.R.id.text1, values);
		 
         listView.setAdapter(adapter);
         
         listView.setOnItemClickListener(new OnItemClickListener() {
        	 
        	 @Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int itemPosition     = position;
				
				
				switch(itemPosition)
				{
				
				
				case 0:
					 Intent intent=new Intent("com.example.bro.DruminentryActivity");
					 startActivity(intent);
					 break;
				case 1:
					 Intent intent1=new Intent("com.example.bro.SalesActivity");
					 startActivity(intent1);
					 break;
					 
				case 2:
					 Intent intent2=new Intent("com.example.bro.PayActivity");
					 startActivity(intent2);
					 break;
					 
				case 3:
					 Intent intent3=new Intent("com.example.bro.LedgerActivity");
					 startActivity(intent3);
					 break;
					 
				case 4:
					 Intent intent4=new Intent("com.example.bro.DrumstockActivity");
					 startActivity(intent4);
					 break;
					 
				
				}
				
			}

        }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
