package comp_431.privacytracking.company;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.company.adapters.CompanyUserListAdapter;

public class UserListActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter;

    @BindView(R.id.userList)
    RecyclerView userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);

        userList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CompanyUserListAdapter(getUserList());
        userList.setAdapter(adapter);
    }

    private List<String> getUserList() {
        return LoginActivity.dbmanag.getListOfUsersIDsOutOfAllContracts(LoginActivity.currentUser.getUid());
    }

    @OnClick({R.id.userList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
        }
    }
}
