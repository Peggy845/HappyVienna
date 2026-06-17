package com.peggy.happyvienna.data.model;

public class TempBPRecord {
    public String time;       // 格式: HH:mm
    public String value;      // 格式: 120/80/70
    public boolean isSelected;

    public TempBPRecord(String time, String value) {
        this.time = time;
        this.value = value;
        this.isSelected = false;
    }
}