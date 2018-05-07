package comp_431.privacytracking.company;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.company.adapters.CompanyUserListAdapter;
import comp_431.privacytracking.company.adapters.UserContractsAdapter;
import comp_431.privacytracking.database.meta_data.MetaDB;
import comp_431.privacytracking.user.UserEnum;

public class UserContractsActivity extends AppCompatActivity {


    @BindView(R.id.ContractsOfUser)
    RecyclerView userContracts;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contracts);
        userContracts.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserContractsAdapter(getUserContractList());
        userContracts.setAdapter(adapter);
    }

    // Make a list with all this user´s contracts.
    private List<String> getUserContractList(){
        List<MetaDB> contracts = LoginActivity.db.metaDAO().UserContractsInCompany(LoginActivity.currentUser.toString(),getIntent().getStringExtra("Id"));
        List<String> contractsList = new ArrayList<String>();
        MetaDB actual;
        for(int i =0;i<contracts.size();i++) {
            actual= contracts.get(i);
            String sharelist = shareListTotString(actual.getShareList());
            contractsList.add(
                    "URI".concat(actual.getUri()).concat("\n")
                    .concat("User Id").concat(actual.getUserId()).concat("\n")
                    .concat("BackWard Ref Id ").concat(actual.getBackRefId()).concat("\n")
                    .concat("Company Id ").concat(actual.getCompanyId()).concat("\n")
                    .concat("Root ID ").concat(actual.getRootId()).concat("\n")
                    .concat("Creation Time ").concat(actual.getCreationTime().toString()).concat("\n")
                    .concat("Expiration Time ").concat(actual.getExpirationTime().toString()).concat("\n")
                    .concat("Deleted ").concat(actual.getDeleted().toString()).concat("\n")
                    .concat("Deleted").concat(sharelist).concat("\n"));
        }
        return contractsList;
    }

    private String shareListTotString(ArrayList<Boolean> shareList){
        String result ="Share List: \n";
        UserEnum index = new UserEnum();
        for(int i=0;i<shareList.size();i++){
            if(shareList.get(i)){
                result.concat(index.returnFiel(i)).concat( ": True").concat("\n");
            }
            else{
                result.concat(index.returnFiel(i)).concat( ": False").concat("\n");
            }
        }
        return result;
    }

    @OnClick({R.id.userList})
    public void onViewClicked(View view){
        switch (view.getId()){
        }
    }

}
