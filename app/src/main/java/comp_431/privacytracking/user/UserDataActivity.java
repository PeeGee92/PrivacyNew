package comp_431.privacytracking.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.database.user.UserDB;

public class UserDataActivity extends AppCompatActivity {

    @BindView(R.id.btnSave)
    Button btnSave;
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
    @BindView(R.id.etEmail)
    EditText etEmail;
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
        //Object test = new UserDB.listIndex;

        etAddress.setText(userDB.getUserEmail());
        etCity.setText(userDB.getUserCity());
        etCountry.setText(userDB.getUserCountry());
        etZipCode.setText(userDB.getUserZip());
        etFirstName.setText(userDB.getUserFirstName());
        etLastName.setText(userDB.getUserLastName());
        etEmail.setText(userDB.getUserEmail());

        //swEmail.setChecked((userDB.getUserShareList().get(UserDB.listIndex.userEmail) == 1) ? true : false );
    }
}
