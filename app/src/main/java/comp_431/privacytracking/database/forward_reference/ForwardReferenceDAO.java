package comp_431.privacytracking.database.forward_reference;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.Insert;

import java.util.List;

@Dao
public interface ForwardReferenceDAO {

    @Query("SELECT * FROM ForwardReferenceDB")
    public List<ForwardReferenceDB> getAll();
    
    @Query("SELECT newRecordUri FROM ForwardReferenceDB WHERE uri = :uri")
    public List<String> ForwardedRecordsUri(String uri);

    @Insert
    public void insert(ForwardReferenceDB forwardDB);
}