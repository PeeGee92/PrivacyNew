package comp_431.privacytracking.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.database.company.CompanyDB;
import comp_431.privacytracking.user.UserEnum;

public class CompanyDataActivity extends AppCompatActivity {

    CompanyDB companyDB;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnChangeName)
    Button btnChangeName;
    @BindView(R.id.etCompanyName)
    EditText etCompanyName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.etFirstName)
    TextView etFirstName;
    @BindView(R.id.etLastName)
    TextView etLastName;
    @BindView(R.id.etAddress)
    TextView etAddress;
    @BindView(R.id.etCountry)
    TextView etCountry;
    @BindView(R.id.etCity)
    TextView etCity;
    @BindView(R.id.etZipCode)
    TextView etZipCode;
    @BindView(R.id.swEmail)
    Switch swEmail;
    @BindView(R.id.swFirstName)
    Switch swFirstName;
    @BindView(R.id.swLastName)
    Switch swLastName;
    @BindView(R.id.swAddress)
    Switch swAddress;
    @BindView(R.id.swCountry)
    Switch swCountry;
    @BindView(R.id.swCity)
    Switch swCity;
    @BindView(R.id.swZipCode)
    Switch swZipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_data);
        ButterKnife.bind(this);

        // Get company data
        companyDB = LoginActivity.db.CompanyDAO().getCompanyById(LoginActivity.currentUser.getUid());

        setDataToFields();
    }

    @OnClick({R.id.btnSave, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                updateCompanyRequestedData();
                break;
            case R.id.btnCancel:
                startActivity(new Intent(this, CompanyMainActivity.class));
                break;
        }
    }

    private void setDataToFields() {

        etCompanyName.setText(companyDB.getCompanyName());

        swEmail.setChecked(companyDB.getCompanyRequiredFields().get(new UserEnum().returnValue("userEmail")));
        swFirstName.setChecked(companyDB.getCompanyRequiredFields().get(new UserEnum().returnValue("userFirstName")));
        swLastName.setChecked(companyDB.getCompanyRequiredFields().get(new UserEnum().returnValue("userLastName")));
        swAddress.setChecked(companyDB.getCompanyRequiredFields().get(new UserEnum().returnValue("userAddress")));
        swCountry.setChecked(companyDB.getCompanyRequiredFields().get(new UserEnum().returnValue("userCountry")));
        swCity.setChecked(companyDB.getCompanyRequiredFields().get(new UserEnum().returnValue("userCity")));
        swZipCode.setChecked(companyDB.getCompanyRequiredFields().get(new UserEnum().returnValue("userZip")));

    }

    private void updateCompanyRequestedData() {

        companyDB.setCompanyName(etCompanyName.getText().toString().trim());

        companyDB.getCompanyRequiredFields().set(new UserEnum().returnValue("userEmail"), swEmail.isChecked());
        companyDB.getCompanyRequiredFields().set(new UserEnum().returnValue("userFirstName"), swFirstName.isChecked());
        companyDB.getCompanyRequiredFields().set(new UserEnum().returnValue("userLastName"), swLastName.isChecked());
        companyDB.getCompanyRequiredFields().set(new UserEnum().returnValue("userAddress"), swAddress.isChecked());
        companyDB.getCompanyRequiredFields().set(new UserEnum().returnValue("userCountry"), swCountry.isChecked());
        companyDB.getCompanyRequiredFields().set(new UserEnum().returnValue("userCity"), swCity.isChecked());
        companyDB.getCompanyRequiredFields().set(new UserEnum().returnValue("userZip"), swZipCode.isChecked());

        LoginActivity.db.CompanyDAO().update(companyDB);

        startActivity(new Intent(this, CompanyMainActivity.class));

    }
}
