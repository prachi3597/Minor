package domhelp.com.minor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "worker.db";
    public static final String TABLE_NAME = "worker_table";
    public static final String SNO = "sno.";
    public static final String PH_NO = "mobno.";
    public static final String UID = "user";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String AGE = "age";



    public Databasehelper(Context context,) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
