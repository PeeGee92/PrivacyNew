package comp_431.privacytracking.user;

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
import comp_431.privacytracking.database.user.UserDB;

public class UserDataActivity extends AppCompatActivity {

    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etCountry)
    EditText etCountry;
    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.etZipCode)
    EditText etZipCode;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.swFirstName)
    Switch swFirstName;
    @BindView(R.id.swLastName)
    Switch swLastName;
    @BindView(R.id.swEmail)
    Switch swEmail;
    @BindView(R.id.swZipCode)
    Switch swZipCode;
    @BindView(R.id.swAddress)
    Switch swAddress;
    @BindView(R.id.swCity)
    Switch swCity;
    @BindView(R.id.swCountry)
    Switch swCountry;

    UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        ButterKnife.bind(this);

        // Get user data
        userDB = LoginActivity.db.userDAO().getUserById(LoginActivity.currentUser.getUid());

        setDataToFields();
    }

    private void setDataToFields() {

        etAddress.setText(userDB.getUserEmail());
        etCity.setText(userDB.getUserCity());
        etCountry.setText(userDB.getUserCountry());
        etZipCode.setText(userDB.getUserZip());
        etFirstName.setText(userDB.getUserFirstName());
        etLastName.setText(userDB.getUserLastName());
        tvEmail.setText(userDB.getUserEmail());

        swEmail.setChecked(userDB.getUserShareList().get(new UserEnum().returnValue("userEmail")));
        swFirstName.setChecked(userDB.getUserShareList().get(new UserEnum().returnValue("userFirstName")));
        swLastName.setChecked(userDB.getUserShareList().get(new UserEnum().returnValue("userLastName")));
        swAddress.setChecked(userDB.getUserShareList().get(new UserEnum().returnValue("userAddress")));
        swCountry.setChecked(userDB.getUserShareList().get(new UserEnum().returnValue("userCountry")));
        swCity.setChecked(userDB.getUserShareList().get(new UserEnum().returnValue("userCity")));
        swZipCode.setChecked(userDB.getUserShareList().get(new UserEnum().returnValue("userZip")));

    }

    @OnClick({R.id.btnSave, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                updateUserData();
                break;
            case R.id.btnCancel:
                startActivity(new Intent(this, UserMainActivity.class));
                break;
        }
    }

    private void updateUserData() {

        userDB.setUserCity(etCity.getText().toString().trim());
        userDB.setUserCountry(etCountry.getText().toString().trim());
        userDB.setUserAddress(etAddress.getText().toString().trim());
        userDB.setUserFirstName(etFirstName.getText().toString().trim());
        userDB.setUserLastName(etLastName.getText().toString().trim());
        userDB.setUserZip(etZipCode.getText().toString().trim());

        userDB.getUserShareList().set(new UserEnum().returnValue("userEmail"), swEmail.isChecked());
        userDB.getUserShareList().set(new UserEnum().returnValue("userFirstName"), swFirstName.isChecked());
        userDB.getUserShareList().set(new UserEnum().returnValue("userLastName"), swLastName.isChecked());
        userDB.getUserShareList().set(new UserEnum().returnValue("userAddress"), swAddress.isChecked());
        userDB.getUserShareList().set(new UserEnum().returnValue("userCountry"), swCountry.isChecked());
        userDB.getUserShareList().set(new UserEnum().returnValue("userCity"), swCity.isChecked());
        userDB.getUserShareList().set(new UserEnum().returnValue("userZip"), swZipCode.isChecked());

        LoginActivity.db.userDAO().update(userDB);

        startActivity(new Intent(this, UserMainActivity.class));

    }
}
