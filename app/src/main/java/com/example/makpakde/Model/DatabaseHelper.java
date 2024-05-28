    package com.example.makpakde.Model;

    import android.annotation.SuppressLint;
    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    import androidx.annotation.Nullable;
    import androidx.annotation.StringDef;

    import com.example.makpakde.EdamamAPI.Recipe;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;

    public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "my_database";
        public static final int DATABASE_VERSION = 1;
        public static final String TABLE_USER = "users";
        public static final String USER_COLUMN_ID = "id";
        public static final String USER_COLUMN_FULLNAME = "fullname";
        public static final String USER_COLUMN_USERNAME = "username";
        public static final String USER_COLUMN_PASSWORD = "password";
        public static final String TABLE_RECENT_RECIPE = "recent_recipe";
        public static final String RECIPE_COLUMN_ID = "id";
        public static final String RECIPE_COLUMN_RECIPE_ID = "recipe_id";
        public static final String RECIPE_COLUMN_USER_ID = "user_id";
        public static final String TABLE_BOOKMARK = "bookmarks";
        public static final String BOOKMARK_COLUMN_ID = "id";
        public static final String BOOKMARK_COLUMN_RECIPE_ID = "recipe_id";
        public static final String BOOKMARK_COLUMN_USER_ID = "user_id";

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_USER + " ("
                    + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + USER_COLUMN_FULLNAME + " TEXT, "
                    + USER_COLUMN_USERNAME + " TEXT, "
                    + USER_COLUMN_PASSWORD + " TEXT )");
            db.execSQL("CREATE TABLE " + TABLE_RECENT_RECIPE + " ("
                    + RECIPE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + RECIPE_COLUMN_RECIPE_ID + " TEXT, "
                    + RECIPE_COLUMN_USER_ID + " INTEGER, "
                    + "FOREIGN KEY(" + RECIPE_COLUMN_USER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + "))");
            db.execSQL("CREATE TABLE " + TABLE_BOOKMARK + " ("
                    + BOOKMARK_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BOOKMARK_COLUMN_RECIPE_ID + " TEXT, "
                    + BOOKMARK_COLUMN_USER_ID + " INTEGER, "
                    + "FOREIGN KEY(" + BOOKMARK_COLUMN_USER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + "))");

        }

        public int loginUser(String username) {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] projection = {USER_COLUMN_ID};
            String selection = USER_COLUMN_USERNAME + " = ?";
            String[] selectionArgs = {username};

            Cursor cursor = db.query(TABLE_USER, projection, selection, selectionArgs, null, null, null);
            int userId = -1; // Nilai default jika login gagal

            if (cursor.moveToFirst()) {
                userId = cursor.getInt(cursor.getColumnIndexOrThrow(USER_COLUMN_ID));
            }

            cursor.close();
            db.close();

            return userId;
        }

        public Set<String> getRecipeIdsByUserId(int userId) {
            Set<String> recipeIds = new HashSet<>();
            SQLiteDatabase db = getReadableDatabase();
            String[] columns = {RECIPE_COLUMN_RECIPE_ID};
            String selection = RECIPE_COLUMN_USER_ID + " = ?";
            String[] selectionArgs = {String.valueOf(userId)};

            Cursor cursor = db.query(TABLE_RECENT_RECIPE, columns, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    String recipeId = cursor.getString(cursor.getColumnIndexOrThrow(RECIPE_COLUMN_RECIPE_ID));
                    int hashIndex = recipeId.indexOf('#');
                    int underscoreIndex = recipeId.indexOf('_', hashIndex);
                    String recipeIdFix = recipeId.substring(underscoreIndex + 1);

                    recipeIds.add(recipeIdFix);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return recipeIds;
        }

        public Set<String> getRecipeIdsByUserIdBookmark(int userId) {
            Set<String> recipeIds = new HashSet<>();
            SQLiteDatabase db = getReadableDatabase();
            String[] columns = {BOOKMARK_COLUMN_RECIPE_ID};
            String selection = BOOKMARK_COLUMN_USER_ID + " = ?";
            String[] selectionArgs = {String.valueOf(userId)};

            Cursor cursor = db.query(TABLE_BOOKMARK, columns, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    String recipeId = cursor.getString(cursor.getColumnIndexOrThrow(BOOKMARK_COLUMN_RECIPE_ID));
                    int hashIndex = recipeId.indexOf('#');
                    int underscoreIndex = recipeId.indexOf('_', hashIndex);
                    String recipeIdFix = recipeId.substring(underscoreIndex + 1);

                    recipeIds.add(recipeIdFix);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return recipeIds;
        }


        public void insertDataUser(String fullname,String username, String password){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(USER_COLUMN_FULLNAME, fullname);
            values.put(USER_COLUMN_USERNAME, username);
            values.put(USER_COLUMN_PASSWORD, password);

            db.insert(TABLE_USER, null, values);
        }

        public void insertRecentRecipe(String recipe_id, int userId) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(RECIPE_COLUMN_RECIPE_ID, recipe_id);
            values.put(RECIPE_COLUMN_USER_ID, userId);
            db.insert(TABLE_RECENT_RECIPE, null, values);
        }

        public void insertBookmarkRecipe(String recipe_id, int userId) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BOOKMARK_COLUMN_RECIPE_ID, recipe_id);
            values.put(BOOKMARK_COLUMN_USER_ID, userId);
            db.insert(TABLE_BOOKMARK, null, values);
        }

        public Cursor getAllRecordRecentRecipe(){
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery("SELECT * FROM " + TABLE_RECENT_RECIPE, null);
        }


        public Boolean checkUsername(String username){
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? ", new String[]{username});
            if (cursor.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        }

        public Boolean checkUsernamePassword(String username, String password){
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ? ", new String[]{username, password});
            if (cursor.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        }

        public void updateRecordUserFullname(int id, String fullname){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(USER_COLUMN_FULLNAME, fullname);
            db.update(TABLE_USER, values, USER_COLUMN_ID + " = ? ", new String[]{String.valueOf(id)});
        }

        public void updateRecordUserPassword(int id, String password){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(USER_COLUMN_PASSWORD, password);
            db.update(TABLE_USER, values, USER_COLUMN_ID + " = ? ", new String[]{String.valueOf(id)});
        }

        public void deleteRecordUser(int id){
            SQLiteDatabase db = getWritableDatabase();
            db.delete(TABLE_USER, USER_COLUMN_ID + " = " + id, null);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
