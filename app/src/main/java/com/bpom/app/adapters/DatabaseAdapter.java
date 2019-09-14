package com.bpom.app.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "LOCAL_DB";
    private static final int DATABASE_VERSION = 1;
    /*public static final String TABLE_V_QUESTIONER = "v_questioner";
    public static final String TB_V_QUESTION = "v_question";*/
    public static final String TB_V_RESPONDEN = "v_responden";
    /*public static final String TB_B_ANSWER = "b_answer";*/



    //----------TB_V_RESPONDEN Key Field-----------------
    public static final String b_id = "b_id";
    public static final String b_survey_level = "b_survey_level";
    public static final String b_area_id = "b_area_id";
    public static final String b_name = "b_name";
    public static final String b_sex = "b_sex";
    public static final String b_dob = "b_dob";
    public static final String b_address = "b_address";
    public static final String b_email = "b_email";
    public static final String b_telepon = "b_telepon";
    public static final String b_instansi = "b_instansi";
    public static final String b_pendidikan = "b_pendidikan";
    public static final String b_longitude = "b_longitude";
    public static final String b_latitude = "b_latitude";
    public static final String b_status = "b_status";
    public static final String b_is_delete = "b_is_delete";
    public static final String b_delete_date = "b_delete_date";
    public static final String b_date = "b_date";
    public static final String b_survey_id = "b_survey_id";
    public static final String b_area_root_id = "b_area_root_id";
    public static final String b_area_level_one_id = "b_area_level_one_id";
    public static final String b_area_level_two_id = "b_area_level_two_id";
    public static final String b_area_level_three_id = "b_area_level_three_id";
    public static final String b_staff_id = "b_staff_id";
    public static final String b_full_name = "b_full_name";


    private static final String CREATE_TB_V_RESPONDEN = "CREATE TABLE "+ TB_V_RESPONDEN + "("
            + "id" + " integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + b_id + " integer(30) NOT NULL DEFAULT 1,"
            + b_survey_level + " text(255) NOT NULL DEFAULT '',"
            + b_area_id + " text(255) NOT NULL DEFAULT '',"
            + b_name + " text(30) NOT NULL DEFAULT '',"
            + b_sex + " text(255) NOT NULL DEFAULT '',"
            + b_dob + " text(255) NOT NULL DEFAULT '',"
            + b_address + " text(255) NOT NULL DEFAULT '',"
            + b_email + " text(255) NOT NULL DEFAULT '',"
            + b_telepon + " text(255) NOT NULL DEFAULT '',"
            + b_instansi + " text(255) NOT NULL DEFAULT '',"
            + b_pendidikan + " text(255) NOT NULL DEFAULT '',"
            + b_longitude + " text(255) NOT NULL DEFAULT '',"
            + b_latitude + " text(255) NOT NULL DEFAULT '',"
            + b_status + " text(255) NOT NULL DEFAULT '',"
            + b_is_delete + " text(255) NOT NULL DEFAULT '',"
            + b_delete_date + " text(255) NOT NULL DEFAULT '',"
            + b_date + " text(255) NOT NULL DEFAULT '',"
            + b_survey_id + " text(255) NOT NULL DEFAULT '',"
            + b_area_root_id + " text(255) NOT NULL DEFAULT '',"
            + b_area_level_one_id + " text(255) NOT NULL DEFAULT '',"
            + b_area_level_two_id + " text(255) NOT NULL DEFAULT '',"
            + b_area_level_three_id + " text(255) NOT NULL DEFAULT '',"
            + b_staff_id + " text(255) NOT NULL DEFAULT '',"
            + b_full_name + " text(255) NOT NULL DEFAULT ''"
            +" );";
    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_V_RESPONDEN);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + CREATE_TB_V_RESPONDEN + "'");
        onCreate(db);
    }

      /*public long addStudentDetail(String student) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, student);
       // insert row in students table
        long insert = db.insert(TABLE_STUDENTS, null, values);

        return insert;
    }
    public ArrayList<String> getAllStudentsList() {
        ArrayList<String> studentsArrayList = new ArrayList<String>();
        String name="";
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
               // adding to Students list
                studentsArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array", studentsArrayList.toString());
        }
        return studentsArrayList;
    }*/
}

