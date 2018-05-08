package comp_431.privacytracking.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comp_431.privacytracking.R;

public class ChangeContractActivity extends AppCompatActivity {

    @BindView(R.id.swZip)
    Switch swZip;
    @BindView(R.id.swCity)
    Switch swCity;
    @BindView(R.id.swCountry)
    Switch swCountry;
    @BindView(R.id.swAddr)
    Switch swAddr;
    @BindView(R.id.swLName)
    Switch swLName;
    @BindView(R.id.swFName)
    Switch swFName;
    @BindView(R.id.swEmail)
    Switch swEmail;
    @BindView(R.id.llSwitches)
    LinearLayout llSwitches;
    @BindView(R.id.txtShareList)
    TextView txtShareList;
    @BindView(R.id.Deleted)
    TextView Deleted;
    @BindView(R.id.swDeleted)
    Switch swDeleted;
    @BindView(R.id.llDeleted)
    LinearLayout llDeleted;
    @BindView(R.id.RootID)
    TextView RootID;
    @BindView(R.id.BackRef)
    TextView BackRef;
    @BindView(R.id.ExpirationTime)
    TextView ExpirationTime;
    @BindView(R.id.CreationTime)
    TextView CreationTime;
    @BindView(R.id.UserID)
    TextView UserID;
    @BindView(R.id.CompanyID)
    TextView CompanyID;
    @BindView(R.id.URI)
    TextView URI;
    @BindView(R.id.llTopLeft)
    LinearLayout llTopLeft;
    @BindView(R.id.txtRootId)
    TextView txtRootId;
    @BindView(R.id.txtBackRef)
    TextView txtBackRef;
    @BindView(R.id.txtExpTime)
    TextView txtExpTime;
    @BindView(R.id.txtCreationTime)
    TextView txtCreationTime;
    @BindView(R.id.txtUserId)
    TextView txtUserId;
    @BindView(R.id.txtCompanyId)
    TextView txtCompanyId;
    @BindView(R.id.txtUri)
    TextView txtUri;
    @BindView(R.id.llTopRight)
    LinearLayout llTopRight;
    @BindView(R.id.Zip)
    TextView Zip;
    @BindView(R.id.City)
    TextView City;
    @BindView(R.id.Country)
    TextView Country;
    @BindView(R.id.Address)
    TextView Address;
    @BindView(R.id.LastName)
    TextView LastName;
    @BindView(R.id.FirstName)
    TextView FirstName;
    @BindView(R.id.Email)
    TextView Email;
    @BindView(R.id.llBottomLeft)
    LinearLayout llBottomLeft;
    @BindView(R.id.MainLayout)
    RelativeLayout MainLayout;
    @BindView(R.id.btnSave)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_contract);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btnSave)
    public void onViewClicked() {
    }
}
