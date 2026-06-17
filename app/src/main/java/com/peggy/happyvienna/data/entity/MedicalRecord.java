package com.peggy.happyvienna.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "MEDICAL_RECORD", indices = {@Index(value = {"SYNC_ID"}, unique = true)})
public class MedicalRecord {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "LOCAL_ID")
    private Long localId;

    @ColumnInfo(name = "SYNC_ID")
    private String syncId;

    @NonNull
    @ColumnInfo(name = "DEPARTMENT_NAME")
    private String departmentName;

    @ColumnInfo(name = "SEARCH_TAGS")
    private String searchTags;

    @ColumnInfo(name = "DOCTOR_NOTE")
    private String doctorNote;

    @ColumnInfo(name = "REQUIRE_FASTING")
    private boolean requireFasting;

    @ColumnInfo(name = "APPOINTMENT_TIMESTAMP")
    private long appointmentTimestamp;

    @NonNull
    @ColumnInfo(name = "VISIBILITY")
    private String visibility;

    public MedicalRecord(@NonNull String departmentName, @NonNull String visibility) {
        this.departmentName = departmentName;
        this.visibility = visibility;
    }

    public Long getLocalId() { return localId; }
    public void setLocalId(Long localId) { this.localId = localId; }

    public String getSyncId() { return syncId; }
    public void setSyncId(String syncId) { this.syncId = syncId; }

    @NonNull
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(@NonNull String departmentName) { this.departmentName = departmentName; }

    public String getSearchTags() { return searchTags; }
    public void setSearchTags(String searchTags) { this.searchTags = searchTags; }

    public String getDoctorNote() { return doctorNote; }
    public void setDoctorNote(String doctorNote) { this.doctorNote = doctorNote; }

    public boolean isRequireFasting() { return requireFasting; }
    public void setRequireFasting(boolean requireFasting) { this.requireFasting = requireFasting; }

    public long getAppointmentTimestamp() { return appointmentTimestamp; }
    public void setAppointmentTimestamp(long appointmentTimestamp) { this.appointmentTimestamp = appointmentTimestamp; }

    @NonNull
    public String getVisibility() { return visibility; }
    public void setVisibility(@NonNull String visibility) { this.visibility = visibility; }
}