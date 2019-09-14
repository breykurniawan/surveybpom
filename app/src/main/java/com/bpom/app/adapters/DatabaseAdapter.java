package com.bpom.app.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "local_db";
    private static final int DATABASE_VERSION = 1;
    public static final String TB_SETTING = "setting";
    public static final String TB_V_RESPONDEN = "v_responden";
    public static final String TB_V_QUESTIONER = "v_questioner";
    public static final String TB_B_AREA = "b_area";
    public static final String TB_V_QUESTION = "v_question";
    public static final String TB_B_ANSWER = "b_answer";


    //----------TB_SETTING Key Field-----------------
    public static final String skey = "skey";
    public static final String svalue = "svalue";

    public static final String CREATE_TB_APP_SETTING = "CREATE TABLE "+ TB_SETTING + "("
            + "id" + " integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + skey + " text(255) NOT NULL DEFAULT '',"
            + svalue + " text(255) NOT NULL DEFAULT ''"
            +" );";

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

    public static final String CREATE_TB_V_RESPONDEN = "CREATE TABLE "+ TB_V_RESPONDEN + "("
            + "id" + " integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + b_id + " integer(30) NOT NULL DEFAULT 1,"
            + b_survey_level + " text(255) NOT NULL DEFAULT '',"
            + b_area_id + " text(255) NOT NULL DEFAULT '',"
            + b_name + " text(255) NOT NULL DEFAULT '',"
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

    //----------TB_V_QUESTIONER Key Field-----------------
    /*public static final String b_id = "b_id";
    public static final String b_name = "b_name";
    public static final String b_status = "b_status";
    public static final String b_date = "b_date";
    public static final String b_survey_id = "b_survey_id";*/
    public static final String b_users_id = "b_users_id";
    public static final String b_survey_name = "b_survey_name";
    public static final String b_users = "b_users";
    //public static final String b_is_delete = "b_is_delete";
    public static final String b_description = "b_description";

    public static final String CREATE_TB_V_QUESTIONER = "CREATE TABLE "+ TB_V_QUESTIONER + "("
            + "id" + " integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + b_id + " integer(30) NOT NULL DEFAULT 1,"
            + b_name + " text(255) NOT NULL DEFAULT '',"
            + b_status + " text(255) NOT NULL DEFAULT '',"
            + b_date + " text(255) NOT NULL DEFAULT '',"
            + b_survey_id + " text(255) NOT NULL DEFAULT '',"
            + b_users_id + " text(255) NOT NULL DEFAULT '',"
            + b_survey_name + " text(255) NOT NULL DEFAULT '',"
            + b_users + " text(255) NOT NULL DEFAULT '',"
            + b_is_delete + " text(255) NOT NULL DEFAULT '',"
            + b_description + " text(255) NOT NULL DEFAULT ''"
            +" );";


    //----------TB_V_QUESTION Key Field-----------------


    public static final String b_name_questioner = "b_name_questioner";
    //public static final String b_users_id = "b_users_id";
    public static final String b_position = "b_position";
    public static final String b_id_questioner = "b_id_questioner";
    //public static final String b_description = "b_description";
    public static final String question_type = "question_type";
    public static final String b_parent_id = "b_parent_id";
    public static final String b_title = "b_title";
    public static final String b_option_list = "b_option_list";
    public static final String b_question_id = "b_question_id";
    //public static final String b_survey_id = "b_survey_id";
    //public static final String b_is_delete = "b_is_delete";
    public static final String b_label = "b_label";

    public static final String CREATE_TB_V_QUESTION = "CREATE TABLE "+ TB_V_QUESTION + "("
            + "id" + " integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + b_name_questioner + " text(255) NOT NULL DEFAULT '',"
            + b_users_id + " text(255) NOT NULL DEFAULT '',"
            + b_position + " text(255) NOT NULL DEFAULT '',"
            + b_id_questioner + " text(255) NOT NULL DEFAULT '',"
            + b_description + " text(255) NOT NULL DEFAULT '',"
            + question_type + " text(255) NOT NULL DEFAULT '',"
            + b_parent_id + " text(255) NOT NULL DEFAULT '',"
            + b_title + " text(255) NOT NULL DEFAULT '',"
            + b_option_list + " text(255) NOT NULL DEFAULT '',"
            + b_question_id + " text(255) NOT NULL DEFAULT '',"
            + b_survey_id + " text(255) NOT NULL DEFAULT '',"
            + b_is_delete + " text(255) NOT NULL DEFAULT '',"
            + b_label + " text(255) NOT NULL DEFAULT ''"
            +" );";


    //----------TB_B_AREA Key Field-----------------
    //public static final String b_id = "b_id";
    public static final String b_area = "b_area";
    //public static final String b_description = "b_description";
    public static final String b_client = "b_client";
    public static final String b_start = "b_start";
    public static final String b_finish = "b_finish";
    public static final String b_target = "b_target";
    //public static final String b_status = "b_status";
    //public static final String b_is_delete = "b_is_delete";
    //public static final String b_delete_date = "b_delete_date";
    //public static final String b_date = "b_date";
    public static final String b_client_name = "b_client_name";
    public static final String count = "count";
    public static final String b_percent = "b_percent";
    public static final String b_area_level = "b_area_level";
    public static final String b_result = "b_result";

    public static final String CREATE_TB_B_AREA = "CREATE TABLE "+ TB_B_AREA + "("
            + "id" + " integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + b_id + " integer(30) NOT NULL DEFAULT 1,"
            + b_area + " text(255) NOT NULL DEFAULT '',"
            + b_description + " text(255) NOT NULL DEFAULT '',"
            + b_client + " text(255) NOT NULL DEFAULT '',"
            + b_start + " text(255) NOT NULL DEFAULT '',"
            + b_finish + " text(255) NOT NULL DEFAULT '',"
            + b_target + " text(255) NOT NULL DEFAULT '',"
            + b_status + " text(255) NOT NULL DEFAULT '',"
            + b_is_delete + " text(255) NOT NULL DEFAULT '',"
            + b_delete_date + " text(255) NOT NULL DEFAULT '',"
            + b_date + " text(255) NOT NULL DEFAULT '',"
            + b_client_name + " text(255) NOT NULL DEFAULT '',"
            + count + " text(255) NOT NULL DEFAULT '',"
            + b_percent + " text(255) NOT NULL DEFAULT '',"
            + b_area_level + " text(255) NOT NULL DEFAULT '',"
            + b_result + " text(255) NOT NULL DEFAULT ''"
            +" );";

    //----------TB_B_ANSWER Key Field-----------------


    //public static final String b_id = "b_id";
    //public static final String b_question_id = "b_question_id";
    public static final String b_option = "b_option";
    //public static final String b_is_delete = "b_is_delete";
    //public static final String b_delete_date = "b_delete_date";

    public static final String CREATE_TB_B_ANSWER = "CREATE TABLE "+ TB_B_ANSWER + "("
            + "id" + " integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + b_id + " integer(30) NOT NULL DEFAULT 1,"
            + b_question_id + " text(255) NOT NULL DEFAULT '',"
            + b_option + " text(255) NOT NULL DEFAULT '',"
            + b_is_delete + " text(255) NOT NULL DEFAULT '',"
            + b_delete_date + " text(255) NOT NULL DEFAULT ''"
            +" );";

    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_APP_SETTING);
        db.execSQL(CREATE_TB_V_RESPONDEN);
        db.execSQL(CREATE_TB_V_QUESTIONER);
        db.execSQL(CREATE_TB_V_QUESTION);
        db.execSQL(CREATE_TB_B_AREA);

        //db.execSQL(CREATE_TB_B_ANSWER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TB_SETTING + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TB_V_RESPONDEN + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TB_V_QUESTIONER + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TB_V_QUESTION + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TB_B_AREA + "'");
        //db.execSQL("DROP TABLE IF EXISTS '" + TB_B_ANSWER + "'");
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

