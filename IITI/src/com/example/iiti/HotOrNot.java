package com.example.iiti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {
	
	public static final String Drum_RowID="_id";
	public static final String _RowID="_id";
	public static final String Drum_Capacity="Capacity";
	public static final String Drum_Quantity="Quantity1";
	public static final String Drum_date="date1";
	public static final String Drum_inout="inout";
	public static final String news_title="title";
	public static final String news_content="content";
	public static final String Drum_desci="desci";
	public static final String events_content="content";
	public static final String events_name="name";
	public static final String events_clubname="clubname";
	public static final String events_sender="sender";
	public static final String events_date="date1";
	public static final String events_time="time1";
	public static final String events_venue="venue";
	public static final String timein="timein";
	public static final String timeout="timeout";
	public static final String details="details";
	private static final String DATABASE_NAME="HotOrNotdb";
	private static final String DATABASE_TABLE10="newscontent";
	private static final String DATABASE_TABLE11="eventscontent";
	private static final String DATABASE_TABLE12="smonday";
	private static final String DATABASE_TABLE13="stuesday";
	private static final String DATABASE_TABLE14="swednesday";
	private static final String DATABASE_TABLE15="sthursday";
	private static final String DATABASE_TABLE16="sfriday";
	private static final String DATABASE_TABLE="drumentry";
	private static final int DATABASE_VERSION=1;
	private static final String DATABASE_TABLE1="companylist";
	public static final String Com_RowID="_id";
	public static final String Com_Name="Name";
	public static final String Com_Balance="Balance";
	private static final String DATABASE_TABLE2="Salestran";
	public static final String Sales_Id="_id";
	private static final String DATABASE_TABLE3="Paytran";
	private static final String DATABASE_TABLE4="Drumstock";
	private static final String DATABASE_TABLE5="forwadedupdatedbal";
	
	public static final String Sales_Date="date1";
	public static final String Sales_Cname="cname";
	public static final String Sales_Quan="quan";
	public static final String Sales_Rate="rate";
	public static final String Sales_Amt="amt";
	public static final String Sales_Des="des";
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper
	{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+DATABASE_TABLE+" ( "+
					Drum_RowID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Drum_date+" TEXT NOT NULL,"+Drum_Capacity+" TEXT NOT NULL,"+Drum_Quantity+" INTEGER,"+Drum_inout+" TEXT NOT NULL,"+Drum_desci+" TEXT NOT NULL);"
					);
			System.out.println("created 1");
			db.execSQL("CREATE TABLE "+DATABASE_TABLE1+" ( "+
					Com_RowID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Com_Name+" TEXT NOT NULL,"+Com_Balance+" INTEGER);"
					);

			System.out.println("created 2");
			db.execSQL("CREATE TABLE "+DATABASE_TABLE2+" ( "+
					Sales_Id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Sales_Des+" TEXT NOT NULL,"+Sales_Date +" TEXT NOT NULL,"+Sales_Cname+" TEXT NOT NULL,"+Sales_Quan+" INTEGER,"+Sales_Rate+" INTEGER,"+Sales_Amt+" INTEGER);"
					);

			System.out.println("created 3");
			db.execSQL("CREATE TABLE "+DATABASE_TABLE3+" ( "+
					Sales_Id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Sales_Des+" TEXT NOT NULL,"+Sales_Date +" TEXT NOT NULL,"+Sales_Cname+" TEXT NOT NULL,"+Sales_Amt+" INTEGER);"
					);

			System.out.println("created 4");
			db.execSQL("CREATE TABLE "+DATABASE_TABLE4+" ( "+
					Sales_Id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Drum_Capacity+" TEXT NOT NULL,"+Drum_Quantity+" INTEGER);"
					);
			db.execSQL("CREATE TABLE "+DATABASE_TABLE5+" ( "+
					Sales_Id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Sales_Des+" TEXT NOT NULL,"+Sales_Cname+" TEXT NOT NULL,"+Sales_Date+" TEXT NOT NULL,"+Sales_Amt+" INTEGER);"
					);
			db.execSQL("CREATE TABLE "+DATABASE_TABLE10+" ( "+
					_RowID+" INTEGER PRIMARY KEY ,"+news_title+" TEXT NOT NULL,"+news_content+" TEXT NOT NULL);"
					);
			db.execSQL("CREATE TABLE "+DATABASE_TABLE11+" ( "+
					_RowID+" INTEGER PRIMARY KEY ,"+events_name+" TEXT NOT NULL,"+events_clubname+" TEXT NOT NULL,"+events_date+" TEXT NOT NULL,"+events_time+" TEXT NOT NULL,"+events_venue+" TEXT NOT NULL,"+events_sender+" TEXT NOT NULL,"+events_content+" TEXT NOT NULL);"
					);
			db.execSQL("CREATE TABLE "+DATABASE_TABLE12+" ( "+
					timein+" DATETIME,"+timeout+" DATETIME,"+details+" TEXT NOT NULL);");		
			db.execSQL("CREATE TABLE "+DATABASE_TABLE13+" ( "+
					timein+" DATETIME,"+timeout+" DATETIME,"+details+" TEXT NOT NULL);");
			db.execSQL("CREATE TABLE "+DATABASE_TABLE14+" ( "+
					timein+" DATETIME,"+timeout+" DATETIME,"+details+" TEXT NOT NULL);");
			db.execSQL("CREATE TABLE "+DATABASE_TABLE15+" ( "+
					timein+" DATETIME,"+timeout+" DATETIME,"+details+" TEXT NOT NULL);");
			db.execSQL("CREATE TABLE "+DATABASE_TABLE16+" ( "+
					timein+" DATETIME,"+timeout+" DATETIME,"+details+" TEXT NOT NULL);");
			
			ContentValues cv=new ContentValues();
			//cv.put(Drum_date, date);
			cv.put(Drum_Capacity, "5 ltr");
			
			cv.put(Drum_Quantity, 0);
			db.insert(DATABASE_TABLE4, null, cv);
			ContentValues cv1=new ContentValues();
			//cv.put(Drum_date, date);
			cv1.put(Drum_Capacity, "10 ltr");
			
			cv1.put(Drum_Quantity, 0);
			db.insert(DATABASE_TABLE4, null, cv1);
			ContentValues cv2=new ContentValues();
			//cv.put(Drum_date, date);
			cv2.put(Drum_Capacity, "20 ltr");
			
			cv2.put(Drum_Quantity, 0);
			db.insert(DATABASE_TABLE4, null, cv2);
			ContentValues cv3=new ContentValues();
			//cv.put(Drum_date, date);
			cv3.put(Drum_Capacity, "35 ltr");
			
			cv3.put(Drum_Quantity, 0);
			db.insert(DATABASE_TABLE4, null, cv3);
			ContentValues cv4=new ContentValues();
			//cv.put(Drum_date, date);
			cv4.put(Drum_Capacity, "50 ltr");
			
			cv4.put(Drum_Quantity, 0);
			db.insert(DATABASE_TABLE4, null, cv4);
			ContentValues cv5=new ContentValues();
			//cv.put(Drum_date, date);
			cv5.put(Drum_Capacity, "200 ltr");
			
			cv5.put(Drum_Quantity, 0);
			db.insert(DATABASE_TABLE4, null, cv5);
	
			System.out.println("created 5");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE1);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE2);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE4);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE5);

			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE3);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE10);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE11);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE12);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE13);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE14);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE15);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE16);
			onCreate(db);
		}
		
	}
	
	public HotOrNot(Context c)
	{
		ourContext=c;
	}
	public HotOrNot open() throws SQLException
	{
		ourHelper=new DbHelper(ourContext);
		ourDatabase=ourHelper.getWritableDatabase();
		return this;
	}
	public HotOrNot openr() throws SQLException
	{
		ourHelper=new DbHelper(ourContext);
		ourDatabase=ourHelper.getReadableDatabase();
		return this;
	}
	public void close()
	{
		ourHelper.close();
	}
	public long createntry(String date, String quantity, String capacity,String inout,String desci) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(Drum_date, date);
		cv.put(Drum_Capacity, capacity);
		
		cv.put(Drum_Quantity, Integer.parseInt(quantity));
		cv.put(Drum_inout, inout);
		cv.put(Drum_desci, desci);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public long createnews(String id, String title, String content) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(_RowID, Integer.parseInt(id));
		cv.put(news_title, title);
		
		cv.put(news_content, content);
	
		return ourDatabase.insert(DATABASE_TABLE10, null, cv);
	}
	
	public long createvent(String id, String cname, String ename, String date1, String time1, String venue, String sender, String content2) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(_RowID, Integer.parseInt(id));
		cv.put(events_clubname, cname);
		cv.put(events_name, ename);
		cv.put(events_date, date1);
		cv.put(events_time, time1);
		cv.put(events_venue, venue);
		cv.put(events_sender, sender);
		
		cv.put(events_content, content2);
	
		return ourDatabase.insert(DATABASE_TABLE11, null, cv);
	}
	
	public int getcount() {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE1;
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int cnt = cursor.getCount();
	    cursor.close();
	    return cnt;
	
	}
	public Cursor getcname() {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE1;
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	}
	public long createcomp(String name, String bal) {
		// TODO Auto-generated method stub
		ContentValues cv2=new ContentValues();
		cv2.put(Com_Name, name);
		cv2.put(Com_Balance, Integer.parseInt(bal));
		return ourDatabase.insert(DATABASE_TABLE1, null, cv2);
		
	}
	public void updatebal(String cname, String amt1) {
		// TODO Auto-generated method stub
String countQuery = "SELECT  Balance FROM " + DATABASE_TABLE1+" WHERE Name='"+cname+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int d=cursor.getColumnIndex("Balance");
		String cbal=null;
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			cbal=cursor.getString(d);
			
		}
		int cur=Integer.parseInt(cbal);
		cur=cur+Integer.parseInt(amt1);
	    ContentValues up=new ContentValues();
	    up.put(Com_Balance, cur);
		ourDatabase.update(DATABASE_TABLE1, up, Com_Name+" = '"+cname+"'", null);
		
	}
	public void createSales(String date1, String cname, String quan,
			String rate1, String amt1, String desc1) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(Sales_Date, date1);
		cv.put(Sales_Cname, cname);
		
		cv.put(Sales_Quan, Integer.parseInt(quan));
		cv.put(Sales_Rate, Integer.parseInt(rate1));
		cv.put(Sales_Amt, Integer.parseInt(amt1));
		cv.put(Sales_Des, desc1);
		ourDatabase.insert(DATABASE_TABLE2, null, cv);

	}
	public void createpay(String date1, String cname, String amt, String desci) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(Sales_Date, date1);
		cv.put(Sales_Cname, cname);
		
		cv.put(Sales_Amt, Integer.parseInt(amt));
		cv.put(Sales_Des, desci);
		ourDatabase.insert(DATABASE_TABLE3, null, cv);
	
	}
	public void redbal(String cname, String amt) {
		// TODO Auto-generated method stub
		
String countQuery = "SELECT  Balance FROM " + DATABASE_TABLE1+" WHERE Name='"+cname+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int d=cursor.getColumnIndex("Balance");
		String cbal=null;
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			cbal=cursor.getString(d);
			
		}
		int cur=Integer.parseInt(cbal);
		cur=cur-Integer.parseInt(amt);
	    ContentValues up=new ContentValues();
	    up.put(Com_Balance, cur);
		ourDatabase.update(DATABASE_TABLE1, up, Com_Name+" = '"+cname+"'", null);
		
	
	}
	public int getpaycount(String cname) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE3 +" WHERE "+Sales_Cname+" = '"+cname+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int cnt = cursor.getCount();
	    cursor.close();
	    return cnt;
	


	}
	public int gettrancount(String cname) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE2 +" WHERE "+Sales_Cname+" = '"+cname+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int cnt = cursor.getCount();
	    cursor.close();
	    return cnt;
	

	}
	public Cursor getpaycur(String cname) {
		// TODO Auto-generated method stub
String countQuery = "SELECT  * FROM " + DATABASE_TABLE3 +" WHERE "+Sales_Cname+" = '"+cname+"'";

	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	}
	public Cursor getsalescur(String cname) {
		// TODO Auto-generated method stub
String countQuery = "SELECT  * FROM " + DATABASE_TABLE2 +" WHERE "+Sales_Cname+" = '"+cname+"'";

	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	}
	public String getbal(String cname) {
		// TODO Auto-generated method stub
String countQuery = "SELECT  Balance FROM " + DATABASE_TABLE1+" WHERE Name='"+cname+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int d=cursor.getColumnIndex("Balance");
		String cbal=null;
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			cbal=cursor.getString(d);
			
		}
		
		return cbal;
	}
	public void incdrum(String quantity1, String capacity) {
		// TODO Auto-generated method stub
String countQuery = "SELECT "+Drum_Quantity + " FROM " + DATABASE_TABLE4+" WHERE "+Drum_Capacity+" ='"+capacity+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int d=cursor.getColumnIndex(Drum_Quantity);
		String cbal=null;
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			cbal=cursor.getString(d);
			
		}
		int cur=Integer.parseInt(cbal);
		cur=cur+Integer.parseInt(quantity1);
	    ContentValues up=new ContentValues();
	    up.put(Drum_Quantity, cur);
		ourDatabase.update(DATABASE_TABLE4, up, Drum_Capacity+" = '"+capacity+"'", null);
		
	}
	public void decdrum(String quantity1, String capacity) {
		// TODO Auto-generated method stub
String countQuery = "SELECT "+Drum_Quantity + " FROM " + DATABASE_TABLE4+" WHERE "+Drum_Capacity+" ='"+capacity+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int d=cursor.getColumnIndex(Drum_Quantity);
		String cbal=null;
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			cbal=cursor.getString(d);
			
		}
		int cur=Integer.parseInt(cbal);
		
		cur=cur-Integer.parseInt(quantity1);
	    ContentValues up=new ContentValues();
	    up.put(Drum_Quantity, cur);
		System.out.println("Final stat="+cur);
	    ourDatabase.update(DATABASE_TABLE4, up, Drum_Capacity+" = '"+capacity+"'", null);
		
	}
	public int getcountdrumentry(String capacity) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE +" WHERE "+Drum_Capacity+" = '"+capacity+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int cnt = cursor.getCount();
	    cursor.close();
	    return cnt;
	
	}
	public Cursor getdrumentry(String capacity) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE +" WHERE "+Drum_Capacity+" = '"+capacity+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	
	}
	public Cursor getnewsentry() {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE10 +" WHERE "+_RowID+" = (Select max(_id) from "+DATABASE_TABLE10+" )";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	
	}
	public String getbaldrum(String capacity) {
		// TODO Auto-generated method stub
String countQuery = "SELECT "+Drum_Quantity  +" FROM " + DATABASE_TABLE4+" WHERE "+Drum_Capacity+" = '"+capacity+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int d=cursor.getColumnIndex(Drum_Quantity);
		String cbal=null;
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			cbal=cursor.getString(d);
			
		}
		
		return cbal;
	
	}
	public Cursor getSalesEntry(String id) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE2 +" WHERE "+Sales_Id+" = "+id;

	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	
	}
	public void updateSales(String date, String des, String rate, String qua,
			String amit, String id) {
		// TODO Auto-generated method stub
    ContentValues up=new ContentValues();
	    up.put(Sales_Date, date);
	    up.put(Sales_Des, des);
	    up.put(Sales_Rate, rate);
	    up.put(Sales_Quan, qua);
	    up.put(Sales_Amt, amit);
	    
		ourDatabase.update(DATABASE_TABLE2, up, Sales_Id+" = "+id, null);
		
		
	}
	public void updatebalforedittedsales(int i, String cooom) {
		// TODO Auto-generated method stub
		System.out.println("in sales balance update");
		String countQuery = "SELECT  Balance FROM " + DATABASE_TABLE1+" WHERE Name='"+cooom+"'";
	    
	    
Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int d=cursor.getColumnIndex("Balance");
		String cbal=null;
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			cbal=cursor.getString(d);
			
		}
		int cur=Integer.parseInt(cbal);
		cur=cur+i;
	    ContentValues up=new ContentValues();
	    up.put(Com_Balance, cur);
		ourDatabase.update(DATABASE_TABLE1, up, Com_Name+" = '"+cooom+"'", null);
		
	
		
	}
	public Cursor getPayEntry(String id) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE3 +" WHERE "+Sales_Id+" = "+id;

	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	
	}
	public void updatePay(String date, String des, String amit, String id) {
		// TODO Auto-generated method stub
	    ContentValues up=new ContentValues();
	    up.put(Sales_Date, date);
	    up.put(Sales_Des, des);
	    
	    up.put(Sales_Amt, amit);
	    
		ourDatabase.update(DATABASE_TABLE3, up, Sales_Id+" = "+id, null);
			
		
	}
	public void updatebalforedittedPay(int i, String cooom) {
		// TODO Auto-generated method stub
String countQuery = "SELECT  Balance FROM " + DATABASE_TABLE1+" WHERE Name='"+cooom+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int d=cursor.getColumnIndex("Balance");
		String cbal=null;
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			cbal=cursor.getString(d);
			
		}
		int cur=Integer.parseInt(cbal);
		cur=cur-i;
	    ContentValues up=new ContentValues();
	    up.put(Com_Balance, cur);
		ourDatabase.update(DATABASE_TABLE1, up, Com_Name+" = '"+cooom+"'", null);
		
	
	}
	public void delsal(String idi) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE2, Sales_Id + "=" +idi, null);
		
	}
	public void delPay(String idi) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE3, Sales_Id + "=" +idi, null);
	}
	public void createforwadbal(String name, String bal, String string, String cdate) {
		// TODO Auto-generated method stub
		ContentValues cv2=new ContentValues();
		cv2.put(Sales_Cname, name);
		cv2.put(Sales_Amt, Integer.parseInt(bal));
		cv2.put(Sales_Des,string);
		cv2.put(Sales_Date, cdate);
		ourDatabase.insert(DATABASE_TABLE5, null, cv2);
		
	}
	public int getforwadedbalcount(String cname) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE5 +" WHERE "+Sales_Cname+" = '"+cname+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int cnt = cursor.getCount();
	    cursor.close();
	    return cnt;
	

	}
	public Cursor getforwadedbal(String cname) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE5 +" WHERE "+Sales_Cname+" = '"+cname+"'";

	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	
	}
	public Cursor getdumEntry(String idi) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE +" WHERE "+Drum_RowID+" = "+idi;

	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	
	}
	public void deldrumentry(String idi) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, Drum_RowID + "=" +idi, null);
			
	}
	public Cursor getForwadEntry(String idi) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT  * FROM " + DATABASE_TABLE5 +" WHERE "+Sales_Id+" = '"+idi+"'";

	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	
	}
	public void delFor(String idi) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE5, Sales_Id + "=" +idi, null);
		
	}
	public void updatebalforwadPay(int i, String cooom) {
		// TODO Auto-generated method stub
String countQuery = "SELECT  Balance FROM " + DATABASE_TABLE1+" WHERE Name='"+cooom+"'";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int d=cursor.getColumnIndex("Balance");
		String cbal=null;
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			cbal=cursor.getString(d);
			
		}
		int cur=Integer.parseInt(cbal);
		cur=cur-i;
	    ContentValues up=new ContentValues();
	    up.put(Com_Balance, cur);
		ourDatabase.update(DATABASE_TABLE1, up, Com_Name+" = '"+cooom+"'", null);
		
	}
	public void updateforwadentry(String amit, String id) {
		// TODO Auto-generated method stub
		ContentValues up=new ContentValues();
	    
	    up.put(Sales_Amt, amit);
	    
		ourDatabase.update(DATABASE_TABLE5, up, Sales_Id+" = "+id, null);
			
		
	}
	public int getcountnews() {
		// TODO Auto-generated method stub
	String countQuery = "SELECT  * FROM " + DATABASE_TABLE10;
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int cnt = cursor.getCount();
	    cursor.close();
	    return cnt;
	
	}
	public int getcountevents() {
		// TODO Auto-generated method stub
String countQuery = "SELECT  * FROM " + DATABASE_TABLE11;
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int cnt = cursor.getCount();
	    cursor.close();
	    return cnt;
	
	}
	public Cursor geteventsentry() {
		// TODO Auto-generated method stub
	String countQuery = "SELECT  * FROM " + DATABASE_TABLE11;
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	
	}
	public Cursor getallnewsentry() {
		// TODO Auto-generated method stub
	String countQuery = "SELECT  * FROM " + DATABASE_TABLE10 ;
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	}
	public Cursor getmaxeventsentry() {
		// TODO Auto-generated method stub
	String countQuery = "SELECT  * FROM " + DATABASE_TABLE11 +" WHERE "+_RowID+" = (Select max(_id) from "+ DATABASE_TABLE11+" )";
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    
		return cursor;
	
	}
	public void creattimetableentry(String beg, String end, String details3, int k) {
		// TODO Auto-generated method stub
		ContentValues cv2=new ContentValues();
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		Date date10 = null;
		Date date11 = null;
		try {
		 date10 = sdf1.parse(beg);
			 date11 = sdf1.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fbe=sdf1.format(date10);
		String fend=sdf1.format(date11);
		
		
		cv2.put(timein, fbe);
		cv2.put(timeout, fend);
		cv2.put(details,details3);
		switch(k)
		{
		case 0:
			ourDatabase.insert(DATABASE_TABLE12, null, cv2);	
			break;
		case 1:
			ourDatabase.insert(DATABASE_TABLE13, null, cv2);	
			break;
		case 2:
			ourDatabase.insert(DATABASE_TABLE14, null, cv2);	
			break;
		case 3:
			ourDatabase.insert(DATABASE_TABLE15, null, cv2);	
			break;
		case 4:
			ourDatabase.insert(DATABASE_TABLE16, null, cv2);	
			break;
		}
		
	
	}
	public Cursor getappropriatetimetable() {
		// TODO Auto-generated method stub
		
		String countQuery=null;
		Calendar c = Calendar.getInstance(); 
		int dayofweek=c.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		String str = sdf1.format(new Date());
		String countQuery1=null;
		Cursor cursor1=null;
		int d;
		String cbal=null;
		String cbal1=null;
		switch(dayofweek)
		{	
		case 7:
		case 1:
			countQuery = "SELECT * FROM " + DATABASE_TABLE12 +" order by "+ timein +" asc limit 2";
			break;
		case 2:
			 countQuery1 = "SELECT  "+timein+" FROM " + DATABASE_TABLE12+" order by "+ timein +" asc limit 1";
		    
		     cursor1 = ourDatabase.rawQuery(countQuery1, null);
		    d=cursor1.getColumnIndex(timein);
			 cbal=null;
			for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
				cbal=cursor1.getString(d);
				
				
			}
countQuery1 = "SELECT  "+timeout+" FROM " + DATABASE_TABLE12+" order by "+ timeout +" desc limit 1";
		    
		    cursor1 = ourDatabase.rawQuery(countQuery1, null);
		   d=cursor1.getColumnIndex(timeout);
			 cbal1=null;
			for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
				cbal1=cursor1.getString(d);
				System.out.println("here it is in for loop");
				
			}
			
			if(isbeforeall(str,cbal))
			{
				countQuery = "SELECT * FROM " + DATABASE_TABLE12 +" order by "+ timein +" asc limit 2";
					
			}
				else if(isafterall(str,cbal1))
			{
					countQuery = "SELECT * FROM " + DATABASE_TABLE13 +" order by "+ timein +" asc limit 2";
					
			}
				else
			{
					countQuery1 = "SELECT * FROM " + DATABASE_TABLE12;
				    
				    cursor1 = ourDatabase.rawQuery(countQuery1, null);
				   d=cursor1.getCount();
				   String tin[]=new String[d];
				   String tout[]=new String[d];
				int ctin=cursor1.getColumnIndex(timein);
				int ctout=cursor1.getColumnIndex(timeout);
				   
				int j=0;
					for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
						tin[j]=cursor1.getString(ctin);
						tout[j++]=cursor1.getString(ctout);
						
					}
					
					for(j=1;j<d;j++)
					{
						if(isbetween(str,tin[j-1],tin[j]))
							break;
					}
					j--;
					if(j==d-1)
					countQuery = "SELECT * FROM " + DATABASE_TABLE12 +" where "+ timein +" = '"+tin[j]+"'";
					else
						countQuery = "SELECT * FROM " + DATABASE_TABLE12 +" where "+ timein +" = '"+tin[j++]+"' OR "+timein +" = '"+tin[j]+"'";
							
			}
		
		break;
		case 3:
 countQuery1 = "SELECT  "+timein+" FROM " + DATABASE_TABLE13+" order by "+ timein +" asc limit 1";
		    
	 cursor1 = ourDatabase.rawQuery(countQuery1, null);
		     d=cursor1.getColumnIndex(timein);
			 cbal=null;
			for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
				cbal=cursor1.getString(d);
				
				
			}
