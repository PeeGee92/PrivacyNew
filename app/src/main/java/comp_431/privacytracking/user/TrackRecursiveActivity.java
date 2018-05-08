package comp_431.privacytracking.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.database.meta_data.MetaDB;
import comp_431.privacytracking.user.adapter.TrackRecursiveActivityAdapter;

public class TrackRecursiveActivity extends AppCompatActivity {


    RecyclerView.Adapter adapter;
    @BindView(R.id.rw)
    RecyclerView rw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_recursive);
        ButterKnife.bind(this);

        rw.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrackRecursiveActivityAdapter(getRecords());
        rw.setAdapter(adapter);
    }

    private List<MetaDB> getRecords() {
        String backward_recordId = getIntent().getStringExtra("Record");
        List<String> forwardedRecordsUri = LoginActivity.db.ForwardReferenceDAO().ForwardedRecordsUri(backward_recordId);
        List<MetaDB> forwRecords = new ArrayList<>();
        for (int i = 0; i < forwardedRecordsUri.size(); i++) {
            forwRecords.add(LoginActivity.db.metaDAO().getFromUri(forwardedRecordsUri.get(i)));
        }
        return forwRecords;
    }
}
