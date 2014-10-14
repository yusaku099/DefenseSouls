package com.example.defense_souls;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "mydb";
	private static final int DB_VERSION = 1;

	public static final String TABLE_NAME = "score";
	public static final String COL_STAGENAME = "colName";
    public static final String COL_STAGENO = "colStageno";
    public static final String COL_STAGELEVEL = "colStagelevel";
	public static final String COL_SCORE = "colScore";
    public static final String COL_DATE = "colDate";
	private static final String STRING_CREATE =
		"CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ COL_STAGENAME + " TEXT, " + COL_STAGENO + " INTEGER, " + COL_STAGELEVEL + " INTEGER, " + COL_SCORE + " INTEGER, " + COL_DATE + " TEXT) ;" ;

	public MyDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// データベーステーブルの作成
		db.execSQL(STRING_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// データベースのクリアと作り直し
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);

	}

}
