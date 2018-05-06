package comp_431.privacytracking.database.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;


@Entity // TODO add optional data (null default)
public class UserDB {

    @PrimaryKey
    @NonNull
    private String userId;

    @ColumnInfo (name = "user_email")
    private String userEmail;

    /*
    @ColumnInfo ( name ="user_password")
    private String userPassword;
    */

    @ColumnInfo (name = "user_first_name")
    private String userFirstName;

    @ColumnInfo (name = "user_last_name")
    private String userLastName;

    @ColumnInfo (name = "user_address")
    private String userAddress;

    @ColumnInfo (name = "user_city")
    private String userCity;

    @ColumnInfo (name = "user_country")
    private String userCountry;

    @ColumnInfo (name = "user_zip")
    private String userZip;

    @ColumnInfo (name = "user_type")
    private boolean companyUser;

//    @ColumnInfo (name = "user_genesis_time")
//    private Timestamp userGenesisTime;

    @ColumnInfo (name = "user_userShareList")
    private ArrayList <Boolean> userShareList;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isCompanyUser() {
        return companyUser;
    }

    public void setCompanyUser(boolean companyUser) {
        this.companyUser = companyUser;
    }

    //    public Timestamp getUserGenesisTime() {
//        return userGenesisTime;
//    }
//
//    public void setUserGenesisTime(Timestamp userGenesisTime) {
//        this.userGenesisTime = userGenesisTime;
//    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }

    public ArrayList<Boolean> getUserShareList() {
        return userShareList;
    }
    public void setUserShareList(ArrayList<Boolean> shareOrNot) {
        this.userShareList = shareOrNot;
    }

//Todo: ??
    public UserDB(@NonNull String userId, String userEmail, boolean companyUser) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.companyUser = companyUser;
    }
}
