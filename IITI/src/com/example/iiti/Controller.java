package com.example.iiti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gms.gcm.GoogleCloudMessaging;


//import com.google.android.gcm.GCMRegistrar; 
 
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.util.Log;


@SuppressLint("NewApi")
public class Controller extends Application{

	   private  final int MAX_ATTEMPTS = 5;
	    SharedPreferences prefs1;

	    private  final int BACKOFF_MILLI_SECONDS = 2000;
	    private  final Random random = new Random();
	     
	     
	    String regid=null;
	    
	     // Register this account with the server.
	    public void registerOngcmserver(final Context context) {
	          

	        
	       
	        final GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);

	    	new AsyncTask<Void, Void, String>() {
	            @Override
	            protected String doInBackground(Void... params) {
	                String msg = "";
	                try {
	                    
                String creg=gcm.register(Config.GOOGLE_SENDER_ID);
	            System.out.println("it is damn here to register and regid="+creg);    	
                regid = creg;
	                    msg = "Device registered, registration ID=" + regid;

	                    // You should send the registration ID to your server over HTTP, so it
	                    // can use GCM/HTTP or CCS to send messages to your app.
	                   // sendRegistrationIdToBackend();

	                    // For this demo: we don't need to send it because the device will send
	                    // upstream messages to a server that echo back the message using the
	                    // 'from' address in the message.

	                    // Persist the regID - no need to register again.
	                    
	                } catch (IOException ex) {
	                    msg = "Error :" + ex.getMessage();
	                    // If there is an error, don't just keep trying to register.
	                    // Require the user to click a button again, or perform
	                    // exponential back-off.
	                    return null;
	                }
	                return msg;
	            }

	            @Override
	            protected void onPostExecute(String msg) {
	            
	            }
	        }.execute(null, null, null);
        
        System.out.println("This is regid:"+regid+ "got it or not");
           
