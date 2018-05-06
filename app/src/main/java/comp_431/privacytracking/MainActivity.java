package comp_431.privacytracking;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import comp_431.privacytracking.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    //public static AppDatabase db;
    public static DatabaseManager dbmanag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        // Database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "privacy_tracker")
                .allowMainThreadQueries()
                .build();
                */
//        dbmanag =  new DatabaseManager(getApplicationContext());
    }
}
