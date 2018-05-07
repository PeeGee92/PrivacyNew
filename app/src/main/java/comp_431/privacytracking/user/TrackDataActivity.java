package comp_431.privacytracking.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.database.meta_data.MetaDB;
import comp_431.privacytracking.user.adapter.TrackDataActivityAdapter;
import comp_431.privacytracking.user.adapter.UserCompanyListAdapter;

public class TrackDataActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    @BindView(R.id.rvOriginalRecordsTrack)
    RecyclerView rvOriginalRecordsTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_data);
        ButterKnife.bind(this);

        rvOriginalRecordsTrack.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrackDataActivityAdapter(getOriginalRecords());
        rvOriginalRecordsTrack.setAdapter(adapter);
    }
    private List<MetaDB> getOriginalRecords(){
        return LoginActivity.dbmanag.userOriginalContracts(LoginActivity.currentUser.toString());
    }
}
