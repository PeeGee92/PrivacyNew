package comp_431.privacytracking.database.meta_data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;


import comp_431.privacytracking.database.user.UserDB;

@Entity
public class MetaDB {

    @PrimaryKey
    @NonNull
    private String uri; //TODO generate method for uri

    @ColumnInfo ( name = "CompanyId")
    private String CompanyId;

    @ColumnInfo ( name = "UserId")
    private String UserId;

    @ColumnInfo (name = "creation_time")
    private Long creationTime;

    @ColumnInfo (name = "expiration_time")
    private Long expirationTime;

    @ColumnInfo (name = "backward_reference")
    private String backRefId;

    @ColumnInfo (name = "backward_root")
    private String rootId;

    @ColumnInfo (name = "share_list")
    public ArrayList<Boolean> shareList;

    @ColumnInfo (name = "deleted")
    public Boolean deleted;





    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {this.expirationTime = expirationTime;}

    public String getBackRefId() {
        return backRefId;
    }

    public void setBackRefId(String refId) {this.backRefId = refId;}

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public String getCompanyId() {return CompanyId; }

    public void setCompanyId(String companyId) {CompanyId = companyId;}

    public String getUserId() {return UserId;}

    public void setUserId(String userId) { UserId = userId;}

    public ArrayList<Boolean> getShareList() {return shareList;}

    public void setShareList(ArrayList<Boolean> shareList) {this.shareList =shareList;}

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public ArrayList<Boolean> getCopyOfShareList(){
        ArrayList<Boolean> newList = new ArrayList<Boolean>();
        ArrayList<Boolean> actualList = getShareList();
        for(int i=0;i< actualList.size();i++){
            newList.add(actualList.get(i));
        }
        return newList;
    }
}
