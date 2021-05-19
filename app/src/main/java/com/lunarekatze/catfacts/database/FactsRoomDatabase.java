package com.lunarekatze.catfacts.database;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.NonNull;

@Database(entities = {FactItem.class}, version = 2)
public abstract class FactsRoomDatabase extends RoomDatabase {

    public abstract FactsDao wordDao();

    private static FactsRoomDatabase INSTANCE;

    public static FactsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FactsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FactsRoomDatabase.class, "questions")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}