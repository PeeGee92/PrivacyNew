package comp_431.privacytracking.database.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM userdb")
    public List<UserDB> getAll();

    @Query("SELECT * FROM userdb WHERE userId = :userIdInput")
    public UserDB getUserById(String userIdInput);

    /*
    @Query ("SELECT userId FROM userdb WHERE user_email = :emailInput and user_password = :passwordInput")
    public UserDB Authenticacion(String emailInput, String passwordInput);
    */

    @Update
    public void update(UserDB userDB);

    @Insert
    public void insert(UserDB userDB);

}
