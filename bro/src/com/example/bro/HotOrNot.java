package com.example.bro;

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
	public static final String Drum_Capacity="Capacity";
	public static final String Drum_Quantity="Quantity1";
	public static final String Drum_date="date1";
	public static final String Drum_inout="inout";
	
	public static final String Drum_desci="desci";
	
	private static final String DATABASE_NAME="HotOrNotdb";
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
	
}
