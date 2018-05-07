package comp_431.privacytracking.user;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.database.company.CompanyDB;
import comp_431.privacytracking.user.adapter.UserCompanyListAdapter;

public class CompanyListActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    @BindView(R.id.rvCompanies)
    RecyclerView rvCompanies;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        ButterKnife.bind(this);

        // Recycler View
        rvCompanies.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserCompanyListAdapter(getCompaniesList());
        rvCompanies.setAdapter(adapter);
    }

    // TODO
    private List<String> getCompaniesList() {
       List<CompanyDB> companies =  LoginActivity.db.CompanyDAO().getAll();
       List<String> string = new ArrayList<>();
       return string;
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        // TODO Add contract activity
    }
}
