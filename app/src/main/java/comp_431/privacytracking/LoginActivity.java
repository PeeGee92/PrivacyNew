package comp_431.privacytracking;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comp_431.privacytracking.company.CompanyMainActivity;
import comp_431.privacytracking.database.AppDatabase;
import comp_431.privacytracking.database.company.CompanyDB;
import comp_431.privacytracking.database.user.UserDB;
import comp_431.privacytracking.user.UserMainActivity;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_email)
    AutoCompleteTextView etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_sign_in)
    Button btnSignIn;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.rbCompany)
    RadioButton rbCompany;
    @BindView(R.id.rbIndividual)
    RadioButton rbIndividual;

    // Database
    public static AppDatabase db;

    // Firebase Authentication
    private static FirebaseAuth mAuth;
    public static FirebaseUser currentUser;
    public static DatabaseManager dbmanag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "privacy_tracker")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        rbIndividual.setChecked(true);
        dbmanag =  new DatabaseManager();
    }

    @OnClick({R.id.btn_sign_in, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                checkInput(true);
                break;
            case R.id.btn_register:
                checkInput(false);
                break;
        }
    }

    private void checkInput(boolean signin) {
        if (!isEmailValid(etEmail.getText().toString().trim())) {
            Toast.makeText(LoginActivity.this, "Please enter a valid email address",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (etPassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter a password",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!signin) {
            if (etPassword.getText().toString().trim().length() < 6) {
                Toast.makeText(LoginActivity.this, "Password is too short",
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Register or Sign-in
        if (signin) {
            signInUser();
        } else {
            registerUser();
        }
    }

    private void signInUser() {

        mAuth.signInWithEmailAndPassword(etEmail.getText().toString().trim(), etPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            boolean correctType = checkUserType();

                            if (!correctType) {
                                Toast.makeText(LoginActivity.this, "Wrong user type selected",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }

                            currentUser = mAuth.getCurrentUser();
                            userWithDB(false);
                        } else {
                            Toast.makeText(LoginActivity.this, "Sign-in failed! Please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean checkUserType() {
        FirebaseUser tempUser = mAuth.getCurrentUser();

        if (rbCompany.isChecked()) {
            CompanyDB tempCompanyDB = db.CompanyDAO().getCompanyById(tempUser.getUid());
            if (tempCompanyDB == null)
                return false;
            else
                return true;
        }
        else {
            UserDB tempUserDB = db.userDAO().getUserById(tempUser.getUid());
            if (tempUserDB == null)
                return false;
            else
                return true;
        }

    }

    private void registerUser() {

        mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            currentUser = mAuth.getCurrentUser();
                            userWithDB(true);
                        } else {
                            Toast.makeText(LoginActivity.this, "Registration failed! Please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void userWithDB(boolean newUser) {
        boolean company;
        company = rbCompany.isChecked();

        if (newUser) {
            if (company) {
                db.CompanyDAO().insert(new CompanyDB(currentUser.getUid()));
            }
            else {
                db.userDAO().insert(new UserDB(currentUser.getUid(), currentUser.getEmail()));
            }
        }

        if (company) {
            startActivity(new Intent(this, CompanyMainActivity.class));
        } else {
            startActivity(new Intent(this, UserMainActivity.class));
        }
    }

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

}

