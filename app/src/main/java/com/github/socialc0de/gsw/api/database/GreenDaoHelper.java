package com.github.socialc0de.gsw.api.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import com.github.socialc0de.gsw.api.database.dao.DaoMaster;
import com.github.socialc0de.gsw.api.database.dao.DaoSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */
public class GreenDaoHelper {
    public static final String DB_NAME = "gsw.db";

    private static Map<Pair<Context, String>, DaoMaster> daoMasterMap = new HashMap<Pair<Context, String>, DaoMaster>();
    private static Map<String, DaoSession> daoSessionMap = Collections.synchronizedMap(new HashMap());

    public static DaoSession newDaoSession(Context context, String name) {
        return newDaoSession(context, name, null, false);
    }

    public static DaoSession newDaoSession(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        return newDaoSession(context, name, factory, false);
    }

    public static DaoSession newDaoSession(
            Context context, String name, SQLiteDatabase.CursorFactory factory, boolean writeable) {
        final DaoMaster daoMaster = getDaoMaster(context, name, factory, writeable);
        return daoMaster.newSession();
    }

    public static DaoMaster getDaoMaster(
            Context context, String dbName, SQLiteDatabase.CursorFactory factory, boolean writeable) {
        final Pair<Context, String> keyPair = new Pair<Context, String>(context, dbName);
        if (!daoMasterMap.containsKey(keyPair)) {
            final DaoMaster.DevOpenHelper dbHelper = new DaoMaster.DevOpenHelper(context, dbName, factory);
            final DaoMaster daoMaster = new DaoMaster(
                    (writeable) ? dbHelper.getWritableDatabase() : dbHelper.getReadableDatabase());
            daoMasterMap.put(keyPair, daoMaster);
        }

        return daoMasterMap.get(keyPair);
    }

    public static DaoSession getDaoSession(Context context, String name) {
        synchronized (daoSessionMap) {
            if (!daoSessionMap.containsKey(name)) {
                daoSessionMap.put(name, newDaoSession(context.getApplicationContext(), name, null, false));
            }

            return daoSessionMap.get(name);
        }
    }

    /*
    public static void initDatabase(Context context, String dbName) {
        fillDatabase(context, "testcriterion.json", Testcriterion[].class, GswDB.class, "saveTestcriterions");
        fillDatabase(context, "contacts.json", Contact[].class, GswDB.class, "saveContacts");
        fillDatabase(context, "countries.json", Country[].class, GswDB.class, "saveCountries");
        fillDatabase(context, "questions.json", Question[].class, GswDB.class, "saveQuestions");
        fillDatabase(context, "categories.json", Category[].class, GswDB.class, "saveCategories");
        if(fillDatabase(context, "currencies.json", Currency[].class, GswDB.class, "saveCurrencies")) {
            try {
                GswDB.addEuroToCurrencyDatabase(context); // euro is not in json, so add it
            }catch (Exception e){
                Log.e("TEST", "Error in Database import, e="+e);
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString("PREF_DATABASE_CURRENCIES_UPDATEDATE", getStringFromResource(context, "currencies.json" + ".updatedate")).commit();
        }
    }

    private static boolean fillDatabase(Context context, String jsonScriptName, final Class itemClass, final Class dbClass, final String methodName){
        int currentJsonCodeVersion = PreferenceManager.getDefaultSharedPreferences(context).getInt(jsonScriptName+"_codeversion", 0);
        String newSqlCodeVersionTxt = getStringFromResource(context, jsonScriptName + ".version");
        int newJsonCodeVersion = 0;
        if(newSqlCodeVersionTxt != null){
            newJsonCodeVersion = new Integer(newSqlCodeVersionTxt);
        }
        if(newJsonCodeVersion > currentJsonCodeVersion) {
            try {
                String json = getStringFromResource(context, jsonScriptName);
                Object[] jsonObjects = (Object[])new Gson().fromJson(json, itemClass);

                Method method = dbClass.getMethod(methodName, Context.class, jsonObjects.getClass());
                method.invoke(dbClass, context, jsonObjects);
              //  GswDB.saveCountries(context, jsonObjects);

                PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(jsonScriptName+"_codeversion", new Integer(newJsonCodeVersion)).commit();

                return true;
            } catch (Exception e) {
                Log.e("TEST", "Fehler in Import, e=" + e);
                return false;
            }
        }else{
            return false;
        }
    }
    */


    /*
    public static void initDatabase_sql(Context context, String dbName) {
        int currentSqlCodeVersion = PreferenceManager.getDefaultSharedPreferences(context).getInt("PREF_DATABASE_CODEVERSION", 0);

        String sqlscriptName = DB_NAME+".sql";
        String newSqlCodeVersionTxt = getStringFromResource(context, sqlscriptName+".version");
        int newSqlCodeVersion = 0;
        if(newSqlCodeVersionTxt != null){
            newSqlCodeVersion = new Integer(newSqlCodeVersionTxt);
        }
        if(newSqlCodeVersion > currentSqlCodeVersion) {
            try {
                DaoSession daoSession = GreenDaoHelper.newDaoSession(context, DB_NAME);
                final Pair<Context, String> keyPair = new Pair<Context, String>(context, dbName);
                DaoMaster daoMaster = daoMasterMap.get(keyPair);
                if(daoMaster != null) {
                    // init categories table with default data
                    // get SQL Script
                    String sql = getStringFromResource(context, sqlscriptName);
                    if(sql != null) {
                        // fill content into database
                        fillDatabase(sql, daoMaster.getDatabase());

                        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("PREF_DATABASE_CODEVERSION", new Integer(newSqlCodeVersion)).commit();
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    */


    private static String getStringFromResource(Context context, String dbname) {
        InputStream inputStream;
        byte[] buffer;
        try {
            inputStream = context.getResources().getAssets().open("database/" + dbname);
            int size = inputStream.available();
            buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String text = new String(buffer);

            return text;
        } catch (IOException e) {

        }

        return null;
    }

    /*
    private static void fillDatabase(String sql, SQLiteDatabase daoDb) {
        if(sql != null && sql.length() > 0) {
            // fill content into database
            String[] queries = sql.split("\\r?\\n");
            String q;
            for(int i = 0; i < queries.length; i++) {
                q = queries[i];
                if(q != null && !q.equals("") && !q.startsWith("/*")) {
                    daoDb.execSQL(q);
                }
            }
        }
    }
    */

}
