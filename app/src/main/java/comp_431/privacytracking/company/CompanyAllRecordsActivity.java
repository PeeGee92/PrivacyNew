package comp_431.privacytracking.company;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.company.adapters.OriginalContractsAdapter;
import comp_431.privacytracking.database.meta_data.MetaDB;
import comp_431.privacytracking.user.UserEnum;

public class CompanyAllRecordsActivity extends AppCompatActivity {

    @BindView(R.id.OriginalContracts)
    RecyclerView originalContracts;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_original_records);
        ButterKnife.bind(this);
        originalContracts.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OriginalContractsAdapter(getContractList());
        originalContracts.setAdapter(adapter);
    }

    private List<String> getContractList(){
        List<MetaDB> contracts = LoginActivity.db.metaDAO().getAllFromCompany(LoginActivity.currentUser.getUid());
        List<String> contractsList = new ArrayList<String>();
        MetaDB actual;
        for(int i =0;i<contracts.size();i++) {
            actual= contracts.get(i);
            if (actual.getDeleted()) {continue;}
            contractsList.add(RecordToString(actual));
        }
        return contractsList;
    }


    // Make a list with all this user´s contracts.
    private String RecordToString(MetaDB record){
        String result = "";
        String sharelist = shareListTotString(record.getShareList());

        result += (record.getUri());
        result += ("\n");
        result += ("User Id");
        result += (record.getUserId());
        result += ("\n");
        result += ("BackWard Ref Id ");
        result += (record.getBackRefId());
        result += ("\n");
        result += ("Company Id ");
        result += (record.getCompanyId());
        result += ("\n");
        result += ("Root ID ");
        result += (record.getRootId());
        result += ("\n");
        result += ("Creation Time ");
        result += (LoginActivity.dbmanag.timeStampFormat.format(record.getCreationTime()));
        result += ("\n");
        result += ("Expiration Time ");
        result += (LoginActivity.dbmanag.timeStampFormat.format(record.getExpirationTime()));
        result += ("\n");
        result += ("Deleted ");
        result += (record.getDeleted().toString());
        result += ("\n");
        result += (sharelist);
        result += ("\n");

        return result;
    }

    private String shareListTotString(ArrayList<Boolean> shareList){
        String result = "";
        UserEnum index = new UserEnum();
        for(int i=0;i<shareList.size();i++){
            if(shareList.get(i)){
                result += (index.returnFiel(i));
                result += ( ": True");
                result += ("\n");
            }
            else{
                result += (index.returnFiel(i));
                result += ( ": False");
                result += ("\n");
            }
        }
        return result;
    }
}
