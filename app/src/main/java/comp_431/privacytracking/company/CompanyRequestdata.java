package comp_431.privacytracking.company;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp_431.privacytracking.R;

public class CompanyRequestdata extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_requestdata);

        btn = (Button) findViewById(R.id.button2_request);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                OpenActivity();
            }
        });
    }

    public void OpenActivity(){
        Intent intent = new Intent(this, CompanyMainActivity.class);
        startActivity(intent);
    }
}
