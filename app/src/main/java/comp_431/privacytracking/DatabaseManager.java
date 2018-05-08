package comp_431.privacytracking;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import comp_431.privacytracking.database.AppDatabase;
import comp_431.privacytracking.database.company.CompanyDB;
import comp_431.privacytracking.database.forward_reference.ForwardReferenceDB;
import comp_431.privacytracking.database.meta_data.MetaDB;
import comp_431.privacytracking.database.user.UserDB;
import android.arch.persistence.room.Room;
import android.content.Context;


// TODO: Use convertors when inserting and taking info from the database. ( Long <-> Date)
public class DatabaseManager {

   //TODO: New User;

    public void UserJoinsCompany(String userID,String companyID){
        // First We get the user record.
        UserDB user = LoginActivity.db.userDAO().getUserById(userID);

        // IMPORTANT: req are the fields of user needed by the company. Otherwise the "contract" is not created;
        //Then we get both user´s share list and the company´s request list and compare then creating the final list.
        ArrayList<Boolean> usersharelist = user.getUserShareList();
        ArrayList<Boolean> metaPermisions = new ArrayList<>();
        ArrayList<Boolean> req = LoginActivity.db.CompanyDAO().getCompanyById(companyID).getCompanyRequiredFields();


        for( int i=0;i<usersharelist.size();i++) {
            if (usersharelist.get(i) && req.get(i)) {
                //If the user allows to share this info and the company requested it then you get it.
                metaPermisions.add(true);
            }
            else if(!usersharelist.get(i) && req.get(i)){
                return;
            }
            else {
                metaPermisions.add(false);
            }
        }
        //Creation of the new MetaDB row
        // Need to create a calendar to set the value for the expiration variable.
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        // Current Time
        Timestamp creation = new Timestamp(cal.getTime().getTime());
        // Now we add the expiration date  +constant months.
        cal.add(Calendar.MONTH, LoginActivity.db.expirationmonths);
        Timestamp expiration = new Timestamp(cal.getTime().getTime());
        MetaDB newRecord = new MetaDB(createUri(userID, companyID, creation));

        newRecord.setCreationTime(creation.getTime());
        newRecord.setExpirationTime(expiration.getTime());
        newRecord.setBackRefId("Origin"); // Origin of this record.
        newRecord.setRootId(newRecord.getUri());
        newRecord.setCompanyId(companyID);
        newRecord.setUserId(userID);
        newRecord.setShareList(metaPermisions);
        newRecord.setDeleted(false);

        // Inserting the new row in the database.
        LoginActivity.db.metaDAO().insert(newRecord);


        }

    public void CompanyForwardCompany(String CompanyHas, String CompanyWants){
        List<MetaDB> candidates = LoginActivity.db.metaDAO().getAllFromCompany(CompanyHas);
        ArrayList<Boolean> sharelistcandidate;
        Boolean selected;
        ArrayList<Boolean> ReqCompanyWants = LoginActivity.db.CompanyDAO().getCompanyById(CompanyWants).getCompanyRequiredFields();
        MetaDB actualCandidate;


        for(int i=0;i<candidates.size();i++){ //Lets filter the results.
            actualCandidate = candidates.get(i);
            if(actualCandidate.getDeleted()){ return;}
            sharelistcandidate = actualCandidate.getShareList();
            selected = true;
            for( int j=0;j<sharelistcandidate.size();j++) {
                if(!sharelistcandidate.get(i) && ReqCompanyWants.get(i)){
                    selected=false;
                    return;
                }
            }
            if(selected){ //Create new record in metaDB , create a new Forward of this uri.
                transferRecord(CompanyWants,actualCandidate);
            }
        }
        return;
    }

    private void transferRecord(String CompanyWants,MetaDB actualCandidate){
        //Creation of the new MetaDB row
        // Need to create a calendar to set the value for the expiration variable.
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        // Current Time
        Timestamp creation = new Timestamp(cal.getTime().getTime());
        // Now we add the expiration date  +constant months.
        cal.add(Calendar.MONTH, LoginActivity.db.expirationmonths);
        Timestamp expiration = new Timestamp(cal.getTime().getTime());
        MetaDB newRecord = new MetaDB(createUri(actualCandidate.getUserId(),CompanyWants, creation));


        newRecord.setCreationTime(creation.getTime());
        newRecord.setExpirationTime(expiration.getTime());
        newRecord.setBackRefId(actualCandidate.getUri()); //Previous Record.
        newRecord.setRootId(actualCandidate.getRootId()); //First Record Created.
        newRecord.setCompanyId(CompanyWants);
        newRecord.setUserId(actualCandidate.getUserId());
        newRecord.setShareList(actualCandidate.getCopyOfShareList());
        newRecord.setDeleted(false);

        // Inserting the new row in the database.
        LoginActivity.db.metaDAO().insert(newRecord);

        //Now we have to create a new FowardReference.
        ForwardReferenceDB newForward = new ForwardReferenceDB();
        newForward.setUri(actualCandidate.getUri());
        newForward.setNewRecordUri(newRecord.getUri());
        newForward.setForwardReferenceID(CompanyWants);
        newForward.setSourceReferenceId(actualCandidate.getCompanyId());

        LoginActivity.db.ForwardReferenceDAO().insert(newForward);
    }

    public List<String> getListOfUsersIDsOutOfAllContracts(String CompanyID){
        List<String> CompanyUsers = LoginActivity.db.metaDAO().ListAllUsers(CompanyID);
        List<String> ListUsersWithoutRepetitions = new ArrayList<String>();

        String actualUser;
        Boolean actuallyInList;
        for(int j = 0; j < CompanyUsers.size();j++) {
            actuallyInList = false;
            actualUser = CompanyUsers.get(j);
            for (int i = 0; i < ListUsersWithoutRepetitions.size(); i++) {
                if (ListUsersWithoutRepetitions.get(i) == actualUser) {
                    actuallyInList = true;
                    break;
                }
                if (!actuallyInList) {
                    ListUsersWithoutRepetitions.add(actualUser);
                }
            }
        }
        return ListUsersWithoutRepetitions;
    }

    private String createUri(String userID,String companyID, Timestamp creation){
        String uri = "";
        uri += userID;
        uri += companyID;
        uri += creation.toString();

        return uri;
    }

    public List<MetaDB> userOriginalContracts(String userID){

        List<MetaDB> test = LoginActivity.db.metaDAO().OriginalRecordsFromUser(userID, "Origin");

        return test;
    }

    public List<CompanyDB> companiesUserHasNoContractWith(){
        List<String> allCompaniesIds = LoginActivity.db.CompanyDAO().getAllIds();
        List<MetaDB> userOrigContracts = userOriginalContracts(LoginActivity.currentUser.getUid());
        MetaDB actual;
        List<CompanyDB> allCompanies = new ArrayList<>();
        for(int i=0;i<userOrigContracts.size();i++){
            actual = userOrigContracts.get(i);
            if(allCompaniesIds.contains(actual.getCompanyId())){
                allCompaniesIds.remove(actual.getCompanyId());
            }
        }
        for(int j=0;j<allCompaniesIds.size();j++){
            allCompanies.add(LoginActivity.db.CompanyDAO().getCompanyById(allCompaniesIds.get(j)));

        }
        return allCompanies;
    }

    public List<MetaDB> companyOriginalContracts(String companyId){

        List<MetaDB> test = LoginActivity.db.metaDAO().OriginalRecordsFromCompany(companyId, "Origin");

        return test;
    }
}