countQuery1 = "SELECT  "+timeout+" FROM " + DATABASE_TABLE13+" order by "+ timeout +" desc limit 1";
		    
		    cursor1 = ourDatabase.rawQuery(countQuery1, null);
		   d=cursor1.getColumnIndex(timeout);
			 cbal1=null;
			 System.out.println("Count of entry " +cursor1.getCount());
			for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
				cbal1=cursor1.getString(d);
				System.out.println("In the loop for tuesday");
				
			}
			
			if(isbeforeall(str,cbal))
			{
				countQuery = "SELECT * FROM " + DATABASE_TABLE13 +" order by "+ timein +" asc limit 2";
					
			}
				else if(isafterall(str,cbal1))
			{
					countQuery = "SELECT * FROM " + DATABASE_TABLE14 +" order by "+ timein +" asc limit 2";
					
			}
				else
			{
					countQuery1 = "SELECT * FROM " + DATABASE_TABLE13;
				    
				    cursor1 = ourDatabase.rawQuery(countQuery1, null);
				   d=cursor1.getCount();
				   String tin[]=new String[d];
				   String tout[]=new String[d];
				int ctin=cursor1.getColumnIndex(timein);
				int ctout=cursor1.getColumnIndex(timeout);
				   
				int j=0;
					for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
						tin[j]=cursor1.getString(ctin);
						tout[j++]=cursor1.getString(ctout);
						
					}
					
					for(j=1;j<d;j++)
					{
						if(isbetween(str,tin[j-1],tin[j]))
							break;
					}
					j--;
					if(j==d-1)
					countQuery = "SELECT * FROM " + DATABASE_TABLE13 +" where "+ timein +" = '"+tin[j]+"'";
					else
						countQuery = "SELECT * FROM " + DATABASE_TABLE13 +" where "+ timein +" = '"+tin[j++]+"' OR "+timein+" = '"+tin[j]+"'";
							
			}	break;
		case 4:
			countQuery1 = "SELECT  "+timein+" FROM " + DATABASE_TABLE14+" order by "+ timein +" asc limit 1";
		    
			 cursor1 = ourDatabase.rawQuery(countQuery1, null);
				     d=cursor1.getColumnIndex(timein);
					 cbal=null;
					for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
						cbal=cursor1.getString(d);
						
						
					}
		countQuery1 = "SELECT  "+timeout+" FROM " + DATABASE_TABLE14+" order by "+ timeout +" desc limit 1";
				    
				    cursor1 = ourDatabase.rawQuery(countQuery1, null);
				   d=cursor1.getColumnIndex(timeout);
					 cbal1=null;
					for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
						cbal1=cursor1.getString(d);
						
						
					}
					
					if(isbeforeall(str,cbal))
					{
						countQuery = "SELECT * FROM " + DATABASE_TABLE14 +" order by "+ timein +" asc limit 2";
							
					}
						else if(isafterall(str,cbal1))
					{
							countQuery = "SELECT * FROM " + DATABASE_TABLE15 +" order by "+ timein +" asc limit 2";
							
					}
						else
					{
							countQuery1 = "SELECT * FROM " + DATABASE_TABLE14;
						    
						    cursor1 = ourDatabase.rawQuery(countQuery1, null);
						   d=cursor1.getCount();
						   String tin[]=new String[d];
						   String tout[]=new String[d];
						int ctin=cursor1.getColumnIndex(timein);
						int ctout=cursor1.getColumnIndex(timeout);
						   
						int j=0;
							for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
								tin[j]=cursor1.getString(ctin);
								tout[j++]=cursor1.getString(ctout);
								
							}
							
							for(j=1;j<d;j++)
							{
								if(isbetween(str,tin[j-1],tin[j]))
									break;
							}
							j--;
							if(j==d-1)
							countQuery = "SELECT * FROM " + DATABASE_TABLE14 +" where "+ timein +" = '"+tin[j]+"'";
							else
								countQuery = "SELECT * FROM " + DATABASE_TABLE14 +" where "+ timein +" = '"+tin[j++]+"' OR "+timein+" = '"+tin[j]+"'";
								
					}	break;
		case 5:
			countQuery1 = "SELECT  "+timein+" FROM " + DATABASE_TABLE15+" order by "+ timein +" asc limit 1";
		    
			 cursor1 = ourDatabase.rawQuery(countQuery1, null);
				     d=cursor1.getColumnIndex(timein);
					 cbal=null;
					for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
						cbal=cursor1.getString(d);
						
						
					}
		countQuery1 = "SELECT  "+timeout+" FROM " + DATABASE_TABLE15+" order by "+ timeout +" desc limit 1";
				    
				    cursor1 = ourDatabase.rawQuery(countQuery1, null);
				   d=cursor1.getColumnIndex(timeout);
					 cbal1=null;
					for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
						cbal1=cursor1.getString(d);
						
						
					}
					
					if(isbeforeall(str,cbal))
					{
						countQuery = "SELECT * FROM " + DATABASE_TABLE15 +" order by "+ timein +" asc limit 2";
							
					}
						else if(isafterall(str,cbal1))
					{
							countQuery = "SELECT * FROM " + DATABASE_TABLE16 +" order by "+ timein +" asc limit 2";
							
					}
						else
					{
							countQuery1 = "SELECT * FROM " + DATABASE_TABLE15;
						    
						    cursor1 = ourDatabase.rawQuery(countQuery1, null);
						   d=cursor1.getCount();
						   String tin[]=new String[d];
						   String tout[]=new String[d];
						int ctin=cursor1.getColumnIndex(timein);
						int ctout=cursor1.getColumnIndex(timeout);
						   
						int j=0;
							for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
								tin[j]=cursor1.getString(ctin);
								tout[j]=cursor1.getString(ctout);
								j++;
							}
							
							for(j=1;j<d;j++)
							{
								
								if(isbetween(str,tin[j-1],tin[j]))
									{
									break;
									}
								
							}
							System.out.println("Value of j :" +j);
							j--;
							
							if(j==d-1)
							countQuery = "SELECT * FROM " + DATABASE_TABLE15 +" where "+ timein +" = '"+tin[j]+"'";
							else
								countQuery = "SELECT * FROM " + DATABASE_TABLE15 +" where "+ timein +" = '"+tin[j++]+"' OR "+timein+" = '"+tin[j]+"'";
								
					}break;
		case 6:
			countQuery1 = "SELECT  "+timein+" FROM " + DATABASE_TABLE16+" order by "+ timein +" asc limit 1";
		    
			 cursor1 = ourDatabase.rawQuery(countQuery1, null);
				     d=cursor1.getColumnIndex(timein);
					 cbal=null;
					for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
						cbal=cursor1.getString(d);
						
						
					}
		countQuery1 = "SELECT  "+timeout+" FROM " + DATABASE_TABLE16+" order by "+ timeout +" desc limit 1";
				    
	    cursor1 = ourDatabase.rawQuery(countQuery1, null);
		   d=cursor1.getColumnIndex(timeout);
			 cbal1=null;
			for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
				cbal1=cursor1.getString(d);
				
				
			}
			
					if(isbeforeall(str,cbal))
					{
						countQuery = "SELECT * FROM " + DATABASE_TABLE16 +" order by "+ timein +" asc limit 2";
							
					}
						else if(isafterall(str,cbal1))
					{
							countQuery = "SELECT * FROM " + DATABASE_TABLE12 +" order by "+ timein +" asc limit 2";
							
					}
						else
					{
							countQuery1 = "SELECT * FROM " + DATABASE_TABLE16;
						    
						    cursor1 = ourDatabase.rawQuery(countQuery1, null);
						   d=cursor1.getCount();
						   String tin[]=new String[d];
						   String tout[]=new String[d];
						int ctin=cursor1.getColumnIndex(timein);
						int ctout=cursor1.getColumnIndex(timeout);
						   
						int j=0;
							for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
								tin[j]=cursor1.getString(ctin);
								tout[j++]=cursor1.getString(ctout);
								
							}
							
							for(j=1;j<d;j++)
							{
								if(isbetween(str,tin[j-1],tin[j]))
									break;
							}
							j--;
							if(j==d-1)
							countQuery = "SELECT * FROM " + DATABASE_TABLE16 +" where "+ timein +" = '"+tin[j]+"'";
							else
								countQuery = "SELECT * FROM " + DATABASE_TABLE16 +" where "+ timein +" = '"+tin[j++]+"' OR "+timein+" = '"+tin[j]+"'";
								
					}break;
	}
	    System.out.println(countQuery);
		Cursor cursor12 = ourDatabase.rawQuery(countQuery, null);
	       d=cursor12.getColumnIndex(timein);
			 cbal1=null;
			 
				 
			for(cursor12.moveToFirst();!cursor12.isAfterLast();cursor12.moveToNext()){
				cbal1=cursor12.getString(d);
				System.out.println("In for loop  and value of timein is:"+cbal1);
				
			}
		
		return cursor12;
	
	}
	private boolean isbetween(String str, String string, String string2) {
		// TODO Auto-generated method stub
		int instr,lincbal,uincbal,endstr,lendcbal,uendcbal;
		instr=Integer.parseInt(str.substring(0, str.indexOf(':')));
		lincbal=Integer.parseInt(string.substring(0, string.indexOf(':')));
		uincbal=Integer.parseInt(string2.substring(0, string2.indexOf(':')));
		
		endstr=Integer.parseInt(str.substring(str.indexOf(':')+1));
		lendcbal=Integer.parseInt(string.substring(string.indexOf(':')+1));
		uendcbal=Integer.parseInt(string2.substring(string2.indexOf(':')+1));
		
		if(lincbal<instr&&instr<uincbal)
			return true;
		else if((instr==lincbal&&endstr<(uincbal-lincbal)*60)||(instr==uincbal&&endstr<uendcbal))
			return true;
		else
			return false;
	}
	private boolean isafterall(String str, String cbal1) {
		// TODO Auto-generated method stub
		int instr,incbal,endstr,endcbal;
		instr=Integer.parseInt(str.substring(0, str.indexOf(':')));
		incbal=Integer.parseInt(cbal1.substring(0, cbal1.indexOf(':')));
		endstr=Integer.parseInt(str.substring(str.indexOf(':')+1));
		endcbal=Integer.parseInt(cbal1.substring(cbal1.indexOf(':')+1));
		if(instr>incbal)
			return true;
		else if(instr>incbal&&endstr>endcbal)
			return true;
		else
			return false;
	}
	private boolean isbeforeall(String str, String cbal) {
		// TODO Auto-generated method stub
		int instr,incbal,endstr,endcbal;
		instr=Integer.parseInt(str.substring(0, str.indexOf(':')));
		incbal=Integer.parseInt(cbal.substring(0, cbal.indexOf(':')));
		endstr=Integer.parseInt(str.substring(str.indexOf(':')+1));
		endcbal=Integer.parseInt(cbal.substring(cbal.indexOf(':')+1));
		if(instr<=incbal)
			return true;
		else 
			return false;
		
		
	
	
	}
	
	public int gettablerows(int k) {
		// TODO Auto-generated method stub
		String countQuery=null;
		switch(k)
		{
		case 0:
		 countQuery = "SELECT  * FROM " + DATABASE_TABLE12;
		    break;
		case 1:
			 countQuery = "SELECT  * FROM " + DATABASE_TABLE13;
			    break;
		case 2:
			 countQuery = "SELECT  * FROM " + DATABASE_TABLE14;
			    break;
		case 3:
			 countQuery = "SELECT  * FROM " + DATABASE_TABLE15;
			    break;
		case 4:
			 countQuery = "SELECT  * FROM " + DATABASE_TABLE16;
			    break;
		}
		
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    int cnt = cursor.getCount();
	    cursor.close();
	    return cnt;
	
	}
	
	public Cursor gettimetableentry(int k) {
		// TODO Auto-generated method stub
		String countQuery=null;
		switch(k)
		{
		case 0:
		 countQuery = "SELECT  * FROM " + DATABASE_TABLE12;
		    break;
		case 1:
			 countQuery = "SELECT  * FROM " + DATABASE_TABLE13;
			    break;
		case 2:
			 countQuery = "SELECT  * FROM " + DATABASE_TABLE14;
			    break;
		case 3:
			 countQuery = "SELECT  * FROM " + DATABASE_TABLE15;
			    break;
		case 4:
			 countQuery = "SELECT  * FROM " + DATABASE_TABLE16;
			    break;
		}
		
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	   
	    return cursor;
	
	}
}
