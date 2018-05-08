package comp_431.privacytracking.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.database.company.CompanyDB;
import comp_431.privacytracking.database.meta_data.MetaDB;
import comp_431.privacytracking.user.adapter.ModifyContractsActivityAdapter;
import comp_431.privacytracking.user.adapter.UserCompanyListAdapter;

public class ModifyContractsActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    @BindView(R.id.rvListOfUserContracts)
    RecyclerView rvListOfUserContracts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_contracts);
        ButterKnife.bind(this);

        // Recycler View
        rvListOfUserContracts.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ModifyContractsActivityAdapter(getUserContracts());
        rvListOfUserContracts.setAdapter(adapter);
    }

    private List<MetaDB> getUserContracts() {
        return LoginActivity.db.metaDAO().getAllUserContracts(LoginActivity.currentUser.getUid());
    }
}
