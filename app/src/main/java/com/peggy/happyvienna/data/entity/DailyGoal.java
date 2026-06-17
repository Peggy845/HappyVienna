import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DAILY_GOAL")
public class DailyGoal {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String goalName;      // 例如："早上量血壓", "吃藥A"
    public boolean isCompleted;
    public String completionTime; // 紀錄完成的時間點
    public String goalType;      // "BP", "NFC", "MANUAL" (手動勾選)
}