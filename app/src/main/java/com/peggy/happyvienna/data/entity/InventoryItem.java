package com.peggy.happyvienna.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "INVENTORY_ITEM", indices = {@Index(value = {"SYNC_ID"}, unique = true)})
public class InventoryItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "LOCAL_ID")
    private Long localId;

    @ColumnInfo(name = "SYNC_ID")
    private String syncId;

    @NonNull
    @ColumnInfo(name = "ITEM_NAME")
    private String itemName;

    @ColumnInfo(name = "NFC_TAG_ID")
    private String nfcTagId;

    @ColumnInfo(name = "CURRENT_STOCK")
    private int currentStock;

    @ColumnInfo(name = "DAILY_CONSUMPTION")
    private int dailyConsumption;

    @NonNull
    @ColumnInfo(name = "VISIBILITY")
    private String visibility;

    @NonNull
    @ColumnInfo(name = "OWNER_ID")
    private String ownerId;

    @ColumnInfo(name = "LAST_UPDATED_TIMESTAMP")
    private long lastUpdatedTimestamp;

    // Room requires a constructor or accessible fields.
    public InventoryItem(@NonNull String itemName, @NonNull String visibility, @NonNull String ownerId) {
        this.itemName = itemName;
        this.visibility = visibility;
        this.ownerId = ownerId;
    }

    public Long getLocalId() { return localId; }
    public void setLocalId(Long localId) { this.localId = localId; }

    public String getSyncId() { return syncId; }
    public void setSyncId(String syncId) { this.syncId = syncId; }

    @NonNull
    public String getItemName() { return itemName; }
    public void setItemName(@NonNull String itemName) { this.itemName = itemName; }

    public String getNfcTagId() { return nfcTagId; }
    public void setNfcTagId(String nfcTagId) { this.nfcTagId = nfcTagId; }

    public int getCurrentStock() { return currentStock; }
    public void setCurrentStock(int currentStock) { this.currentStock = currentStock; }

    public int getDailyConsumption() { return dailyConsumption; }
    public void setDailyConsumption(int dailyConsumption) { this.dailyConsumption = dailyConsumption; }

    @NonNull
    public String getVisibility() { return visibility; }
    public void setVisibility(@NonNull String visibility) { this.visibility = visibility; }

    @NonNull
    public String getOwnerId() { return ownerId; }
    public void setOwnerId(@NonNull String ownerId) { this.ownerId = ownerId; }

    public long getLastUpdatedTimestamp() { return lastUpdatedTimestamp; }
    public void setLastUpdatedTimestamp(long lastUpdatedTimestamp) { this.lastUpdatedTimestamp = lastUpdatedTimestamp; }
}