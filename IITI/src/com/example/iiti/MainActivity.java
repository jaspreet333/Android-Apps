package com.example.iiti;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.app.FragmentTransaction;
import android.support.v4.app.ActionBarDrawerToggle;

//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

    private static String[] mPlanetTitles  = {"Home","Time Table","News","Event","About Us"};
    static ArrayAdapter<String> adapter;
    static View rv;
    static View rvnews;
    static View rvevents;
    static View rvtimetable;
    
   /* final String[] fragments ={
            "com.example.iiti.FragmentOne",
            "com.example.iiti.FragmentTwo",
            "com.example.iiti.FragmentThree","com.example.iiti.FragmentThree"};
    */
    private DrawerLayout mDrawerLayout;
    private static ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private static CharSequence mTitle;
    static Controller aController;
    static Context context;
    static Display display;
    Point size;
    
    static int width ;
    static int height;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=getApplicationContext();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mTitle = mDrawerTitle = getTitle();
//        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
  //      mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    //    mDrawerList = (ListView) findViewById(R.id.left_drawer);
		aController = (Controller) getApplicationContext();
         display = getWindowManager().getDefaultDisplay();
         size = new Point();
        display.getSize(size);
         width = size.x;
         height = size.y;
         if (!aController.isConnectingToInternet()) {
             
             // Internet Connection is not present
             aController.showAlertDialog(MainActivity.this,
                     "Internet Connection Error",
                     "Please connect to working Internet connection", false);
              
             // stop executing code by return
            
         }
        // Set the adapter for the list view

        // set a custom shadow that overlays the main content when the drawer opens
      //  mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,  R.layout.drawer_list_item, mPlanetTitles));
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        registerReceiver(mHandleMessageReceiver, new IntentFilter(
                Config.DISPLAY_MESSAGE_ACTION));
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
        
        
	}
	
	  private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
	         
	        @Override
	        public void onReceive(Context context, Intent intent) {
	             
	            String type = intent.getExtras().getString("type");
	           
	            if(type.equals("News"))
	            {    
	            // Waking up mobile if it is sleeping
	            aController.acquireWakeLock(getApplicationContext());
	           
	            CardView as=(CardView)rv.findViewWithTag(type);
	            // Display message on the screen
	         //   lblMessage.append(newMessage + "");         
	           // adapter.remove(adapter.getItem(info.position));
	    	  //adapter.add(newMessage);
	            //System.out.println(" after position");
	    	  	
	        	TextView nw=(TextView)as.findViewWithTag("please");
	        	TextView nw1=(TextView)as.findViewWithTag("mainmess");
		    	  
	    	  	System.out.println("This is the content"+ intent.getExtras().getString("content") +"got it or not");
	    	  	if(intent.getExtras().getString("title")!=null)
		    	  	nw.setText(intent.getExtras().getString("title"));
		    	  	//adapter.notifyDataSetChanged();
	    	  	if(nw1!=null&&intent.getExtras().getString("content")!=null)
		    	  	nw1.setText(intent.getExtras().getString("content"));
		    	else if(intent.getExtras().getString("content")!=null)
		    	{
		    		TextView arb=new TextView(as.getContext());
		    		arb.setTag("mainmess");
		    		arb.setText(intent.getExtras().getString("content"));
		    		arb.setTextSize(18);
				      arb.setTextColor(Color.BLUE);
		    			  nw.measure(0, 0);
		    	          
		              	int alp=(50+((nw.getMeasuredWidth()/width)*30));
		              	//System.out.println("In this piece");
		              	arb.setPadding(5, alp, 0, 0);
		              	arb.setVisibility(View.VISIBLE);
		              	as.addView(arb);
		              	
		    			
		    			//end
		    		
		    	}  
	            Toast.makeText(getApplicationContext(), 
	                           "Got Message: " + type, 
	                           Toast.LENGTH_LONG).show();
	             
	            // Releasing wake lock
	            aController.releaseWakeLock();
	        }
	        if(type.equals("update fragment"))
            {    
            // Waking up mobile if it is sleeping
            aController.acquireWakeLock(getApplicationContext());
           
            FragmentManager fm = getFragmentManager();
        	
	        FragmentTransaction ft = fm.beginTransaction();
	
	        //wvf.updateUrl(link);
	Fragment gf=fm.findFragmentByTag("0");
	ft.detach(gf);
	ft.attach(gf);
	ft.commit();
	           
            // Releasing wake lock
            aController.releaseWakeLock();
        }
	        if(type.equals("Events"))
	            {    
	            // Waking up mobile if it is sleeping
	            aController.acquireWakeLock(getApplicationContext());
	           
	            CardView as=(CardView)rv.findViewWithTag(type);
	            // Display message on the screen
	         //   lblMessage.append(newMessage + "");         
	           // adapter.remove(adapter.getItem(info.position));
	    	  //adapter.add(newMessage);
	            //System.out.println(" after position");
	    	  	
	    	  	TextView nw=(TextView)as.findViewWithTag("please");
	    	  	TextView nw1=(TextView)as.findViewWithTag("mainmess");
	    	  	
	    	  	System.out.println("This is the content"+ intent.getExtras().getString("content") +"got it or not");
	    	  	
	    		nw.setText(intent.getExtras().getString("ename"));
		    	  	String gd=intent.getExtras().getString("content")+"\nDate:"+intent.getExtras().getString("date1")+"\tTime:"+intent.getExtras().getString("time1")+"\nVenue:"+intent.getExtras().getString("venue")+"\n"+intent.getExtras().getString("sender")+"\n"+intent.getExtras().getString("cname");
	    	  	//adapter.notifyDataSetChanged();
		    	  	if(nw1!=null)
			    	  	nw1.setText(gd);
			    	else
			    	{
			    		TextView arb=new TextView(as.getContext());
			    		arb.setTag("mainmess");
			    		arb.setText(gd);
			    		
			    			  nw.measure(0, 0);
			    	          
			              	int alp=(50+((nw.getMeasuredWidth()/width)*30));
			              	//System.out.println("In this piece");
			              	arb.setPadding(5, alp, 0, 0);
			              	arb.setVisibility(View.VISIBLE);
			              	arb.setTextSize(18);
						      arb.setTextColor(Color.BLUE);
			              	as.addView(arb);
			              	
			    			
			    			//end
			    		
			    	}
	            Toast.makeText(getApplicationContext(), 
	                           "Got Message: " + type, 
	                           Toast.LENGTH_LONG).show();
	             
	            // Releasing wake lock
	            aController.releaseWakeLock();
	        }   
	        }
	    };
	    @Override
	    protected void onDestroy() {
	        // Cancel AsyncTask
	        
	        try {
	            // Unregister Broadcast Receiver
	            unregisterReceiver(mHandleMessageReceiver);
	             
	            //Clear internal resources.
	            
	             
	        } catch (Exception e) {
	            Log.e("UnRegister Receiver Error",  "> " + e.getMessage());
	        }
	        super.onDestroy();
	    }

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
	        selectItem(position);
	    }
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
	    // Create a new fragment and specify the planet to show based on position
	    Fragment fragment = new PlanetFragment();
	    Bundle args = new Bundle();
	   
	    args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
	  
	    fragment.setArguments(args);
	   
	
       
	    // Insert the fragment by replacing any existing fragment
	    FragmentManager fragmentManager = getFragmentManager();
	    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment,Integer.toString(position)).commit();

	    // Highlight the selected item, update the title, and close the drawer
	    mDrawerList.setItemChecked(position, true);
	    setTitle(mPlanetTitles[position]);
	    mDrawerLayout.closeDrawer(mDrawerList);
	    
	}

	@Override
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    
	    
	    getActionBar().setTitle(mTitle);
	}
	public static void csetTitile(CharSequence title) {
	    mTitle = title;
	    
	    ActionBarActivity activity = (ActionBarActivity)context;
	    activity.getActionBar().setTitle(mTitle);
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
		if (mDrawerToggle.onOptionsItemSelected(item)) {
		      return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    
    
    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PlanetFragment extends Fragment {
        
    	public static final String ARG_PLANET_NUMBER = "planet_number";
        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            
        	int i = getArguments().getInt(ARG_PLANET_NUMBER);
        	View rootView = null;
        	ListView lv = null;
        	Context cont=container.getContext();
        	switch(i)
        	{
        	
        	case 0:
        		rootView =gethome(inflater,container,cont); 
        
          	    break;
        	case 1:
        		rootView = gettimetable(inflater,container,cont);
        		//inflater.inflate(R.layout.quiz, container, false);
        		
        		
        		break;
        	case 2:
        		
        		
        		
        		rootView = getnews(inflater,container,cont);
        		break;
        	case 3:
        		
        		
        		
        		
        		rootView = getevents(inflater,container,cont);
        		break;
      
        		
        	}

            int imageId = getResources().getIdentifier(mPlanetTitles[i],"drawable", getActivity().getPackageName());
            //((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(mPlanetTitles[i]);
            return rootView;
        }
        
        
    }
	public static View gethome(LayoutInflater inflater, ViewGroup container, final Context cont) {
		// TODO Auto-generated method stub
		 rv=inflater.inflate(R.layout.home, container, false);
		String[] items ={"News","Events","Time Table"};
		 final TextView[] tv2 = new TextView[3];
   	     
        final ViewGroup dismissableContainer = (ViewGroup) rv.findViewById(R.id.dismissable_container);
        for (int i = 0; i < items.length; i++) {
            final CardView cv = new CardView(rv.getContext());
            final int[] dcar = {0,0,0};
            cv.setRight(5);
            cv.setLeft(5);
            cv.setTag(items[i]);
            final int ch=height/6;
            //cv.setMinimumHeight(ch);
            
            TextView tv=new TextView(cv.getContext());
            tv.setTag("heading");
             tv.setTextSize(8);
            tv.setTextColor(Color.RED);
             tv.setText(items[i]);
             tv.setPadding(5, 5, 0,0);
             cv.addView(tv);
            TextView tv1=new TextView(cv.getContext());
           tv1.setTag("please");
            tv1.setTextSize(22);
            tv1.setTextColor(Color.BLUE);
            
            tv1.setText(items[i]);
            HotOrNot re=new HotOrNot(rv.getContext());
          tv2[i]=new TextView(cv.getContext());
            tv2[i].setTag("mainmess");
            tv2[i].setTextSize(18);
            tv2[i].setText(items[i]);
            tv2[i].setTextColor(Color.BLUE);
            tv2[i].setVisibility(View.INVISIBLE);
            cv.measure(0, 0);
             switch(i)
            {
            case 0:
        		
        		re.openr();
        		
        		
        		
        		//c=c1+c2;
        		//System.out.println(c);
        		
        		//Cursor cpay=re.getpaycur(cname);
        		Cursor csales=re.getnewsentry();
        	if(csales!=null){
        		int title=csales.getColumnIndex(re.news_title);
        		int conti=csales.getColumnIndex(re.news_content);
        		String ftitle=null;
        		String fcont=null;
        		if(!aController.registeredOnServer(context))
        		 {
        			ftitle="Sorry man you are not registered on our server yet";
        			fcont="Sorry man you are not registered on our server yet";
        		 }
        		 else
        		 {
        			 ftitle="Oops No news to be displayed";
        			 fcont="Oops No news to be displayed";
        		 }
        			
        		
        		
        		int j=0;
        		for(csales.moveToFirst();!csales.isAfterLast();csales.moveToNext()){
        			
        			ftitle=csales.getString(title);
        			fcont=csales.getString(conti);
        			
        			j++;
        		}

            tv1.setText(ftitle);
            tv2[i].setText(fcont);
            //tv1.setPadding(5, 15, 0,0);
            //tv2[i].setPadding(5, 50, 0, 0);
            
        	}
        	
            break;
            case 1:
re.openr();
        		
        		
        		
        		//c=c1+c2;
        		//System.out.println(c);
        		
        		//Cursor cpay=re.getpaycur(cname);
        		Cursor csales1=re.getmaxeventsentry();
        	if(csales1!=null){
        		int title=csales1.getColumnIndex(re.events_name);
        		int g1=csales1.getColumnIndex(re.events_content);
        		int g2=csales1.getColumnIndex(re.events_date);
        		int g3=csales1.getColumnIndex(re.events_clubname);
        		int g4=csales1.getColumnIndex(re.events_sender);
        		int g5=csales1.getColumnIndex(re.events_time);
        		int g6=csales1.getColumnIndex(re.events_venue);
        		String ftitle=null;
        		String fcont=null;
        		if(!aController.registeredOnServer(context))
       		 {
       			ftitle="Sorry man you are not registered on our server yet";
       			fcont="Sorry man you are not registered on our server yet";
       		 }
       		 else
       		 {
       			 ftitle="Oops No Events to be displayed";
       			 fcont="Oops No Events to be displayed";
       		 }
        		int j=0;
        		for(csales1.moveToFirst();!csales1.isAfterLast();csales1.moveToNext()){
        			
        			ftitle=csales1.getString(title);
        			fcont=csales1.getString(g1);
        			fcont=fcont+"\n"+"Date:"+csales1.getString(g2);
        			fcont=fcont+"\t"+"Time:"+csales1.getString(g5);
        			fcont=fcont+"\t"+"Venue:"+csales1.getString(g6);
        			fcont=fcont+"\n"+csales1.getString(g4);
        			fcont=fcont+"\n"+"Club:"+csales1.getString(g3);
        			j++;
        		}

            tv1.setText(ftitle);
            tv2[i].setText(fcont);
            //tv2.setMaxHeight(60);
            //tv1.setPadding(5, 15, 0,0);
            }
            	break;
            case 2:
            if(aController.gottimetableornot(context))
            {	
        		re.openr();
        		
        		tv2[i].setTextSize(18);	
        	tv1.setTextSize(18);	
        		//c=c1+c2;
        		System.out.println("Setting time table");
        		
        		//Cursor cpay=re.getpaycur(cname);
        		 csales1=re.getappropriatetimetable();
        	if(csales1!=null){
        		int time1=csales1.getColumnIndex(re.timein);
        		int time2=csales1.getColumnIndex(re.timeout);
        		int details=csales1.getColumnIndex(re.details);
        		String ftime=null;
        		
        		System.out.println("Csales1 is not null ");
        		int d=0;
        		if(csales1.getCount()==0)
        		{
        			tv1.setText("Nothing to be displayed");
        			tv2[i].setText("Nothing to be displayed");
        		}
        		for(csales1.moveToFirst();!csales1.isAfterLast();csales1.moveToNext()){
        			
        			
        			ftime=csales1.getString(time1);
        			
        			ftime=ftime+"-"+csales1.getString(time2);
        			
         			ftime=ftime+"  "+csales1.getString(details);
        			if(d==0)
        				tv1.setText(ftime);
        			else
        				tv2[i].setText(ftime);
        			d++;
        		}
        		

            }
        	else 
        	{	
        		
        		System.out.println("Csales1 is null ");
        		
        	}
        	
            }
            tv1.measure(0, 0);
       		
   System.out.println("Screen width: "+width+"line count: "+tv1.getMeasuredWidth()/width);
                
            break;
            
            
            }
            
             tv1.measure(0, 0);
             
         	int alp=(50+((tv1.getMeasuredWidth()/width)*30));
         	//System.out.println("In this piece");
         	tv2[i].setPadding(5, alp, 0, 0);
         	
         	
            	tv1.setPadding(5, 15, 0,0);
            cv.addView(tv1); 
             cv.measure(0,0);
            dcar[i]=cv.getMeasuredHeight();
                  
         
          System.out.println(tv1.getMeasuredHeight()+" this is finally it");
          System.out.println(" Card heights "+dcar[i]);
          
         cv.setOnClickListener(new CardView.OnClickListener()
         {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final TextView a=(TextView )v.findViewWithTag("please");
				//final TextView b=(TextView )v.findViewWithTag("mainmess");
				 v.measure(0, 0);
			
				 
           final int h1=v.getMeasuredHeight();
           System.out.println(h1+"this is the height of card");
        int fh=0;   
      		String ctad2=(String) v.getTag();							//Replace fragment here man
    	   	
    	   	if(ctad2.equals("News"))
    	   	{	fh=dcar[0];
    	   	
    	   	}
    	   	else if(ctad2.equals("Events"))
    	   	{
    	   		fh=dcar[1];
    	   	}
    	   	else if(ctad2.equals("Time Table"))
    	   {
    	   		fh=dcar[2];
    	   }
    	System.out.println("h1: "+h1+" fh :"+fh);
           if(h1>fh) 
        	   {
        	   	
          		String ctad=(String) v.getTag();							//Replace fragment here man
        	   	int d = 0;
        	   	if(ctad.equals("News"))
        	   		d=2;
        	   	else if(ctad.equals("Events"))
        	   		d=3;
        	   	else if(ctad.equals("Time Table"))
        	   d=1;
        	    Fragment fragment = new PlanetFragment();
        	    Bundle args = new Bundle();
        	   
        	    args.putInt(PlanetFragment.ARG_PLANET_NUMBER, d);
        	  
        	    fragment.setArguments(args);
        	   
        	
        	    Activity activity = (Activity)v.getContext();
        	    // Insert the fragment by replacing any existing fragment
        	    FragmentManager fragmentManager = activity.getFragmentManager();
        	    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment,Integer.toString(d)).commit();

        	    // Highlight the selected item, update the title, and close the drawer
        	    mDrawerList.setItemChecked(d, true);
        	    //csetTitile(mPlanetTitles[d]);
        	    
        	   }
           else
           {
        	   String ctad14=(String) v.getTag();							//Replace fragment here man
       	   	
       	   	if(ctad14.equals("News"))
       	   	{
       	   		((CardView)v).addView(tv2[0]);
       	   		tv2[0].setVisibility(View.VISIBLE);
       	   	}
       	   	else if(ctad14.equals("Events"))
       	   	{
       	   		((CardView)v).addView(tv2[1]);
       	   	tv2[1].setVisibility(View.VISIBLE);
       	   	}
       	   	else if(ctad14.equals("Time Table"))
       	   	{
       	   		((CardView)v).addView(tv2[2]);
       	   	tv2[2].setVisibility(View.VISIBLE);
       	   	}
        	   /*b.measure(0, 0);
        	   v.measure(0, 0);
        	   int cline=b.getMeasuredWidth()/width;
        	   
        	   if(b.getMeasuredWidth()%width!=0)
        		   cline++;
        	  int hfg=h1+26*cline;
        	  System.out.println("Text of tv2 "+b.getText()+" "+cline+" "+ hfg);
        	  b.setVisibility(View.VISIBLE); 
        	  v.setMinimumHeight(hfg);  
              */
           }
          /*if(v!=(View)a)
          {
        	  
      	    Toast.makeText(cont,"Clicked Blank" + a.getText(),Toast.LENGTH_LONG).show();
      	    v.setMinimumHeight(h2);  
            
          }
          else
          {
        	  
       	    Toast.makeText(cont,
                   "Clicked Text MAn" + a.getText(),
                   Toast.LENGTH_LONG).show();  
       	    
          }
          */
        	
        	 
           /*a.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View texti) {
				// TODO Auto-generated method stub
				if(texti==a)
				{
					   
					   
				}
				else
				{
				       }
			}
		});*/
           
			}
        	 
         });
            
            // Create a generic swipe-to-dismiss touch listener.
            cv.setOnTouchListener(new SwipeDismissTouchListener(
                    cv,
                    null,
                    new SwipeDismissTouchListener.DismissCallbacks() {
                        @Override
                        public boolean canDismiss(Object token) {
                            return true;
                        }

                        @Override
                        public void onDismiss(View view, Object token) {
                            dismissableContainer.removeView(cv);
                        }
                    }));
            dismissableContainer.addView(cv);
        }


