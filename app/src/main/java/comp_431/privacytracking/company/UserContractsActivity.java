package comp_431.privacytracking.company;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;

public class UserContractsActivity extends AppCompatActivity {

    public String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contracts);

        user = LoginActivity.currentUser.getUid();
    }




}
