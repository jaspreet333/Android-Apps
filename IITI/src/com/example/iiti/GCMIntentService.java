package com.example.iiti;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMIntentService extends IntentService {
 
    private static final String TAG = "GCMIntentService";
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    private Controller aController = null;
        
 
    public GCMIntentService() {
        // Call extended class Constructor GCMBaseIntentService
        super(Config.GOOGLE_SENDER_ID);
    }
 
    /**
     * Method called on device registered
     **/
 
    /**
     * Method called on device unregistred
     * */
    /**
     * Method called on Receiving a new message from GCM server
     * */
    
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		aController = (Controller) getApplicationContext();
    	Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);
        Context context=getApplicationContext();
        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM
             * will be extended in the future with new message types, just ignore
             * any message types you're not interested in, or that you don't
             * recognize.
             */
            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " +
                        extras.toString());
            // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
                for (int i=0; i<5; i++) {
                    Log.i("TAG", "Working... " + (i+1)
                            + "/5 @ " + SystemClock.elapsedRealtime());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                }
                Log.i("TAG", "Completed work @ " + SystemClock.elapsedRealtime());
                // Post notification of received message.
                
                Log.i("TAG", "Received: " + extras.toString());
                String type = intent.getExtras().getString("Type");
                
                if(type.equals("News")){
                System.out.println("This is the messsage type "+type+" got or not");
                String id=intent.getExtras().getString("Mid");
                String title=intent.getExtras().getString("Title");
                String content=intent.getExtras().getString("Content");
            	HotOrNot entry=new HotOrNot(GCMIntentService.this);
    			entry.open();
    			entry.createnews(id,title,content);
    			
    			//sendNotification(type+":"+ title);	
    			generateNotification(GCMIntentService.this,type+":"+ title);
    			entry.close();
    		
                aController.displayMessageOnScreen(context, type,id,title,content);
                }
                if(type.equals("Events")){
                    System.out.println("This is the messsage "+type+" got or not");
                    String id=intent.getExtras().getString("Mid");
                    String cname=intent.getExtras().getString("Clubname");
                    String ename=intent.getExtras().getString("eventname");
                    String date1=intent.getExtras().getString("date");
                    String time1=intent.getExtras().getString("time");
                    String venue=intent.getExtras().getString("venue");
                    String sender=intent.getExtras().getString("sender");
                    String content=intent.getExtras().getString("Content");
                	HotOrNot entry=new HotOrNot(GCMIntentService.this);
        			entry.open();
        			entry.createvent(id,cname,ename,date1,time1,venue,sender,content);
        			
            			
        			entry.close();
        			//sendNotification(type+":"+ ename);
        			generateNotification(GCMIntentService.this,type+":"+ ename);
        			
        			aController.displayMessageOnScreen(context, type,id,cname,ename,date1,time1,venue,sender,content);
                    }
                if(type.equals("updatetimetable"))
                {
                	
                }
                System.out.println("After aController");
            }
        }
            		
	}

 
    /**
     * Method called on receiving a deleted message
     * */
 
    /**
     * Method called on Error
     * */
 
    /**
     * Create a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message) {
     
        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();
         System.out.println("This is 1");
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
         
        String title = context.getString(R.string.app_name);
        System.out.println("This is 2");
        Intent notificationIntent = new Intent(context, MainActivity.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
       // notification.flags |= Notification.FLAG_AUTO_CANCEL;
        System.out.println("This is 3");         
        
        notification.flags |=  Notification.FLAG_AUTO_CANCEL ;         
        notification.flags |=Notification.FLAG_ONLY_ALERT_ONCE;
        // Play default notification sound
        System.out.println("This is 4");
        notification.defaults = Notification.DEFAULT_SOUND;
        
        //notification.sound = Uri.parse(                       "android.resource://"  + context.getPackageName() + "your_sound_file_name.mp3");
         
        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);      
 
    }

    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentTitle("IITI")
        .setStyle(new NotificationCompat.BigTextStyle()
        .bigText(msg))
        .setContentText(msg);
      
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}