return rv;
	}
	public static View getnews(LayoutInflater inflater, ViewGroup container, final Context cont) {
		// TODO Auto-generated method stub
		 rvnews=inflater.inflate(R.layout.news, container, false);
		//String[] items ={"News","Quiz","Events","Mess"};
        
		 
        final ViewGroup dismissableContainer = (ViewGroup) rvnews.findViewById(R.id.dismissable_container);
        HotOrNot re=new HotOrNot(rvnews.getContext());
		re.openr();
		int c=re.getcountnews();
	    String val[]=new String[c]; 
	    String vald[]=new String[c]; 
		
	    Cursor csales=re.getallnewsentry();
	if(csales!=null){
		int title=csales.getColumnIndex(re.news_title);
		int dtitle=csales.getColumnIndex(re.news_content);
		
		int j=0;
		for(csales.moveToFirst();!csales.isAfterLast();csales.moveToNext()){
			
			val[j]=csales.getString(title);
			vald[j]=csales.getString(dtitle);
			j++;
		}

   
	}
	if(c==0)
	{
	       final CardView cv = new CardView(rvnews.getContext());
           cv.setRight(5);
           cv.setLeft(5);
           
           final int ch=height/6;
           cv.setMinimumHeight(ch);
           
           TextView tv1=new TextView(cv.getContext());
          tv1.setTag("please");
           tv1.setTextSize(22);
           if(aController.registeredOnServer(context))
           tv1.setText("No News to be displayed");
           else
        	   tv1.setText("You are not registered");
              
           
   		re.close();
           
   	 tv1.setTextColor(Color.RED);
    
                      tv1.setPadding(5, 15, 0,0);
                      
                      cv.addView(tv1);
           cv.setPadding(5, 5, 5,20);
        
           cv.setOnClickListener(new CardView.OnClickListener()
           {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
           	
           }
           		);
           // Create a generic swipe-to-dismiss touch listener.
           cv.setOnTouchListener(new SwipeDismissTouchListener(
                   cv,
                   null,
                   new SwipeDismissTouchListener.DismissCallbacks() {
                       @Override
                       public boolean canDismiss(Object token) {
                           return true;
                       }

                       @Override
                       public void onDismiss(View view, Object token) {
                           dismissableContainer.removeView(cv);
                       }
                   }));
           dismissableContainer.addView(cv);
		
	}
	
        for (int i = 0; i < c; i++) {
            final CardView cv = new CardView(rvnews.getContext());
            cv.setRight(5);
            cv.setLeft(5);
            
            final int ch=height/6;
            cv.setMinimumHeight(ch);
            
            TextView tv1=new TextView(cv.getContext());
           tv1.setTag("please");
            tv1.setTextSize(22);
            tv1.setText(val[i]);
            TextView tv2=new TextView(cv.getContext());
            tv2.setTag("mainmess");
            tv2.setTextSize(18);
            tv2.setText(vald[i]);
              tv1.setTextColor(Color.RED);
              tv2.setTextColor(Color.BLUE);
    		re.close();
            
            
    		 tv1.setPadding(5, 15, 0,0);
             tv1.measure(0,0);
             if(tv1.getMeasuredWidth()/width<1)
             	tv2.setPadding(5, 50, 0, 0);
             else
             	{
             	int alp=50+(tv1.getMeasuredWidth()/width)*30;
             	//System.out.println("In this piece");
             	tv2.setPadding(5, alp, 0, 0);
             	
             	}
                    
                       
                      
            cv.addView(tv1);
           cv.addView(tv2);
            cv.setPadding(5, 5, 5,20);
         
            cv.setOnClickListener(new CardView.OnClickListener()
            {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
            	
            }
            		);
            // Create a generic swipe-to-dismiss touch listener.
            cv.setOnTouchListener(new SwipeDismissTouchListener(
                    cv,
                    null,
                    new SwipeDismissTouchListener.DismissCallbacks() {
                        @Override
                        public boolean canDismiss(Object token) {
                            return true;
                        }

                        @Override
                        public void onDismiss(View view, Object token) {
                            dismissableContainer.removeView(cv);
                        }
                    }));
            dismissableContainer.addView(cv);
        }
	


return rvnews;
	}

	
	
	public static View gettimetable(LayoutInflater inflater, ViewGroup container, final Context cont) {
		// TODO Auto-generated method stub
		 rvtimetable=inflater.inflate(R.layout.timetable, container, false);
		//String[] items ={"News","Quiz","Events","Mess"};
        
		 
        final ViewGroup dismissableContainer = (ViewGroup) rvtimetable.findViewById(R.id.dismissable_container);
        String[] dayofint={"Monday","tuesday","Wednesday","Thursday","Friday"};
        for (int i = 0; i < 5; i++) {
            final CardView cv = new CardView(rvtimetable.getContext());
            cv.setRight(5);
            cv.setLeft(5);
            cv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            final int ch=200;
            //cv.setMinimumHeight(ch);
            HotOrNot re=new HotOrNot(rvtimetable.getContext());
    		re.openr();
    		int c=re.gettablerows(i);
    		TextView hed=new TextView(rvtimetable.getContext());
    		//hed.setTextSize(7);
    		hed.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
    		hed.setTextColor(Color.RED);
    		hed.setPadding(5, 5, 0,0);
    		cv.addView(hed);
    		hed.setText(dayofint[i]);
    	    String val[]=new String[c]; 
    		Cursor csales=re.gettimetableentry(i);
    		if(c==0)
    		{
    			TextView ds=new TextView(rvtimetable.getContext());
    			ds.setPadding(5, 15,0,0);
    			ds.setTextSize(18);
    			if(!aController.registeredOnServer(context))
    				ds.setText("You are not registered on ur server");
    			else
    				ds.setText("No plannings man You are free enjoy");
ds.setTextColor(Color.BLUE);
    			cv.addView(ds);
    		}
    	if(csales!=null){
    		int k11=csales.getColumnIndex(re.timein);
    		int k12=csales.getColumnIndex(re.timeout);
    		int k13=csales.getColumnIndex(re.details);
    		
    		int j=0;
    		for(csales.moveToFirst();!csales.isAfterLast();csales.moveToNext()){
    			
    			val[j]=csales.getString(k11);
    			
    			val[j]=val[j]+"-"+csales.getString(k12);
    			
    			val[j]=val[j]+"  "+csales.getString(k13);
    			
    			j++;
    		}

       
    	}
    	LinearLayout ll = new LinearLayout(rvtimetable.getContext());
    	ll.setOrientation(LinearLayout.VERTICAL);
    
    	ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    final TextView[] tv = new TextView[c];
    	 for(int dj=0;dj<c;dj++){
    	 tv[dj]=new TextView(rvtimetable.getContext());
    	 tv[dj].setLayoutDirection(LinearLayout.VERTICAL);
    	 
    	 tv[dj].setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    	 }
    	 int tan=0;
        for( int dj=0;dj<c;dj++){
        	 
           // System.out.println(val[dj]+" got or not"); 
        	tv[dj].setText(val[dj]);
             tv[dj].setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
             tv[dj].setTextColor(Color.BLUE);
             
     		re.close();
     		int dy=0; 
       		Point size = new Point();
     		display.getSize(size);
     		int width = size.x;
     		int height = size.y;
   int cwid=0;
   	     
   tv[dj].measure(0, 0);
      
     		if(dj!=0 )
     		{
     		
     		 
     			if((tv[dj-1].getMeasuredWidth()/width)==0)
     			dy=27*(dj+1+tan);
     			else
     				{
     				
     				dy=27*(tan+dj+1+(tv[dj-1].getMeasuredWidth()/width));
     				tan+=(tv[dj-1].getMeasuredWidth()/width);
     				}
    
     		}
     		else
     			dy=21*(dj+1);
 			
     		if(i==1)
     		{
     			System.out.println("This is widht of textview"+tv[dj].getMeasuredWidth());
     			System.out.println(tv[dj].getText()+" linecount"+(tv[dj].getMeasuredWidth()/width));
     		}
                        tv[dj].setPadding(5, dy, 0,0);
             cv.addView(tv[dj]);
            
                       	
        }
        cv.setPadding(5, 5, 5,20);
        
        cv.setOnClickListener(new CardView.OnClickListener()
        {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        	
        }
        		);
        
        // Create a generic swipe-to-dismiss touch listener.
        cv.setOnTouchListener(new SwipeDismissTouchListener(
                cv,
                null,
                new SwipeDismissTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(Object token) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view, Object token) {
                        dismissableContainer.removeView(cv);
                    }
                }));
        dismissableContainer.addView(cv);

}


