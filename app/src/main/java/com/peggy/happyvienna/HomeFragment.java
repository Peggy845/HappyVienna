package com.peggy.happyvienna;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.peggy.happyvienna.data.model.TempBPRecord;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private List<TempBPRecord> tempSessionRecords = new ArrayList<>();
    private long lastInputTimestamp = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setupQuickActions(view);

        return view;
    }



    private void setupQuickActions(View view) {
        MaterialButton btnNfc = view.findViewById(R.id.btn_quick_nfc);
        MaterialButton btnBp = view.findViewById(R.id.btn_quick_bp);

        btnNfc.setOnClickListener(v -> {
            // TODO: 啟動 NFC 掃描邏輯
            Log.d(TAG, "NFC Medication Triggered");
        });

        btnBp.setOnClickListener(v -> {
            showBloodPressureDialog();
        });
    }

    private void saveBloodPressure(String data) {
        // TODO: 串接 Room Database 儲存血壓邏輯
        Log.d(TAG, "Saving BP data: " + data);
    }

    private void showBloodPressureDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_bp_selection, null);
        dialog.setContentView(view);

        // 20 分鐘效期檢查：若超過時間則清空暫存
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastInputTimestamp > 20 * 60 * 1000) {
            tempSessionRecords.clear();
        }

        LinearLayout recordList = view.findViewById(R.id.layout_record_list);
        MaterialButton btnMeasure = view.findViewById(R.id.btn_measure_again);
        MaterialButton btnRegister = view.findViewById(R.id.btn_register);
        MaterialButton btnClose = view.findViewById(R.id.btn_close);

        refreshRecordList(recordList, btnRegister);

        // 「再量一次」：新增數據
        btnMeasure.setOnClickListener(v -> {
            showInputPopup(dialog, recordList, btnRegister);
        });

        // 「登記」：正式寫入 Room 並關閉
        btnRegister.setOnClickListener(v -> {
            saveSelectedToDatabase();
            tempSessionRecords.clear();
            dialog.dismiss();
        });

        btnClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void refreshRecordList(LinearLayout container, MaterialButton btnRegister) {
        container.removeAllViews();
        btnRegister.setEnabled(false);

        for (int i = 0; i < tempSessionRecords.size(); i++) {
            TempBPRecord record = tempSessionRecords.get(i);
            View itemView = getLayoutInflater().inflate(R.layout.item_bp_temp, null);

            RadioButton rb = itemView.findViewById(R.id.rb_select);
            TextView tvInfo = itemView.findViewById(R.id.tv_bp_info);

            tvInfo.setText(String.format("[%s]  %s", record.time, record.value));
            rb.setChecked(record.isSelected);

            final int index = i;
            itemView.setOnClickListener(v -> {
                for (TempBPRecord r : tempSessionRecords) r.isSelected = false;
                tempSessionRecords.get(index).isSelected = true;
                refreshRecordList(container, btnRegister);
            });

            if (record.isSelected) btnRegister.setEnabled(true);
            container.addView(itemView);
        }
    }

    private void showInputPopup(BottomSheetDialog parentDialog, LinearLayout recordList, MaterialButton btnRegister) {
        BottomSheetDialog inputDialog = new BottomSheetDialog(requireContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_bp_input, null);
        inputDialog.setContentView(view);

        TextInputEditText etSys = view.findViewById(R.id.et_sys);
        TextInputEditText etDia = view.findViewById(R.id.et_dia);
        TextInputEditText etPulse = view.findViewById(R.id.et_pulse);
        MaterialButton btnConfirm = view.findViewById(R.id.btn_confirm_input);

        etSys.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 3) etDia.requestFocus(); // 高壓滿 3 位自動跳低壓
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });

        etDia.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 2) etPulse.requestFocus(); // 低壓滿 2 位自動跳心跳
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });

        btnConfirm.setOnClickListener(v -> {
            String sys = etSys.getText().toString().trim();
            String dia = etDia.getText().toString().trim();
            String pulse = etPulse.getText().toString().trim();

            // 防禦性檢查：確保沒有漏填
            if (TextUtils.isEmpty(sys) || TextUtils.isEmpty(dia) || TextUtils.isEmpty(pulse)) {
                Toast.makeText(requireContext(), "請填寫完整三個數值", Toast.LENGTH_SHORT).show();
                return;
            }

            // 組合字串
            String bpValue = sys + " / " + dia + " / " + pulse;
            String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

            TempBPRecord newRecord = new TempBPRecord(currentTime, bpValue);

            // 將焦點移至最新一筆
            for (TempBPRecord r : tempSessionRecords) {
                r.isSelected = false;
            }
            newRecord.isSelected = true;

            tempSessionRecords.add(newRecord);
            lastInputTimestamp = System.currentTimeMillis();

            // 刷新底層的暫存清單並關閉輸入視窗
            refreshRecordList(recordList, btnRegister);
            inputDialog.dismiss();
        });

        inputDialog.show();
    }

    // 將選中的最終數據寫入底層 SQLite (Room)
    private void saveSelectedToDatabase() {
        TempBPRecord finalRecord = null;
        for (TempBPRecord record : tempSessionRecords) {
            if (record.isSelected) {
                finalRecord = record;
                break;
            }
        }

        if (finalRecord != null) {
            // TODO: 將 finalRecord 轉換為 Room Entity 並寫入 AppDatabase
            Log.d(TAG, "Red Team Audit: User confirmed BP state -> " + finalRecord.time + " | " + finalRecord.value);
            // 這裡未來會調用：((HappyViennaApp) requireActivity().getApplication()).getDatabase().medicalRecordDao().insert(...)
        } else {
            Log.w(TAG, "Save triggered but no record selected. Defensive drop.");
        }
    }
}