package com.peggy.happyvienna.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.peggy.happyvienna.data.dao.InventoryDao;
import com.peggy.happyvienna.data.dao.MedicalRecordDao;
import com.peggy.happyvienna.data.entity.InventoryItem;
import com.peggy.happyvienna.data.entity.MedicalRecord;

@Database(entities = {InventoryItem.class, MedicalRecord.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract InventoryDao inventoryDao();
    public abstract MedicalRecordDao medicalRecordDao();
}