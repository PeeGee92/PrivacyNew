package comp_431.privacytracking.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import comp_431.privacytracking.database.company.CompanyDAO;
import comp_431.privacytracking.database.company.CompanyDB;
import comp_431.privacytracking.database.forward_reference.ForwardReferenceDAO;
import comp_431.privacytracking.database.forward_reference.ForwardReferenceDB;
import comp_431.privacytracking.database.meta_data.MetaDAO;
import comp_431.privacytracking.database.meta_data.MetaDB;
import comp_431.privacytracking.database.user.UserDAO;
import comp_431.privacytracking.database.user.UserDB;

@Database(entities = {UserDB.class, MetaDB.class, CompanyDB.class , ForwardReferenceDB.class}, version = 100)
@TypeConverters({DateTypeConvertors.class,AListBooleansTypeConvertors.class})
public abstract class AppDatabase extends RoomDatabase{
    public abstract UserDAO userDAO();
    public abstract MetaDAO metaDAO();
    public abstract CompanyDAO CompanyDAO();
    public abstract ForwardReferenceDAO ForwardReferenceDAO();

    public int expirationmonths = 6;
}
