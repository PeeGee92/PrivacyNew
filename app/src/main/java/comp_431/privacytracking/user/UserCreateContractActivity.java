package comp_431.privacytracking.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import comp_431.privacytracking.R;

public class UserCreateContractActivity extends AppCompatActivity {

    private String companyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_contract);

        companyId = getIntent().getStringExtra("COMPANY_ID");
    }
}
