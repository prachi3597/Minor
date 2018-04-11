package domhelp.com.minor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "worker.db";
    public static final String TABLE_NAME = "worker_table";
    public static final String SNO = "SNO";
    public static final String PH_NO = "MBNO";
    public static final String UID = "USERID";
    public static final String NAME = "NAME";
    public static final String GENDER = "GENDER";
    public static final String AGE = "AGE";



    public Databasehelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL("create table " + TABLE_NAME +"(SNO INTEGER PRIMARY KEY AUTOINCREMENT,PH_NO INTEGER,UID TEXT,NAME TEXT,GENDER TEXT,AGE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
         onCreate(db);
    }
}
