package comp_431.privacytracking.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comp_431.privacytracking.R;

public class CompanyMainActivity extends AppCompatActivity {

    @BindView(R.id.btn_users_list)
    Button btnUsersList;
    @BindView(R.id.btn_company_data)
    Button btnCompanyData;
    @BindView(R.id.btn_original_records)
    Button btnOriginalRecords;
    @BindView(R.id.btn_request_contracts)
    Button btnRequestContracts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_users_list, R.id.btn_company_data, R.id.btn_original_records})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_users_list:
                startActivity(new Intent(this, UserListActivity.class));
                break;
            case R.id.btn_company_data:
                //startActivity(new Intent(this, CompanyDataActivity.class));
                break;
            case R.id.btn_original_records:
                startActivity(new Intent(this, CompanyOriginalRecordsActivity.class));
                break;
            case R.id.btn_request_contracts:
                startActivity(new Intent(this, Request1Activity.class));
                break;
        }
    }
}
