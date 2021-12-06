package com.example.eventbrite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface PersoanaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Persoana... persoana);

    @Delete
    void delete(Persoana persoana);

    @Query("Select * from TabelaEventbrite")
    List<Persoana> getAll();

}
