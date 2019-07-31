package com.example.go_workingspace.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class Contract {
    private Contract() {

    }

    public static final String CONTENT_AUTHOITY = "com.example.android.go_working_space";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHOITY);
    public static final Uri BASE_ID_URI = Uri.parse("content://" + CONTENT_AUTHOITY + "/#");

    public static final class Entry implements BaseColumns{
        public static final String USER_TABLE_NAME = "userAccounts";
        public static final String OWNER_TABLE_NAME = "ownerAccounts";
        public static final String HISTORY_TABLE_NAME = "history";

        public static final String CURRENT_TABLE_NAME = "history";

        public static final String _ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_BIRTHDAY = "birthday";
        public static final String COLUMN_PHONE = "phone";

        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_RATING_COUNTER = "ratingCounter";

        public static final String COLUMN_PLACE_ID = "place";
        public static final String COLUMN_USER_ID = "user id";
        public static final String COLUMN_DATE = "date";


        public static final String COLUMN_WIFI = "wifi";
        public static final String COLUMN_SHARED = "shared";
        public static final String COLUMN_MEETING_ROOMS = "meeting";
        public static final String COLUMN_DRINKS = "drinks";
        public static final String COLUMN_OUTDOOR = "outdoor";

        public static final String COLUMN_CURRENT_USER = "userId";
        public static final String COLUMN_CURRENT_OWNER = "ownerId";

        public static long currentId = -1;
        public static int currentCwsId = -1;

        public static final Uri USER_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, USER_TABLE_NAME);
        public static final Uri USER_ID_URI = Uri.withAppendedPath(BASE_ID_URI, USER_TABLE_NAME);

        public static final Uri OWNER_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, OWNER_TABLE_NAME);
        public static final Uri OWNER_ID_URI = Uri.withAppendedPath(BASE_ID_URI, OWNER_TABLE_NAME);

        public static final Uri HISTORY_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, HISTORY_TABLE_NAME);
        public static final Uri HISTORY_ID_URI = Uri.withAppendedPath(BASE_ID_URI, HISTORY_TABLE_NAME);

        public static final String CONTENT_LIST_USER =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHOITY + "/" + USER_TABLE_NAME;

        public static final String CONTENT_ITEM_USER =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHOITY + "/" + USER_TABLE_NAME;

        public static final String CONTENT_LIST_OWNER =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHOITY + "/" + OWNER_TABLE_NAME;

        public static final String CONTENT_ITEM_OWNER =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHOITY + "/" + OWNER_TABLE_NAME;

        public static final String CONTENT_LIST_HISTORY =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHOITY + "/" + HISTORY_TABLE_NAME;

        public static final String CONTENT_ITEM_HISTORY =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHOITY + "/" + HISTORY_TABLE_NAME;
    }
}
