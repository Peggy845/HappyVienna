package com.peggy.happyvienna.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.peggy.happyvienna.data.entity.InventoryItem;

@Dao
public interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(InventoryItem item);
}