	    	//return regid;
}
	    
	    public void gettilldatemessagesfromurserver(final Context context)
	    {
	    	final AsyncTask<Void, Void, Void> getmessagesss;
	    	getmessagesss = new AsyncTask<Void, Void, Void>() {
       		 
	               @Override
	               protected Void doInBackground(Void... params1) {
	                    
	               	//System.out.println("Order 2  ");
	               	// Register on our server
	                   // On server creates a new user
	                  // aController.register(context, srollno, regid);
	    	    String regid2=getRegistrationId(context);
	            	   Log.i(Config.TAG, "Getting messages for this  (regId = " + regid2 + ")");
	   	         
	    	        String serverUrl1 = "http://anmol333.orgfree.com/messages1.php";
	    	         
	    	        Map<String, String> params = new HashMap<String, String>();
	    	        //System.out.println("RegId is:"+regid+"Did u get it or no");
	    	        //params.put("regid", regid);
	    	        //params.put("rollno", rollno);
	    	        //params.put("email", email);
	    	         //System.out.println(regid);
	    	        long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
	    	         
	    	        // Once GCM returns a registration id, we need to register onS our server
	    	        // As the server might be down, we will retry it a couple
	    	        // times.
	    	        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
	    	             
	    	            Log.d(Config.TAG, "Attempt #" + i + " to getmessages");
	    	             System.out.println("Attempt #" + i + " to getmessages");
	    	            try {
	    	                //Send Broadcast to Show message on screen
	    	                //displayMessageOnScreen(context, context.getString(
	    	                  //      R.string.server_registering, i, MAX_ATTEMPTS));
	    	                 
	    	                // Post registration values to web server
	    	                getmessages(serverUrl1, regid2,context);
	    	                 gotmessagessuccesfully(context,true);
	    	                //GCMRegistrar.setRegisteredOnServer(context, true);
	    	                 
	    	                //Send Broadcast to Show message on screen
	    	                String message = context.getString(R.string.server_registered);
	    	                //displayMessageOnScreen(context, message);
	    	                 
	    	                return null;
	    	            } catch (IOException e) {
	    	                 
	    	                // Here we are simplifying and retrying on any error; in a real
	    	                // application, it should retry only on unrecoverable errors
	    	                // (like HTTP error code 503).
	    	                 
	    	                Log.e(Config.TAG, "Failed to register on attempt " + i + ":" + e);
	    	                 
	    	                if (i == MAX_ATTEMPTS) {
	    	                    break;
	    	                }
	    	                try {
	    	                     
	    	                    Log.d(Config.TAG, "Sleeping for " + backoff + " ms before retry");
	    	                    Thread.sleep(backoff);
	    	                     
	    	                } catch (InterruptedException e1) {
	    	                    // Activity finished before we complete - exit.
	    	                    Log.d(Config.TAG, "Thread interrupted: abort remaining retries!");
	    	                    Thread.currentThread().interrupt();
	    	                    return null;
	    	                }
	    	                 
	    	                // increase backoff exponentially
	    	                backoff *= 2;
	    	            }
	    	        }
	    	         
	    	        String message = context.getString(R.string.server_register_error,
	    	                MAX_ATTEMPTS);
	    	         
	    	        //Send Broadcast to Show message on screen
	    	        //displayMessageOnScreen(context, message);
	    	    
	                   return null;
	               }

	               @Override
	               protected void onPostExecute(Void result) {
	              //     mRegisterTask = null;
	               }

	           };
	            
	           // execute AsyncTask
	           getmessagesss.execute(null, null, null);

	    }
	    
	    public void getmessages(String url, String regid5,Context context)
	    		throws IOException{
	    	String result="";
	    	InputStream isr=null;
			try
			{
				
				HttpClient httpclient=new DefaultHttpClient();
				//tv2.setText("Checkpoint 1");
				
				HttpPost httppost=new HttpPost(url);
				//tv2.setText("Checkpoint 2");
				HttpResponse response = null;
				if(httpclient==null||httppost==null)
				System.out.println("error is not till here");
				response=httpclient.execute(httppost);
				
				HttpEntity entity=response.getEntity();
				//tv2.setText("Checkpoint 4");
				isr=entity.getContent();
				//tv2.setText("Checkpoint 5");
				
			
				
				
			}
			catch(Exception e)
			{

				
				Log.e( "Try Try But Dont Cry", "Failed while opening the log file.", e );
				
			//	tv.setText("Try Try But Dont Cry");
			}
			try
			{
				
				BufferedReader reader=new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
				StringBuilder sb=new StringBuilder();
				String line=null;
				while((line=reader.readLine())!=null)
				{
					sb.append(line+"\n");
				}
				isr.close();
				//tv.setText("before");
				result=sb.toString();
				//tv.setText(result);
			}
			catch(Exception e)
			{
				//tv.setText("Try 2nd time");
			}
			try{
				
				System.out.println("Entered it 1");		
				String s="";

				//tv1.setText("lenght only");
						JSONArray jArray=new JSONArray(result);
					//	tv1.setText("getting jarray");
						//tv1.setText("lenght "+jArray.length());
						System.out.println("Entered it 2");		
						
						System.out.println("lenght "+jArray.length());
						for(int i=0;i<jArray.length();i++){
							JSONObject json =jArray.getJSONObject(i);
							System.out.println("Entered it 3");
							s=s+"MID : "+json.getString("id")+
								"Text "+json.getString("messagetext")+"\n\n";
						
							
						}
						System.out.println(s);
						//displayMessageOnScreen(context, s);
					}
					catch(Exception e)
					{
						//tv.setText("Try 3rd Time");
					}
			
	    }

	    
	    
	    

	    public void gettimetable(final Context context)
	    {
	    	
	               	//System.out.println("Order 2  ");
	               	// Register on our server
	                   // On server creates a new user
	                  // aController.register(context, srollno, regid);
	    	         
	    	        //Send Broadcast to Show message on screen
	    	        //displayMessageOnScreen(context, message);
	    	    

	    	        
	    	        
	    	        
	    	        
	    	        
	    	    	final AsyncTask<Void, Void, Void> getmessagesss;
	    	    	getmessagesss = new AsyncTask<Void, Void, Void>() {
	           		 
	    	               @Override
	    	               protected Void doInBackground(Void... params1) {
	    	                    
	    	               	//System.out.println("Order 2  ");
	    	               	// Register on our server
	    	                   // On server creates a new user
	    	                  // aController.register(context, srollno, regid);
	    	   	    	    String regid2=getRegistrationId(context);
	 	            	   Log.i(Config.TAG, "Getting timetable for this  (regId = " + regid2 + ")");
	 	   	         
	 	    	        String serverUrl1[] = {"http://anmol333.orgfree.com/2csemonday.php","http://anmol333.orgfree.com/2csetuesday.php","http://anmol333.orgfree.com/2csewednesday.php","http://anmol333.orgfree.com/2csethursday.php","http://anmol333.orgfree.com/2csefriday.php"};
	 	    	         
	 	    	        Map<String, String> params = new HashMap<String, String>();
	 	    	        //System.out.println("RegId is:"+regid+"Did u get it or no");
	 	    	        //params.put("regid", regid);
	 	    	        //params.put("rollno", rollno);
	 	    	        //params.put("email", email);
	 	    	         //System.out.println(regid);
	 	    	        long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
	 	    	         
	 	    	        // Once GCM returns a registration id, we need to register onS our server
	 	    	        // As the server might be down, we will retry it a couple
	 	    	        // times.
	 	    	        System.out.println("Counts for g"+serverUrl1.length);
	 	    	        int g=0; 
	 	    	        while( g < serverUrl1.length){
	 	    	        
	 	    	        	System.out.println("attempting for "+serverUrl1[g]);
	 	    	        	for (int i = 1; i <= MAX_ATTEMPTS; i++) {
	 	    	             
	 	    	            Log.d(Config.TAG, "Attempt #" + i + " to gettimetable");
	 	    	             //System.out.println("Attempt #" + i + " to gettimetable");
	 	    	            try {
	 	    	                //Send Broadcast to Show message on screen
	 	    	                //displayMessageOnScreen(context, context.getString(
	 	    	                  //      R.string.server_registering, i, MAX_ATTEMPTS));
	 	    	                 
	 	    	                // Post registration values to web server
	 	    	                getimetablenow(serverUrl1[g], regid2,context,g);
	 	    	                if(g==(serverUrl1.length)-1){ 
	 	    	                System.out.println("got time tabe succesfully");
	 	    	                	gottimetablesuccesfully(context,true);
	 	    	                //GCMRegistrar.setRegisteredOnServer(context, true);
	 	    	                	displayMessageOnScreen(context);
	 	    	                
	 	    	                }
	 	    	                //Send Broadcast to Show message on screen
	 	    	                String message = context.getString(R.string.server_registered);
	 	    	                //displayMessageOnScreen(context, message);
	 	    	                 
	 	    	                break;
	 	    	                } catch (IOException e) {
	 	    	                 
	 	    	                // Here we are simplifying and retrying on any error; in a real
	 	    	                // application, it should retry only on unrecoverable errors
	 	    	                // (like HTTP error code 503).
	 	    	                 
	 	    	                Log.e(Config.TAG, "Failed to gettimetable on attempt " + i + ":" + e);
	 	    	                 
	 	    	                if (i == MAX_ATTEMPTS) {
	 	    	                    break;
	 	    	                }
	 	    	                try {
	 	    	                     
	 	    	                    Log.d(Config.TAG, "Sleeping for " + backoff + " ms before retry");
	 	    	                    Thread.sleep(backoff);
	 	    	                     
	 	    	                } catch (InterruptedException e1) {
	 	    	                    // Activity finished before we complete - exit.
	 	    	                    Log.d(Config.TAG, "Thread interrupted: abort remaining retries!");
	 	    	                    Thread.currentThread().interrupt();
	 	    	               
	 	    	                }
	 	    	                 
	 	    	                // increase backoff exponentially
	 	    	                backoff *= 2;
	 	    	            }
	 	    	        }
	 	    	        	g++;
	 	    	        }
	 	    	         
	 	    	        String message = context.getString(R.string.server_register_error,
	 	    	                MAX_ATTEMPTS);
						return null;

	    	               }

	    	               @Override
	    	               protected void onPostExecute(Void result) {
	    	              //     mRegisterTask = null;
	    	               }

	    	           };
	    	            
	    	           // execute AsyncTask
	    	           getmessagesss.execute(null, null, null);

	    	        
	    	        

	    }
	    
	    public void getimetablenow(String url, String regid5,Context context,int k)
	    		throws IOException{
	    	String result="";
	    	InputStream isr=null;
			try
			{
				
				HttpClient httpclient=new DefaultHttpClient();
				//tv2.setText("Checkpoint 1");
				
				HttpPost httppost=new HttpPost(url);
				//tv2.setText("Checkpoint 2");
				HttpResponse response = null;
				if(httpclient==null||httppost==null)
				System.out.println("error is not till here");
				response=httpclient.execute(httppost);
				
				HttpEntity entity=response.getEntity();
				//tv2.setText("Checkpoint 4");
				isr=entity.getContent();
				//tv2.setText("Checkpoint 5");
				
			
				
				
			}
			catch(Exception e)
			{

				
				Log.e( "Try Try But Dont Cry", "Failed while opening the log file.", e );
				
			//	tv.setText("Try Try But Dont Cry");
			}
			try
			{
				
				BufferedReader reader=new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
				StringBuilder sb=new StringBuilder();
				String line=null;
				while((line=reader.readLine())!=null)
				{
					sb.append(line+"\n");
				}
				isr.close();
				//tv.setText("before");
				result=sb.toString();
				//tv.setText(result);
			}
			catch(Exception e)
			{
				//tv.setText("Try 2nd time");
			}
			try{
				
				System.out.println("Entered it 1");		
				String s="";

				//tv1.setText("lenght only");
						JSONArray jArray=new JSONArray(result);
					//	tv1.setText("getting jarray");
						//tv1.setText("lenght "+jArray.length());
						System.out.println("Entered it 2");		
						String time1;
						String details;
						System.out.println("lenght "+jArray.length());
						for(int i=0;i<jArray.length();i++){
							JSONObject json =jArray.getJSONObject(i);
							//System.out.println("Entered it 3");
							s=s+"MID : "+json.getString("time1")+
								"Text "+json.getString("details")+"\n\n";
						time1=json.getString("time1");
						int x=time1.lastIndexOf('-');
						String be=time1.substring(0,x);
						String end=time1.substring(x+1);
						details=json.getString("details");
							HotOrNot entry=new HotOrNot(Controller.this);
		        			entry.open();
		        			entry.creattimetableentry(be,end,details,k);
		        			
						
						
	            			
	        			entry.close();
	        			
						
						}
						System.out.println(s);
						//displayMessageOnScreen(context, s);
					}
					catch(Exception e)
					{
						//tv.setText("Try 3rd Time");
					}
			
	    }

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /*static void displayMessageOnScreen(Context context, String message) {
	          
	        Intent intent = new Intent(Config.DISPLAY_MESSAGE_ACTION);
	        intent.putExtra(Config.EXTRA_MESSAGE, message);
	         
	 
	 
	        // Send Broadcast to Broadcast receiver with message
	        context.sendBroadcast(intent);
	         
	    }*/
	    public void registerOnurserver(final Context context,final String rollno)
	    {
	    	
	    	final AsyncTask<Void, Void, Void> mRegisterTask;
	    	mRegisterTask = new AsyncTask<Void, Void, Void>() {
       		 
	               @Override
	               protected Void doInBackground(Void... params1) {
	            	   String regid1=getRegistrationId(context);            
	               	System.out.println("Order 2  ");
	               	// Register on our server
	                   // On server creates a new user
	                  // aController.register(context, srollno, regid);
	    	        Log.i(Config.TAG, "registering device (regId = " + regid1 + ")");
	   	         
	    	        String serverUrl = "http://anmol333.orgfree.com/register1.php";
	    	         
	    	        Map<String, String> params = new HashMap<String, String>();
	    	        System.out.println("RegId is:"+regid1+"Did u get it or no");
	    	        params.put("regid", regid1);
	    	        params.put("rollno", rollno);
	    	        //params.put("email", email);
	    	         //System.out.println(regid);
	    	        long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
	    	         
	    	        // Once GCM returns a registration id, we need to register onS our server
	    	        // As the server might be down, we will retry it a couple
	    	        // times.
	    	        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
	    	             
	    	            Log.d(Config.TAG, "Attempt #" + i + " to register");
	    	             System.out.println("Attempt #" + i + " to register");
	    	            try {
	    	                //Send Broadcast to Show message on screen
	    	                //displayMessageOnScreen(context, context.getString(
	    	                  //      R.string.server_registering, i, MAX_ATTEMPTS));
	    	                 
	    	                // Post registration values to web server
	    	                post(serverUrl, params,context);
	    	                 registeredsuccesfully(context,true);
	    	                //GCMRegistrar.setRegisteredOnServer(context, true);
	    	                 
	    	                //Send Broadcast to Show message on screen
	    	                String message = context.getString(R.string.server_registered);
	    	                //displayMessageOnScreen(context, message);
	    	                 
	    	                return null;
	    	            } catch (IOException e) {
	    	                 
	    	                // Here we are simplifying and retrying on any error; in a real
	    	                // application, it should retry only on unrecoverable errors
	    	                // (like HTTP error code 503).
	    	                 
	    	                Log.e(Config.TAG, "Failed to register on attempt " + i + ":" + e);
	    	                 
	    	                if (i == MAX_ATTEMPTS) {
	    	                    break;
	    	                }
	    	                try {
	    	                     
	    	                    Log.d(Config.TAG, "Sleeping for " + backoff + " ms before retry");
	    	                    Thread.sleep(backoff);
	    	                     
	    	                } catch (InterruptedException e1) {
	    	                    // Activity finished before we complete - exit.
	    	                    Log.d(Config.TAG, "Thread interrupted: abort remaining retries!");
	    	                    Thread.currentThread().interrupt();
	    	                    return null;
	    	                }
	    	                 
	    	                // increase backoff exponentially
	    	                backoff *= 2;
	    	            }
	    	        }
	    	         
	    	        String message = context.getString(R.string.server_register_error,
	    	                MAX_ATTEMPTS);
	    	         
	    	        //Send Broadcast to Show message on screen
	    	        //displayMessageOnScreen(context, message);
	    	    
	                   return null;
	               }

	               @Override
	               protected void onPostExecute(Void result) {
	              //     mRegisterTask = null;
	               }

	           };
	            
	           // execute AsyncTask
	           mRegisterTask.execute(null, null, null);

	    }
	    
	    private SharedPreferences getGCMPreferences(Context context) {
		    // This sample app persists the registration ID in shared preferences, but
		    // how you store the regID in your app is up to you.
		    return getSharedPreferences(Controller.class.getSimpleName(),
		            Context.MODE_PRIVATE);
		}
	     protected void registeredsuccesfully(Context context, boolean b) {
			// TODO Auto-generated method stub
	    	 final SharedPreferences prefs = getGCMPreferences(context);
	 	    //int appVersion = getAppVersion(context);
	 	    //Log.i(Config.TAG, "Saving regId on app version " + appVersion);
	 	    SharedPreferences.Editor editor = prefs.edit();
	 	    editor.putBoolean("Stat", b);
	 	    
	 	    //editor.putInt(PROPERTY_APP_VERSION, appVersion);
	 	    editor.commit();
		}

	     protected void gotmessagessuccesfully(Context context, boolean b) {
				// TODO Auto-generated method stub
		    	 final SharedPreferences prefs = getGCMPreferences(context);
		 	    //int appVersion = getAppVersion(context);
		 	    //Log.i(Config.TAG, "Saving regId on app version " + appVersion);
		 	    SharedPreferences.Editor editor = prefs.edit();
		 	    editor.putBoolean("gotall", b);
		 	    
		 	    //editor.putInt(PROPERTY_APP_VERSION, appVersion);
		 	    editor.commit();
			}
	     
	     protected void gottimetablesuccesfully(Context context, boolean b) {
				// TODO Auto-generated method stub
		    	 final SharedPreferences prefs = getGCMPreferences(context);
		 	    //int appVersion = getAppVersion(context);
		 	    //Log.i(Config.TAG, "Saving regId on app version " + appVersion);
		 	    SharedPreferences.Editor editor = prefs.edit();
		 	    editor.putBoolean("timetable", b);
		 	    
		 	    //editor.putInt(PROPERTY_APP_VERSION, appVersion);
		 	    editor.commit();
			}
	     
	   
	     public boolean registeredOnServer(Context context) {
				// TODO Auto-generated method stub
			
				  final SharedPreferences prefs = getGCMPreferences(context);
				  
				  Boolean registrationId = prefs.getBoolean("Stat", false );
				    if (registrationId.equals(true)) {
				        return true;
				    }
				    else
				    	return false;
			
			}
	     public boolean gettilldatemessagesfromurServer(Context context)
	     {
	   	  final SharedPreferences prefs = getGCMPreferences(context);
		  
		  Boolean gotall = prefs.getBoolean("gotall", false );
		    if (gotall.equals(true)) {
		        return true;
		    }
		    else
		    	return false;
	 
	     }
	     public boolean gottimetableornot(Context context)
	     {
	   	  final SharedPreferences prefs = getGCMPreferences(context);
		  
		  Boolean gotall = prefs.getBoolean("timetable", false );
		    if (gotall.equals(true)) {
		        return true;
		    }
		    else
		    	return false;
	 
	     }     
	     
		// Unregister this account/device pair within the server.
	     void unregister(final Context context, final String regId) {
	          
	        Log.i(Config.TAG, "unregistering device (regId = " + regId + ")");
	         
	        String serverUrl = Config.YOUR_SERVER_URL + "/unregister";
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("regid", regId);
	         
	        try {
	            post(serverUrl, params,context);
	            //GCMRegistrar.setRegisteredOnServer(context, false);
	            String message = context.getString(R.string.server_unregistered);
	            //displayMessageOnScreen(context, message);
	        } catch (IOException e) {
	             
	            // At this point the device is unregistered from GCM, but still
	            // registered in the our server.
	            // We could try to unregister again, but it is not necessary:
	            // if the server tries to send a message to the device, it will get
	            // a "NotRegistered" error message and should unregister the device.
	             
	            String message = context.getString(R.string.server_unregister_error,
	                    e.getMessage());
	            //displayMessageOnScreen(context, message);
	        }
	    }
	 
	    // Issue a POST request to the server.
	    private static void post(String endpoint, Map<String, String> params,Context context)
	            throws IOException {    
	         
	        URL url;
	        try {
	             
	            url = new URL(endpoint);
	             
	        } catch (MalformedURLException e) {
	            throw new IllegalArgumentException("invalid url: " + endpoint);
	        }
	         
	        StringBuilder bodyBuilder = new StringBuilder();
	        Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
	         
	        // constructs the POST body using the parameters
	        while (iterator.hasNext()) {
	            Entry<String, String> param = iterator.next();
	            bodyBuilder.append(param.getKey()).append('=')
	                    .append(param.getValue());
	            if (iterator.hasNext()) {
	                bodyBuilder.append('&');
	            }
	        }
	         
	        String body = bodyBuilder.toString();
	         
	        Log.v(Config.TAG, "Posting '" + body + "' to " + url);
	         
	        byte[] bytes = body.getBytes();
	         
	        HttpURLConnection conn = null;
	        try {
	             
	            Log.e("URL", "> " + url);
	             
	            conn = (HttpURLConnection) url.openConnection();
	            conn.setDoOutput(true);
	            conn.setUseCaches(false);
	            conn.setFixedLengthStreamingMode(bytes.length);
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type",
	                    "application/x-www-form-urlencoded;charset=UTF-8");
	            // post the request
	            OutputStream out = conn.getOutputStream();
	            out.write(bytes);
	            out.close();
	            BufferedReader reader=null;
	            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            
	            // Read Server Response
	            while((line = reader.readLine()) != null)
	                {
	                       // Append server response in string
	                       sb.append(line + "\n");
	                }
	            String faltu=sb.toString();
	            //displayMessageOnScreen(context,faltu );
	            // handle the response
	            int status = conn.getResponseCode();
	             
	            // If response is not success
	            if (status != 200) {
	                 
	              throw new IOException("Post failed with error code " + status);
	            }
	        } finally {
	            if (conn != null) {
	                conn.disconnect();
	            }
	        }
	      }
	     
	     
	     
	    // Checking for all possible internet providers
	    public boolean isConnectingToInternet(){
	         
	        ConnectivityManager connectivity = 
	                             (ConnectivityManager) getSystemService(
	                              Context.CONNECTIVITY_SERVICE);
	          if (connectivity != null)
	          {
	              NetworkInfo[] info = connectivity.getAllNetworkInfo();
	              if (info != null)
	                  for (int i = 0; i < info.length; i++)
	                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
	                      {
	                          return true;
	                      }
	  
	          }
	          return false;
	    }
	     
	   // Notifies UI to display a message.
	     
	     
	   //Function to display simple Alert Dialog
	   public void showAlertDialog(Context context, String title, String message,
	            Boolean status) {
	        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
	 
	        // Set Dialog Title
	        alertDialog.setTitle(title);
	 
	        // Set Dialog Message
	        alertDialog.setMessage(message);
	 
	        if(status != null)
	            // Set alert dialog icon
	            //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
	 
	        // Set OK Button
	        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                 
	            }
	        });
	 
	        // Show Alert Message
	        alertDialog.show();
	    }
	     
	    private PowerManager.WakeLock wakeLock;
	     
	    public  void acquireWakeLock(Context context) {
	        if (wakeLock != null) wakeLock.release();
	 
	        PowerManager pm = (PowerManager) 
	                          context.getSystemService(Context.POWER_SERVICE);
	         
	        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
	                PowerManager.ACQUIRE_CAUSES_WAKEUP |
	                PowerManager.ON_AFTER_RELEASE, "WakeLock");
	         
	        wakeLock.acquire();
	    }
	 
	    public  void releaseWakeLock() {
	        if (wakeLock != null) wakeLock.release(); wakeLock = null;
	    }

		public void freshsetup(Context context, String srollno) {
			// TODO Auto-generated method stub
			
registerOngcmserver(context);	
storeRegistrationId(context,srollno);
registerOnurserver(context,srollno);
gettimetable(context);
//gettilldatemessagesfromurserver(context);


		}
		public void storeRegistrationId(final Context context,final String rollno)
		{
			new AsyncTask<Void, Void, String>() {
	            @Override
	            protected String doInBackground(Void... params) {
	                String msg = "";
	                final SharedPreferences prefs = getGCMPreferences(context);
	        	    int appVersion = getAppVersion(context);
	        	    Log.i(Config.TAG, "Saving regId= "+ regid + "on app version " + appVersion);
	        	    SharedPreferences.Editor editor = prefs.edit();
	        	    editor.putString("regId", regid);
	        	    editor.putString("rollno", rollno);
	        	    editor.putInt("appver", appVersion);
	        	    editor.commit();
	                return msg;
	            }

	            @Override
	            protected void onPostExecute(String msg) {
	            
	            }
	        }.execute(null, null, null);

		}
		private static int getAppVersion(Context context) {
		    try {
		        PackageInfo packageInfo = context.getPackageManager()
		                .getPackageInfo(context.getPackageName(), 0);
		        return packageInfo.versionCode;
		    } catch (NameNotFoundException e) {
		        // should never happen
		        throw new RuntimeException("Could not get package name: " + e);
		    }
		}

		public String getRegistrationId(Context context) {
			// TODO Auto-generated method stub
			final SharedPreferences prefs = getGCMPreferences(context);
		    String registrationId = prefs.getString("regId", "");
		    if (registrationId.isEmpty()) {
		        Log.i(Config.TAG, "Registration not found.");
		        return "";
		    }
		    // Check if app was updated; if so, it must clear the registration ID
		    // since the existing regID is not guaranteed to work with the new
		    // app version.
		    int registeredVersion = prefs.getInt("appver", Integer.MIN_VALUE);
		    int currentVersion = getAppVersion(context);
		    if (registeredVersion != currentVersion) {
		        Log.i(Config.TAG, "App version changed.");
		        return "";
		    }
		    return registrationId;
		}

		public String getRollno(Context context) {
			// TODO Auto-generated method stub
			final SharedPreferences prefs = getGCMPreferences(context);
		    String registrationId = prefs.getString("rollno", "");
		    if (registrationId.isEmpty()) {
		        Log.i(Config.TAG, "Registration not found.");
		        return "";
		    }
		    // Check if app was updated; if so, it must clear the registration ID
		    // since the existing regID is not guaranteed to work with the new
		    // app version.
		    int registeredVersion = prefs.getInt("appver", Integer.MIN_VALUE);
		    int currentVersion = getAppVersion(context);
		    if (registeredVersion != currentVersion) {
		        Log.i(Config.TAG, "App version changed.");
		        return "";
		    }
		    return registrationId;
		}

		public void displayMessageOnScreen(Context context, String type,
				String id, String title, String content) {
			// TODO Auto-generated method stub
		       Intent intent = new Intent(Config.DISPLAY_MESSAGE_ACTION);
		        intent.putExtra("type", type);
		        intent.putExtra("id", id);
		        intent.putExtra("title", title);
		        intent.putExtra("content", content);
		 
		 
		        // Send Broadcast to Broadcast receiver with message
		        context.sendBroadcast(intent);
		         
		}
		public void displayMessageOnScreen(Context context) {
			// TODO Auto-generated method stub
		       Intent intent = new Intent(Config.DISPLAY_MESSAGE_ACTION);
		        intent.putExtra("type", "update fragment");
		 
		 
		        // Send Broadcast to Broadcast receiver with message
		        context.sendBroadcast(intent);
		         
		}
		public void displayMessageOnScreen(Context context, String type,
				String id, String cname, String ename, String date1,
				String time1, String venue, String sender, String content) {
			// TODO Auto-generated method stub
		      Intent intent = new Intent(Config.DISPLAY_MESSAGE_ACTION);
		        intent.putExtra("type", type);
		        intent.putExtra("id", id);
		        intent.putExtra("cname", cname);
		        intent.putExtra("ename", ename);
		        intent.putExtra("date", date1);
		        intent.putExtra("time", time1);
		        intent.putExtra("venue", venue);
		        intent.putExtra("sender", sender);
		        intent.putExtra("content", content);
		 
		 
		        // Send Broadcast to Broadcast receiver with message
		        context.sendBroadcast(intent);
		         	
		}
		
	
}
