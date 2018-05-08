package comp_431.privacytracking.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comp_431.privacytracking.R;

public class UserMainActivity extends AppCompatActivity {

    @BindView(R.id.btn_companies_list)
    Button btnCompaniesList;
    @BindView(R.id.btn_user_data)
    Button btnUserData;
    @BindView(R.id.btn_track_data)
    Button btnTrackData;
    @BindView(R.id.btn_modify_contracts)
    Button btnModifyContracts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_companies_list, R.id.btn_user_data, R.id.btn_track_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_companies_list:
                startActivity(new Intent(this, CompanyListActivity.class));
                break;
            case R.id.btn_user_data:
                startActivity(new Intent(this, UserDataActivity.class));
                break;
            case R.id.btn_track_data:
                startActivity(new Intent(this, TrackDataActivity.class));
                break;
            case R.id.btn_modify_contracts:
                //startActivity(new Intent(this, ModifyContractsActivity.class));
                break;
        }
    }
}
