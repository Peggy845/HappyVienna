# Happy Vienna 🏥

**A personal health companion app for tracking vital signs, medical appointments, and daily wellness goals.**

> *A portfolio project developed during leisure time to demonstrate full-stack capabilities in Android development, IoT integration, and embedded systems.*

---

## 🎯 Project Overview

Happy Vienna is an Android health management application designed to help users organize and track their medical data in one place. The app demonstrates a holistic approach to personal health management by integrating:

- **Vital Signs Monitoring** – Blood pressure and heart rate tracking with timestamp records
- **Medical Records Management** – Organized storage of medical appointments, department visits, and doctor notes
- **Daily Goal Tracking** – Health reminders and habit tracking with support for automated BP measurements and manual checks
- **IoT Integration** – BLE connectivity for seamless data collection from wearable devices (in progress)

---

## 🚀 Technical Highlights

### Architecture & Best Practices
- **Modern Android Stack**: Built with Android 36 (latest Gradle 9.0.1)
- **Room Database**: Type-safe local data persistence with SQLite backend
- **Material Design 3**: Native Android components for intuitive UI/UX
- **Fragment-based Navigation**: Multi-tab bottom navigation with proper state management
- **Java 17**: Leveraging latest language features for cleaner, more maintainable code

### Key Technical Features
| Component | Technology | Purpose |
|-----------|-----------|---------|
| Database | AndroidX Room + SQLite | Structured data persistence with query abstraction |
| UI Framework | Material Design + ConstraintLayout | Responsive, modern interface |
| Navigation | Fragment + Bottom Navigation | Smooth multi-screen experience |
| Testing | JUnit + Espresso | Unit and instrumentation testing support |
| Build System | Gradle with Version Catalog | Centralized dependency management |

---

## 📋 Data Models

The app manages three core data entities:

1. **DailyGoal** – Track health reminders and habits
   - Goal name, completion status, timestamps
   - Support for automated (BP, NFC) and manual tracking

2. **MedicalRecord** – Comprehensive medical visit records
   - Department information, doctor notes, appointment scheduling
   - Fasting requirements, visibility controls, sync capabilities

3. **TempBPRecord** – Vital signs data points
   - Blood pressure (systolic/diastolic), heart rate
   - Time-stamped measurements for trend analysis

---

## 🔄 IoT & Hardware Integration (In Development)

This project is part of a **Dual-CPU embedded system** integration:
- **CM4 Bluetooth LE**: Advertising health device services via BLE 5.x protocol
- **Temperature/Health Sensors**: Real-time data collection from IoT peripherals
- **Incremental Development**: Following a structured 3-stage integration approach
  1. BLE library integration and build stability
  2. Device discovery and advertising verification
  3. GATT services and characteristic read/write operations

---

## 🛠️ Development Setup

### Prerequisites
- Android Studio (Flamingo or later)
- JDK 17+
- Android SDK 36 (target), SDK 26+ (minimum)

### Build & Run
```bash
# Clone the repository
git clone https://github.com/[username]/HappyVienna.git
cd HappyVienna

# Build the project
./gradlew clean build

# Run on emulator/device
./gradlew installDebug
```

### Project Structure
```
HappyVienna/
├── app/
│   ├── src/main/java/com/peggy/happyvienna/
│   │   ├── data/
│   │   │   ├── entity/          # Room database entities
│   │   │   ├── dao/             # Data access objects
│   │   │   └── model/           # UI/ViewModel models
│   │   ├── MainActivity.java    # Tab navigation controller
│   │   ├── HomeFragment.java    # Main dashboard
│   │   └── HappyViennaApp.java  # Application initialization
│   └── build.gradle             # Module dependencies
├── gradle/
│   └── libs.versions.toml       # Centralized version management
└── build.gradle                 # Project-level configuration
```

---

## 📊 Code Quality

- **Type Safety**: Full Java 17 implementation with no null pointer surprises
- **Separation of Concerns**: Clean entity/DAO/UI layer architecture
- **Resource Management**: Proper lifecycle handling with savedInstanceState persistence
- **Dependency Injection**: Room database auto-configuration
- **Testing Framework**: JUnit + Espresso test harness integrated

---

## 🎓 Learning Outcomes

This project demonstrates:

✅ **Full Android Development Lifecycle**
- UI design and implementation (Material Design)
- Local database design and optimization
- Fragment lifecycle and state management

✅ **Software Engineering Practices**
- Version-controlled development with Git
- Gradle-based build automation
- Structured component architecture

✅ **Embedded Systems Integration**
- BLE (Bluetooth Low Energy) protocol implementation
- Real-time sensor data collection
- Multi-threaded device communication

✅ **Cross-platform Capability**
- Android app development
- IoT/embedded firmware integration (CM0/CM4)
- End-to-end health data pipeline

---

## 🔐 Privacy & Compliance

- **Local Storage First**: All health data stored locally on device by default
- **Visibility Controls**: Granular privacy settings per medical record
- **No Cloud Dependency**: Designed for privacy-conscious health tracking
- **Future-Ready**: Sync infrastructure prepared for optional secure cloud backup

---

## 📈 Future Enhancements

- [ ] Complete BLE integration for automatic BP device reading
- [ ] Health data visualization and trend analysis
- [ ] Multi-language support (EN, ZH-TW)
- [ ] Cloud sync with end-to-end encryption
- [ ] Wearable integration (Wear OS)
- [ ] Export medical records (PDF/CSV)

---

## 💼 Portfolio Highlights for Interviewers

**Why I Built This:**
During leisure time, I wanted to create a real-world application that solves an actual problem—helping people manage their health data. This project goes beyond a simple CRUD app:

- **Full-stack thinking**: App → Database → IoT Hardware integration
- **Production mindset**: State management, lifecycle handling, error recovery
- **Hardware integration**: Not just app development; exploring embedded systems and IoT protocols
- **Continuous learning**: Latest Android APIs (Gradle 9.0, Material 3, Java 17) and industry standards

**Technical Depth:**
This isn't a tutorial project. It demonstrates:
- Understanding of mobile architecture patterns
- Database schema design for real-world scenarios
- Integration with hardware systems (BLE/sensors)
- Professional code organization and practices

---

## 📝 License

This project is provided as-is for portfolio demonstration purposes.

---

## 📬 Contact

Questions about the project? Feel free to reach out or review the git commit history for development decisions.

---

**Last Updated**: June 2026  
**Status**: Active Development (IoT Integration Phase)
