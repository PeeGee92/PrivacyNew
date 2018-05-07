package comp_431.privacytracking.database.company;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.Insert;

import java.util.List;

@Dao
public interface CompanyDAO {

    @Query("SELECT * FROM companydb")
    public List<CompanyDB> getAll();

    @Query("SELECT CompanyId FROM companydb")
    public List<String> getAllIds();

    @Query("SELECT * FROM companydb WHERE companyId = :companyId")
    public CompanyDB getCompanyById(String companyId);

    @Query("SELECT companyId FROM companydb WHERE company_name = :companyName")
    public String getCompanyIdByName(String companyName);

    @Query("SELECT companyId FROM companydb WHERE companyId <> :companyID")
    public List<String> getAllExceptMe(String companyID);

    @Update
    public void update(CompanyDB CompanyDB);

    @Insert
    public void insert(CompanyDB CompanyDB);
}
