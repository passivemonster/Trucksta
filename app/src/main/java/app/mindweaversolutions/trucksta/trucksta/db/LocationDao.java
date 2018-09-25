package app.mindweaversolutions.trucksta.trucksta.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("SELECT * from locationData")
    List<locationData> getAll();

    @Insert
    void insertAll(locationData... locationData);

}
