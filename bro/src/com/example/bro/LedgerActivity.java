package com.example.bro;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class LedgerActivity extends ActionBarActivity implements OnClickListener{
	
	public static Activity fa;
	private Spinner s;
	ArrayList<String> listv;
	ArrayAdapter<String> adapter;
	private Button b1;
	private ListView listView; 
	private String array_spinner[];
	private TextView tv; 
	String[] values;
	   String[] itemsv={"Edit","Delete"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ledger);
		HotOrNot v=new HotOrNot(LedgerActivity.this);
		v.openr();
		fa=this;
		int cnt=v.getcount();
		System.out.println(cnt);
		tv=(TextView) findViewById(R.id.bal);
		listView=(ListView) findViewById(R.id.list);
		s = (Spinner) findViewById(R.id.spinner1);
		if(cnt>0)
		{
			Cursor r=v.getcname();
			int d=r.getColumnIndex(v.Com_Name);
			int j=0;
			array_spinner=new String[cnt];
			for(r.moveToFirst();!r.isAfterLast();r.moveToNext()){
				array_spinner[j++]=r.getString(d);
				
			}
			
	        ArrayAdapter adapter = new ArrayAdapter(this,
	        android.R.layout.simple_spinner_item, array_spinner);
	        s.setAdapter(adapter);
	        b1=(Button)findViewById(R.id.button1);
	        b1.setOnClickListener(this);
	        
		}
		registerForContextMenu(listView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ledger, menu);
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
		String cname=(String) s.getSelectedItem();
		int c,c1,c2,c3;
		HotOrNot re=new HotOrNot(LedgerActivity.this);
		re.openr();
		c1=re.getpaycount(cname);
		c2=re.gettrancount(cname);
		c3=re.getforwadedbalcount(cname);
		
		c=c1+c2+c3;
		System.out.println("total strings"+c);
		 values = new String[c];
		Cursor cpay=re.getpaycur(cname);
		Cursor csales=re.getsalescur(cname);
		Cursor cforwad=re.getforwadedbal(cname);
		
		int salesid=csales.getColumnIndex(re.Sales_Id);
		int salesdate=csales.getColumnIndex(re.Sales_Date);
		int salesdes=csales.getColumnIndex(re.Sales_Des);
		int salesamt=csales.getColumnIndex(re.Sales_Amt);
		int csalesid=cforwad.getColumnIndex(re.Sales_Id);
		int csalesdate=cforwad.getColumnIndex(re.Sales_Date);
		int csalesdes=cforwad.getColumnIndex(re.Sales_Des);
		int csalesamt=cforwad.getColumnIndex(re.Sales_Amt);
		
		int j=0;
		for(cforwad.moveToFirst();!cforwad.isAfterLast();cforwad.moveToNext()){
			values[j]=cforwad.getString(csalesid);
			values[j]=values[j]+".) "+cforwad.getString(csalesdate);
			values[j]=values[j]+" "+cforwad.getString(csalesdes);
			values[j]=values[j]+" "+cforwad.getString(csalesamt);
			
			j++;
		}
		for(csales.moveToFirst();!csales.isAfterLast();csales.moveToNext()){
			values[j]="Sales"+csales.getString(salesid);
			values[j]=values[j]+".) "+csales.getString(salesdate);
			values[j]=values[j]+" "+csales.getString(salesdes);
			values[j]=values[j]+" "+csales.getString(salesamt);
			
			j++;
		}
		
		int payid=cpay.getColumnIndex(re.Sales_Id);
		int paydate=cpay.getColumnIndex(re.Sales_Date);
		int paydes=cpay.getColumnIndex(re.Sales_Des);
		int payamt=cpay.getColumnIndex(re.Sales_Amt);
		
		for(cpay.moveToFirst();!cpay.isAfterLast();cpay.moveToNext()){
			values[j]="Pay"+cpay.getString(payid);
			values[j]=values[j]+".) "+cpay.getString(paydate);
			values[j]=values[j]+" "+cpay.getString(paydes);
			values[j]=values[j]+" "+cpay.getString(payamt);
			
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
		//values=(String[])listv.toArray();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listv);
     		
              listView.setAdapter(adapter);
              String bal=re.getbal(cname);
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
	    for (int i = 0; i<itemsv.length; i++) {
	      menu.add(Menu.NONE, i, i, itemsv[i]);
	    }
	  }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  int menuItemIndex = item.getItemId();
	  String menuItemName = itemsv[menuItemIndex];
	  String listItemName = values[info.position];
	  String idi=null;
	  
	  if(listItemName.startsWith("Sales"))
	  {
		  idi=listItemName.substring(5, listItemName.lastIndexOf("."));  
		  if(menuItemIndex==0)
		  {
		  	Intent in1=new Intent("com.example.bro.UpdateEntryActivity");
		  	
		  	in1.putExtra("ID",idi );
		  	
		  	startActivity(in1);
		  	
		  }
		  else
		  {
		  	HotOrNot de=new HotOrNot(LedgerActivity.this);
		  	
		  	de.openr();
		  	System.out.println("looking for cursor");
			Cursor c=de.getSalesEntry(idi);

		  	System.out.println("got cursor");
			c.moveToFirst();
			int salescomp=c.getColumnIndex(de.Sales_Cname);
			int salesamt=c.getColumnIndex(de.Sales_Amt);
			int i=Integer.parseInt(c.getString(salesamt));

		  	System.out.println("got i"+i);
			String cooom=c.getString(salescomp);
			i=-1*i;
		  	de.close();
		 	de.open();
		 	System.out.println("delete sales");
	  	de.delsal(idi);

	 	System.out.println("updating balance");
	  	de.updatebalforedittedsales(i, cooom);

	 	System.out.println("updated balance");
	  	de.close();
	  	System.out.println(info.position);
	  	adapter.remove(adapter.getItem(info.position));
	  	System.out.println(" after position");
	  	
	  	
	  	adapter.notifyDataSetChanged();
	  	HotOrNot re1=new HotOrNot(LedgerActivity.this);
	    re1.openr();
	  	String bal=re1.getbal(cooom);
          tv.setText("Balance: "+bal); 
	re1.close();
		
		  }
 
	  }
	  else if(listItemName.startsWith("Pay"))
	  {
		  idi=listItemName.substring(3, listItemName.lastIndexOf("."));  
		  if(menuItemIndex==0)
		  {
		  	Intent in1=new Intent("com.example.bro.UpdateEntry2Activity");
		  	
		  	in1.putExtra("ID",idi );
		  	startActivity(in1);
		  }
		  else
		  {
			 	HotOrNot de=new HotOrNot(LedgerActivity.this);
			  	de.openr();
				Cursor c=de.getPayEntry(idi);
				c.moveToFirst();
				int salescomp=c.getColumnIndex(de.Sales_Cname);
				int salesamt=c.getColumnIndex(de.Sales_Amt);
				int i=Integer.parseInt(c.getString(salesamt));
				String cooom=c.getString(salescomp);
				i=-1*i;
			  	de.close();
			 	de.open();
			  	de.delPay(idi);
			  	de.updatebalforedittedPay(i, cooom);

			  	de.close();
			  	adapter.remove(adapter.getItem(info.position));

//			  	listv.remove(info.position);
			  	adapter.notifyDataSetChanged();
			  	HotOrNot re1=new HotOrNot(LedgerActivity.this);
			    re1.openr();
			  	String bal=re1.getbal(cooom);
	              tv.setText("Balance: "+bal); 
			re1.close();
				
			  	
		  }
 
	  }
	  else
	  {
		  idi=listItemName.substring(0, listItemName.lastIndexOf("."));  
		  if(menuItemIndex==0)
		  {
		  	Intent in1=new Intent("com.example.bro.UpdateForwadEntryActivity");
		  	
		  	in1.putExtra("ID",idi );
		  	startActivity(in1);
		  }
		  else
		  {
			 	HotOrNot de=new HotOrNot(LedgerActivity.this);
			  	de.openr();
				Cursor c=de.getForwadEntry(idi);
				c.moveToFirst();
				int salescomp=c.getColumnIndex(de.Sales_Cname);
				int salesamt=c.getColumnIndex(de.Sales_Amt);
				int i=Integer.parseInt(c.getString(salesamt));
				String cooom=c.getString(salescomp);
				
			  	de.close();
			 	de.open();
			  	de.delFor(idi);
			  	de.updatebalforwadPay(i, cooom);

			  	de.close();
			  	adapter.remove(adapter.getItem(info.position));

//			  	listv.remove(info.position);
			  	adapter.notifyDataSetChanged();
			    HotOrNot re1=new HotOrNot(LedgerActivity.this);
			    re1.openr();
			  	String bal=re1.getbal(cooom);
	              tv.setText("Balance: "+bal); 
			re1.close();
				  	
			  	
		  }
 
	  }
	  //tv.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
	  return true;
	}
}
