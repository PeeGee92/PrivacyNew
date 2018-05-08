package comp_431.privacytracking.company;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.company.adapters.ForwardAdapter;

public class ForwardActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter;

    @BindView(R.id.rbCompaniesList)
    RecyclerView companiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward);
        ButterKnife.bind(this);

        companiesList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ForwardAdapter(getAllCompanyList());
        companiesList.setAdapter(adapter);
    }
    private List<String> getAllCompanyList(){

        List<String> test = LoginActivity.db.CompanyDAO().getAllExceptMe(LoginActivity.currentUser.getUid());

        return test;
    }

}
