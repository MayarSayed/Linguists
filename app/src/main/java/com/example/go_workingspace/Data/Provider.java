package com.example.go_workingspace.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Provider extends ContentProvider {

    private static final int USER = 100;
    private static final int USER_ID = 101;
    private static final int OWNER = 102;
    private static final int OWNER_ID = 103;
    private static final int HISTORY = 104;
    private static final int HISTORY_ID = 105;

    private DbHelper mDbHelper;

    public static final String LOG_TAG = Provider.class.getSimpleName();
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(Contract.CONTENT_AUTHOITY, Contract.Entry.USER_TABLE_NAME, USER);
        sUriMatcher.addURI(Contract.CONTENT_AUTHOITY, Contract.Entry.USER_TABLE_NAME + "/#", USER_ID);

        sUriMatcher.addURI(Contract.CONTENT_AUTHOITY, Contract.Entry.OWNER_TABLE_NAME, OWNER);
        sUriMatcher.addURI(Contract.CONTENT_AUTHOITY, Contract.Entry.OWNER_TABLE_NAME + "/#", OWNER_ID);

        sUriMatcher.addURI(Contract.CONTENT_AUTHOITY, Contract.Entry.HISTORY_TABLE_NAME, HISTORY);
        sUriMatcher.addURI(Contract.CONTENT_AUTHOITY, Contract.Entry.HISTORY_TABLE_NAME + "/#", HISTORY_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match){
            case USER:
                cursor = database.query(Contract.Entry.USER_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case USER_ID:
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(Contract.Entry.USER_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case OWNER:
                cursor = database.query(Contract.Entry.OWNER_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case OWNER_ID:
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(Contract.Entry.OWNER_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case HISTORY:
                cursor = database.query(Contract.Entry.HISTORY_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case HISTORY_ID:
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(Contract.Entry.HISTORY_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USER:
                return Contract.Entry.CONTENT_LIST_USER;
            case USER_ID:
                return Contract.Entry.CONTENT_ITEM_USER;
            case OWNER:
                return Contract.Entry.CONTENT_LIST_OWNER;
            case OWNER_ID:
                return Contract.Entry.CONTENT_ITEM_OWNER;
            case HISTORY:
                return Contract.Entry.CONTENT_LIST_HISTORY;
            case HISTORY_ID:
                return Contract.Entry.CONTENT_ITEM_HISTORY;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USER:
                return insertUser(uri, contentValues);
            case OWNER:
                return insertOwner(uri, contentValues);
            case HISTORY:
                return insertHistory(uri, contentValues);
            default:
                Log.e(LOG_TAG, "Insertion is not supported for " + uri);
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USER:
                // Delete all rows that match the selection and selection args
                return database.delete(Contract.Entry.USER_TABLE_NAME, selection, selectionArgs);
            case USER_ID:
                // Delete a single row given by the ID in the URI
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return database.delete(Contract.Entry.USER_TABLE_NAME, selection, selectionArgs);
            case OWNER:
                // Delete all rows that match the selection and selection args
                return database.delete(Contract.Entry.OWNER_TABLE_NAME, selection, selectionArgs);
            case OWNER_ID:
                // Delete a single row given by the ID in the URI
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return database.delete(Contract.Entry.OWNER_TABLE_NAME, selection, selectionArgs);
            case HISTORY:
                // Delete all rows that match the selection and selection args
                return database.delete(Contract.Entry.HISTORY_TABLE_NAME, selection, selectionArgs);
            case HISTORY_ID:
                // Delete a single row given by the ID in the URI
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return database.delete(Contract.Entry.HISTORY_TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case USER:
                return updateUser(uri, contentValues, selection, selectionArgs, Contract.Entry.USER_TABLE_NAME);
            case USER_ID:
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateUser(uri, contentValues, selection, selectionArgs, Contract.Entry.USER_TABLE_NAME);
            case OWNER:
                return updateUser(uri, contentValues, selection, selectionArgs, Contract.Entry.OWNER_TABLE_NAME);
            case OWNER_ID:
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateUser(uri, contentValues, selection, selectionArgs, Contract.Entry.OWNER_TABLE_NAME);
            case HISTORY:
                updateHistory(uri, contentValues, selection, selectionArgs);
            case HISTORY_ID:
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateHistory(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    /**
     * Insert a pet into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertUser(Uri uri, ContentValues values) {
        String name = values.getAsString(Contract.Entry.COLUMN_NAME);
        if(name == null){
            throw new IllegalArgumentException("User requires a name");
        }

        String username = values.getAsString(Contract.Entry.COLUMN_USERNAME);
        if(username == null){
            throw new IllegalArgumentException("User requires a username");
        }

        String email = values.getAsString(Contract.Entry.COLUMN_EMAIL);
        if(email == null){
            throw new IllegalArgumentException("User requires an email");
        }

        String password = values.getAsString(Contract.Entry.COLUMN_PASSWORD);
        if(password == null){
            throw new IllegalArgumentException("User requires a password");
        }

        String birthday = values.getAsString(Contract.Entry.COLUMN_BIRTHDAY);
        if(birthday == null){
            throw new IllegalArgumentException("User requires a birthday");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(Contract.Entry.USER_TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertOwner(Uri uri, ContentValues values) {
        String name = values.getAsString(Contract.Entry.COLUMN_NAME);
        if(name == null){
            throw new IllegalArgumentException("Owner requires a name");
        }

        String username = values.getAsString(Contract.Entry.COLUMN_USERNAME);
        if(username == null){
            throw new IllegalArgumentException("Owner requires a username");
        }

        String email = values.getAsString(Contract.Entry.COLUMN_USERNAME);
        if(email == null){
            throw new IllegalArgumentException("Owner requires an email");
        }

        String password = values.getAsString(Contract.Entry.COLUMN_PASSWORD);
        if(password == null){
            throw new IllegalArgumentException("Owner requires a password");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(Contract.Entry.OWNER_TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertHistory(Uri uri, ContentValues values) {
        String date = values.getAsString(Contract.Entry.COLUMN_DATE);
        if(date == null){
            throw new IllegalArgumentException("History requires a date");
        }

        Integer userId = values.getAsInteger(Contract.Entry.COLUMN_USER_ID);
        if(userId == null || userId < 1){
            throw new IllegalArgumentException("History requires a valid user id");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(Contract.Entry.OWNER_TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    private int updateUser(Uri uri, ContentValues values, String selection, String[] selectionArgs, String tableName){
        if(values.containsKey(Contract.Entry.COLUMN_NAME)){
            String name = values.getAsString(Contract.Entry.COLUMN_NAME);
            if(name == null){
                throw new IllegalArgumentException("Requires valid name");
            }
        }

        if(values.containsKey(Contract.Entry.COLUMN_EMAIL)){
            String name = values.getAsString(Contract.Entry.COLUMN_EMAIL);
            if(name == null){
                throw new IllegalArgumentException("Requires valid email");
            }
        }

        if(values.containsKey(Contract.Entry.COLUMN_USERNAME)){
            String name = values.getAsString(Contract.Entry.COLUMN_USERNAME);
            if(name == null){
                throw new IllegalArgumentException("Requires valid username");
            }
        }

        if(values.containsKey(Contract.Entry.COLUMN_BIRTHDAY)){
            String name = values.getAsString(Contract.Entry.COLUMN_BIRTHDAY);
            if(name == null){
                throw new IllegalArgumentException("Requires valid birthday");
            }
        }

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        return database.update(tableName, values, selection, selectionArgs);

    }

    private int updateHistory(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        return 0;
    }
}
