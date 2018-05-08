package comp_431.privacytracking.database.meta_data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MetaDAO {

    @Query("SELECT * FROM metadb")
    public List<MetaDB> getAll();

    @Query("SELECT * FROM metadb WHERE uri = :uri")
    public MetaDB getFromUri(String uri);

    @Query("SELECT * FROM metadb WHERE CompanyID = :RequestedCompany")
    public List<MetaDB> getAllFromCompany(String RequestedCompany);

    @Insert
    public void insert(MetaDB metaDB);

    @Query("SELECT userId FROM metadb WHERE CompanyID = :Company")
    public List<String> ListAllUsers(String Company);

    @Query("SELECT * FROM metadb WHERE backward_reference = :source AND UserId = :UserId ")
    public List<MetaDB> OriginalRecordsFromUser(String UserId, String source);

    @Query("SELECT * FROM metadb WHERE UserId = :UserId ")
    public List<MetaDB> getAllUserContracts(String UserId);

    @Query("SELECT * FROM metadb WHERE backward_reference = :source AND CompanyId = :CompanyId ")
    public List<MetaDB> OriginalRecordsFromCompany(String CompanyId, String source);

    @Query("SELECT * FROM metadb WHERE CompanyId = :CompanyId AND UserId = :UserId ")
    public List<MetaDB> UserContractsInCompany(String UserId, String CompanyId);


}
