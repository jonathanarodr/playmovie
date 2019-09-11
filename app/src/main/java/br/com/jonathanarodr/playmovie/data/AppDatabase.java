package br.com.jonathanarodr.playmovie.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import br.com.jonathanarodr.playmovie.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "playmovie";
    private static final Object LOCK = new Object();
    private static AppDatabase mInstance;

    public static AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Criando uma nova instância do banco de dados...");

                mInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME)
                        .build();
            }
        }

        Log.d(TAG, "Carregando instância do banco de dados...");

        return mInstance;
    }

    public abstract MovieDao movieDao();

}
