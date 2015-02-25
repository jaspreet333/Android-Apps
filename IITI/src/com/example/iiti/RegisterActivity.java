package com.example.iiti;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends ActionBarActivity {

	private EditText rollno;
	private Button b1;
	private Button b2;
	public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
     
    // Asyntask
    AsyncTask<Void, Void, Void> mRegisterTask;
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    SharedPreferences prefs;

    public static String srollno;
    String regid;
 Context context;

 Controller aController;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		rollno=(EditText)findViewById(R.id.et1);
		b1=(Button)findViewById(R.id.b1);
		b2=(Button)findViewById(R.id.b2);
		aController = (Controller) getApplicationContext();
		Context f=getApplicationContext();
    // Check if Internet Connection present
		context = getApplicationContext();
		regid = aController.getRegistrationId(context);
		if (!aController.isConnectingToInternet()&&regid.isEmpty()) {
             
            // Internet Connection is not present
            aController.showAlertDialog(RegisterActivity.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
             
            // stop executing code by return
           
        }
	    

         
			if (android.os.Build.VERSION.SDK_INT > 9) {
			      
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			      StrictMode.setThreadPolicy(policy);
			      
			    }
			gcm = GoogleCloudMessaging.getInstance(this);
            
            
			
		    srollno=aController.getRollno(context);
	        
		        
		        // Check if regid already presents
		        if (!regid.isEmpty()&&aController.registeredOnServer(context)/*&&aController.gettilldatemessagesfromurServer(context)*/&&aController.gottimetableornot(context)) {
		             
		            // Register with GCM            
		            //GCMRegistrar.register(this, Config.GOOGLE_SENDER_ID);
		        	System.out.println("Directly in");
		        	Intent i = new Intent(getApplicationContext(), MainActivity.class);
		        	startActivity(i);
		        	finish();
            
		            
		        }
		        else if(!regid.isEmpty()&&!aController.registeredOnServer(context))
		        {
		        	System.out.println("Not even registered on server");
		        	aController.registerOnurserver(context,srollno);
		            aController.gettimetable(context);
			           
		            //aController.gettilldatemessagesfromurserver(context);
		            Intent i = new Intent(getApplicationContext(), MainActivity.class);
		        	startActivity(i);
		        	finish();

		            
		        }
		        //else if(!regid.isEmpty()&&aController.registeredOnServer(context)/*&&!aController.gettilldatemessagesfromurServer(context)*/)
		        /*{
		            aController.gettimetable(context);
				     
		        	//aController.gettilldatemessagesfromurserver(context);
		            Intent i = new Intent(getApplicationContext(), MainActivity.class);
		        	startActivity(i);
		        	finish();

		        }*/
		        else if(!regid.isEmpty()&&aController.registeredOnServer(context)/*&&aController.gettilldatemessagesfromurServer(context)*/&&!aController.gottimetableornot(context))
		        {
		        	System.out.println("Just fetching time table");
		        	aController.gettimetable(context);
		            Intent i = new Intent(getApplicationContext(), MainActivity.class);
		        	startActivity(i);
		        	finish();

		        }	
		        b1.setOnClickListener(new View.OnClickListener()
		        {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
		            	System.out.println("In Click");
		            	
						 srollno = rollno.getText().toString(); 
		                
		                // Check if user filled the form
		                if(srollno.trim().length()  > 0){
		                     
		                	System.out.println("In trim");
		                    // Launch Main Activity
		                	aController.freshsetup(context,srollno);
		    	           
		    	        
		    	       	    Intent i = new Intent(getApplicationContext(), MainActivity.class);
				        	startActivity(i);
				        	finish();
		                    
			                 
		                }
		                else{
		                     
		                    // user doen't filled that data
		                    aController.showAlertDialog(RegisterActivity.this, 
		                                                "Registration Error!", 
		                                                "Please enter your details", 
		                                                false);
		                }
					}
		        	
		        });
		        b2.setOnClickListener(new View.OnClickListener()
		        {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						System.out.println("In Skip this shit");
					    Intent i = new Intent(getApplicationContext(), MainActivity.class);
			        	startActivity(i);
			        	finish();
		            
					}
				});
		    
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

    @SuppressLint("NewApi")
	private String getRegistrationId(Context context) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    if (registrationId.isEmpty()) {
	        Log.i(Config.TAG, "Registration not found.");
	        return "";
	    }
	    // Check if app was updated; if so, it must clear the registration ID
	    // since the existing regID is not guaranteed to work with the new
	    // app version.
	    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        Log.i(Config.TAG, "App version changed.");
	        return "";
	    }
	    return registrationId;
	}
/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private SharedPreferences getGCMPreferences(Context context) {
	    // This sample app persists the registration ID in shared preferences, but
	    // how you store the regID in your app is up to you.
	    return getSharedPreferences(MainActivity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}
	private void storeRegistrationId(Context context, String regId) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    int appVersion = getAppVersion(context);
	    Log.i(Config.TAG, "Saving regId on app version " + appVersion);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(PROPERTY_REG_ID, regId);
	    editor.putInt(PROPERTY_APP_VERSION, appVersion);
	    editor.commit();
	}

	private void registerInBackground() {
        
		gcm = GoogleCloudMessaging.getInstance(context);
        try {
			regid = gcm.register(Config.GOOGLE_SENDER_ID);
			System.out.println("Device registered, registration ID=" + regid);
			System.out.println("in registerinBackground:"+regid+"gotornit"); 	
        } catch (IOException e) {
			// TODO Auto-generated catch block
		System.out.println("In the error");
        	e.printStackTrace();
		}
        String msg = "Device registered, registration ID=" + regid;

        // You should send the registration ID to your server over HTTP, so it
        // can use GCM/HTTP or CCS to send messages to your app.
       // sendRegistrationIdToBackend();

        // For this demo: we don't need to send it because the device will send
        // upstream messages to a server that echo back the message using the
        // 'from' address in the message.

        // Persist the regID - no need to register again.
        storeRegistrationId(context, regid);
		new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                if (gcm == null) {
   //                    gcm = GoogleCloudMessaging.getInstance(context);
				}
     //              regid = gcm.register(Config.GOOGLE_SENDER_ID);
      //            msg = "Device registered, registration ID=" + regid;
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
            	//aController.showAlertDialog(RegisterActivity.this,
                  //      "Error",        msg, false);
                 System.out.println(msg);
            	
            }
        }.execute(null, null, null);
    }


    @Override
    protected void onDestroy() {
        // Cancel AsyncTask
        if (mRegisterTask != null) {
            mRegisterTask.cancel(true);
        }
        try {
            // Unregister Broadcast Receiver
 //           unregisterReceiver(mHandleMessageReceiver);
             
            //Clear internal resources.
            //GCMRegistrar.onDestroy(this);
             
        } catch (Exception e) {
            Log.e("UnRegister Receiver Error", 
                      "> " + e.getMessage());
        }
        super.onDestroy();
    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
