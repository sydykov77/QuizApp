package com.example.quizapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizapp.models.HistoryModel;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    long insert(HistoryModel historyModel);



    @Query("SELECT*FROM historymodel WHERE id=:id")
    HistoryModel getById(int id);

//    @Query("SELECT*FROM historymodel")
//    HistoryModel getByDifficulty(String difficulty);

    @Delete
    void delete(HistoryModel historyModel);

    @Query("DELETE FROM historymodel")
    void deleteAll();


   @Query("SELECT*FROM historymodel")
   LiveData<List<HistoryModel>> getAll();
}
