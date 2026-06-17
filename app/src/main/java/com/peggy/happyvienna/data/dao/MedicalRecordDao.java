package com.peggy.happyvienna.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.peggy.happyvienna.data.entity.MedicalRecord;

@Dao
public interface MedicalRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(MedicalRecord record);
}