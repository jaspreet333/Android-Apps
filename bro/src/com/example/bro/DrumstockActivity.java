package com.example.bro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class DrumstockActivity extends ActionBarActivity implements OnClickListener{

private Spinner s;
ArrayList<String> listv;
String[] values;
String capacity;	
private Button b1;
private ListView listView; 
private String array_spinner[];
private TextView tv; 
String itemsv="Delete";
ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drumstock);
		tv=(TextView) findViewById(R.id.bal);
		listView=(ListView) findViewById(R.id.list);
		s = (Spinner) findViewById(R.id.spinner1);
	
		array_spinner=new String[6];
        array_spinner[0]="5 ltr";
        array_spinner[1]="10 ltr";
        array_spinner[2]="20 ltr";
        array_spinner[3]="35 ltr";
        array_spinner[4]="50 ltr";
        array_spinner[5]="200 ltr";
        s = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(this,
        android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
        b1=(Button)findViewById(R.id.button1);
        b1.setOnClickListener(this);
        registerForContextMenu(listView);
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drumstock, menu);
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
		 capacity=(String) s.getSelectedItem();
		int c;
		HotOrNot re=new HotOrNot(DrumstockActivity.this);
		re.openr();
		c=re.getcountdrumentry(capacity);
		
		
		//c=c1+c2;
		//System.out.println(c);
		values = new String[c];
		//Cursor cpay=re.getpaycur(cname);
		Cursor csales=re.getdrumentry(capacity);
		int salesid=csales.getColumnIndex(re.Drum_RowID);
		int salesdate=csales.getColumnIndex(re.Drum_date);
		int salesdes=csales.getColumnIndex(re.Drum_desci);
		int salesamt=csales.getColumnIndex(re.Drum_Quantity);
		int inout=csales.getColumnIndex(re.Drum_inout);
		
		int j=0;
		for(csales.moveToFirst();!csales.isAfterLast();csales.moveToNext()){
			
			values[j]=csales.getString(inout)+" Entry ";
			
			values[j]=values[j]+csales.getString(salesid);
			values[j]=values[j]+".) "+csales.getString(salesdate);
			
			values[j]=values[j]+" "+csales.getString(salesdes);
			values[j]=values[j]+"     "+csales.getString(salesamt);
			
			j++;
		}
		
		int k;
		listv=new ArrayList<String>();
		for(k=0;k<values.length;k++)
		{
			
			listv.add(values[k]);
			
		}
		if(c>0)
		{	
		Collections.sort(listv,new Comparator<String>()
	{
			 @Override
		      public int compare(String o1, String o2) {
				 
				 SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");
				 String var;
		        Date date1;
		        Date date2;
		        try {
		        	var=o1.substring(o1.lastIndexOf(")")+2);
		        	var=var.substring(0,14);
		        	System.out.println("var1 "+var);
		        	date1 = (Date) format.parse(var);
		        	var=o2.substring(o2.lastIndexOf(")")+2);
		        	var=var.substring(0,14);
		        	System.out.println("var2 "+var);
		        	date2 = (Date) format.parse(var);
		          
		        } catch (ParseException e) {
		          throw new IllegalArgumentException("Could not parse date!", e);
		          
		          
		        }

		        return date1.compareTo(date2);
		      }	
	}
	
	);
		}

	     adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listv);
     		 
              listView.setAdapter(adapter);
              String bal=re.getbaldrum(capacity);
              tv.setText("Balance: "+bal); 
              re.close();
	
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.list) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	    menu.setHeaderTitle(values[info.position]);
	 
	    //String[] menuItems = getResources().getStringArray(R.menu.ledger);
	        menu.add(Menu.NONE, 0, 0, itemsv);
	  }
	  
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  int menuItemIndex = item.getItemId();
	  String listItemName = values[info.position];
	  String idi=null;
	  
	  if(listItemName.startsWith("in"))
	  {
		  	idi=listItemName.substring(9, listItemName.lastIndexOf("."));  
			HotOrNot de=new HotOrNot(DrumstockActivity.this);
		  	
		  	de.openr();
		  	System.out.println("looking for cursor");
			Cursor c=de.getdumEntry(idi);

		  	System.out.println("got cursor");
			c.moveToFirst();
			int quany=c.getColumnIndex(de.Drum_Quantity);

			String qua=c.getString(quany);
		  	de.close();
		 	de.open();
		 	System.out.println("delete sales");
	  	de.deldrumentry(idi);

	 	System.out.println("updating balance");
	  	de.decdrum(qua, capacity);

	 	System.out.println("updated balance");
	  	de.close();
	  	System.out.println(info.position);
	  	adapter.remove(adapter.getItem(info.position));
	  	System.out.println(" after position");
	  	
	  	
	  	adapter.notifyDataSetChanged();
	  	HotOrNot re1=new HotOrNot(DrumstockActivity.this);
	    re1.openr();
			  
        String bal=re1.getbaldrum(capacity);
        tv.setText("Balance: "+bal); 
        re1.close();
	  
		  
 
	  }
	  else if(listItemName.startsWith("out"))
	  {

			  	idi=listItemName.substring(10, listItemName.lastIndexOf("."));  
				HotOrNot de=new HotOrNot(DrumstockActivity.this);
			  	
			  	de.openr();
			  	System.out.println("looking for cursor");
				Cursor c=de.getdumEntry(idi);

			  	System.out.println("got cursor");
				c.moveToFirst();
				int quany=c.getColumnIndex(de.Drum_Quantity);

				String qua=c.getString(quany);
			  	de.close();
			 	de.open();
			 	System.out.println("delete sales");
		  	de.deldrumentry(idi);

		 	System.out.println("updating balance");
		  	de.incdrum(qua, capacity);

		 	System.out.println("updated balance");
		  	de.close();
		  	System.out.println(info.position);
		  	adapter.remove(adapter.getItem(info.position));
		  	System.out.println(" after position");
		  	
		  	
		  	adapter.notifyDataSetChanged();
		  	
		    HotOrNot re1=new HotOrNot(DrumstockActivity.this);
		    re1.openr();
				  
            String bal=re1.getbaldrum(capacity);
            tv.setText("Balance: "+bal); 
            re1.close();
		  
 
	  }
	  //tv.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
	  return true;
	}

}
