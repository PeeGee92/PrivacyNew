package comp_431.privacytracking.company;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.company.adapters.CompanyUserListAdapter;
import comp_431.privacytracking.database.company.CompanyDB;
import comp_431.privacytracking.database.user.UserDB;

public class UserListActivity extends AppCompatActivity {

    @BindView(R.id.userList)
    RecyclerView userList;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        userList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CompanyUserListAdapter(getUserList());
        userList.setAdapter(adapter);
    }

    private List<String> getUserList() {
        return LoginActivity.dbmanag.getListOfUsersIDsOutOfAllContracts(LoginActivity.currentUser.toString());
    }

    @OnClick({R.id.userList})
    public void onViewClicked(View view){
        switch (view.getId()){
        }
    }
}
