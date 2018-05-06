package comp_431.privacytracking.company;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import comp_431.privacytracking.R;

public class CompanyMainActivity extends AppCompatActivity {

    @BindView(R.id.btn_users_list)
    Button btnUsersList;
    @BindView(R.id.btn_company_data)
    Button btnCompanyData;
    @BindView(R.id.btn_original_records)
    Button btnOriginalRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_main);
    }

    @OnClick({R.id.btn_users_list, R.id.btn_company_data, R.id.btn_original_records})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_users_list:
                startActivity(new Intent(this, UserListActivity.class));
                break;
            case R.id.btn_company_data:
                // TODO start intent
                break;
            case R.id.btn_original_records:
                // TODO start intent
                break;
        }
    }
}
