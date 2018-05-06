package comp_431.privacytracking.database.company;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Timestamp;
import java.util.ArrayList;


@Entity
public class CompanyDB {

    @PrimaryKey
    @NonNull
    private String companyId;

    @ColumnInfo (name = "company_name")
    private String companyName;

    @ColumnInfo (name = "company_genesis_time")
    private Long companyGenesisTime;

    @ColumnInfo (name = "company_required_fields")
    private ArrayList<Boolean> companyRequiredFields;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyGenesisTime() {
        return companyGenesisTime;
    }

    public void setCompanyGenesisTime(Long companyGenesisTime) {
        this.companyGenesisTime = companyGenesisTime;
    }

    public ArrayList<Boolean> getCompanyRequiredFields() {
        return companyRequiredFields;
    }

    public void setCompanyRequiredFields(ArrayList<Boolean> companyRequiredFields) {
        this.companyRequiredFields = companyRequiredFields;
    }
}