return rvtimetable;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	public static View getevents(LayoutInflater inflater, ViewGroup container, final Context cont) {
		// TODO Auto-generated method stub
		 rvevents=inflater.inflate(R.layout.events, container, false);
		//String[] items ={"News","Quiz","Events","Mess"};
       
		 
       final ViewGroup dismissableContainer = (ViewGroup) rvevents.findViewById(R.id.dismissable_container);
       HotOrNot re=new HotOrNot(rvevents.getContext());
		re.openr();
		int c=re.getcountevents();
	    String val[]=new String[c]; 
	    String vald[]=new String[c];
	    Cursor csales=re.geteventsentry();
	if(csales!=null){
		int title=csales.getColumnIndex(re.events_name);
		int g1=csales.getColumnIndex(re.events_content);
		int g2=csales.getColumnIndex(re.events_date);
		int g3=csales.getColumnIndex(re.events_clubname);
		int g4=csales.getColumnIndex(re.events_sender);
		int g5=csales.getColumnIndex(re.events_time);
		int g6=csales.getColumnIndex(re.events_venue);


		int j=0;
		for(csales.moveToFirst();!csales.isAfterLast();csales.moveToNext()){
			
			val[j]=csales.getString(title);
			vald[j]=csales.getString(g1);
			vald[j]=vald[j]+"\n"+"Date:"+csales.getString(g2);
			vald[j]=vald[j]+"\t"+"Time:"+csales.getString(g5);
			vald[j]=vald[j]+"\t"+"Venue:"+csales.getString(g6);
			vald[j]=vald[j]+"\n"+csales.getString(g4);
			vald[j]=vald[j]+"\n"+"Club:"+csales.getString(g3);
			
			j++;
		}

  
	}
	if(c==0)
	{
	       final CardView cv = new CardView(rvevents.getContext());
           cv.setRight(5);
           cv.setLeft(5);
           
           final int ch=height/6;
           cv.setMinimumHeight(ch);
           
           TextView tv1=new TextView(cv.getContext());
          tv1.setTag("please");
           tv1.setTextSize(22);
           if(aController.registeredOnServer(context))
           tv1.setText("No events to be displayed");
           else
        	   tv1.setText("You are not registered");
              
           
   		re.close();
           
   	 tv1.setTextColor(Color.RED);
    
                      tv1.setPadding(5, 15, 0,0);
                      
                      cv.addView(tv1);
           cv.setPadding(5, 5, 5,20);
        
           cv.setOnClickListener(new CardView.OnClickListener()
           {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
           	
           }
           		);
           // Create a generic swipe-to-dismiss touch listener.
           cv.setOnTouchListener(new SwipeDismissTouchListener(
                   cv,
                   null,
                   new SwipeDismissTouchListener.DismissCallbacks() {
                       @Override
                       public boolean canDismiss(Object token) {
                           return true;
                       }

                       @Override
                       public void onDismiss(View view, Object token) {
                           dismissableContainer.removeView(cv);
                       }
                   }));
           dismissableContainer.addView(cv);
		
	}
	
       for (int i = 0; i < c; i++) {
           final CardView cv = new CardView(rvevents.getContext());
           cv.setRight(5);
           cv.setLeft(5);
           
           final int ch=height/6;
           cv.setMinimumHeight(ch);
           
           TextView tv1=new TextView(cv.getContext());
          tv1.setTag("please");
           tv1.setTextSize(22);
           tv1.setText(val[i]);
           TextView tv2=new TextView(cv.getContext());
           tv2.setTag("mainmess");
            tv2.setTextSize(18);
            tv2.setText(vald[i]);
            tv1.setTextColor(Color.RED);
            tv2.setTextColor(Color.BLUE);         
   		re.close();
           
           
   	 tv1.setPadding(5, 15, 0,0);
     tv1.measure(0,0);
     if(tv1.getMeasuredWidth()/width<1)
     	tv2.setPadding(5, 50, 0, 0);
     else
     	{
     	int alp=50+(tv1.getMeasuredWidth()/width)*30;
     	//System.out.println("In this piece");
     	tv2.setPadding(5, alp, 0, 0);
     	
     	}
                  
                      cv.addView(tv1);
                      cv.addView(tv2);
           cv.setPadding(5, 5, 5,20);

           
           cv.setOnClickListener(new CardView.OnClickListener()
           {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
           	
           }
           		);
           
           // Create a generic swipe-to-dismiss touch listener.
           cv.setOnTouchListener(new SwipeDismissTouchListener(
                   cv,
                   null,
                   new SwipeDismissTouchListener.DismissCallbacks() {
                       @Override
                       public boolean canDismiss(Object token) {
                           return true;
                       }

                       @Override
                       public void onDismiss(View view, Object token) {
                           dismissableContainer.removeView(cv);
                       }
                   }));
           dismissableContainer.addView(cv);
       }
       

return rvevents;
		}

}
