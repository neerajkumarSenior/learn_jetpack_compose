# 🛠️ Step-by-Step Implementation Guide: Building NexusAI

इस गाइड का पालन करके आप इस AI-Ready प्रोजेक्ट को अपने कंप्यूटर पर स्टेप-बाय-स्टेप बना सकते हैं।

---

## 📅 Phase 1: Project Setup & Dependencies

### Step 1: Create a New Project
1. **Android Studio** (Koala, Ladybug या लेटेस्ट 2026 वर्शन) खोलें।
2. **New Project** -> **Empty Compose Activity** चुनें।
3. Name: `NexusAI` | Package Name: `com.example.nexusai` | Minimum SDK: `API 35 (Android 15 / 16 Preview)` सेट करें।

### Step 2: Configure Version Catalog (`libs.versions.toml`)
अपनी Gradle डिपेंडेंसीज़ को मैनेज करने के लिए `gradle/libs.versions.toml` में ये वर्शन्स और लाइब्रेरीज़ जोड़ें:

```toml
[versions]
agp = "8.8.0"
kotlin = "2.1.0"
ksp = "2.1.0-1.0.29"
composeBom = "2026.02.00"
appfunctions = "1.0.0-alpha06"
room = "2.7.0-alpha01"
hilt = "2.51.1"

[libraries]
# AppFunctions
androidx-appfunctions = { group = "androidx.appfunctions", name = "appfunctions", version.ref = "appfunctions" }
androidx-appfunctions-service = { group = "androidx.appfunctions", name = "appfunctions-service", version.ref = "appfunctions" }
androidx-appfunctions-compiler = { group = "androidx.appfunctions", name = "appfunctions-compiler", version.ref = "appfunctions" }

# Room & Hilt
room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }
room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-compiler", version.ref = "hilt" }
```

### Step 3: Update App Level `build.gradle.kts`
अपने `:app` मॉड्यूल की बिल्ड फ़ाइल में प्लगइन्स और लाइब्रेरीज़ को लागू करें:

```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 35
    // बाकी डिफ़ॉल्ट कॉन्फ़िगरेशन...
}

dependencies {
    // Jetpack Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    
    // AppFunctions Core & KSP Compiler
    implementation(libs.androidx.appfunctions)
    implementation(libs.androidx.appfunctions.service)
    ksp(libs.androidx.appfunctions.compiler)
    
    // Room & Hilt
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
```

---

## 🗄️ Phase 2: Core Data Layer (Room Database)

AI बैकग्राउंड में जो भी डेटा प्रोसेस करेगा, उसे सेव करने के लिए हमें एक डेटाबेस चाहिए।

### Step 4: Create Expense Entity (`ExpenseEntity.kt`)
```kotlin
package com.example.nexusai.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val category: String,
    val timestamp: Long = System.currentTimeMillis()
)
```

### Step 5: Create Database & DAO
```kotlin
@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expenses ORDER BY timestamp DESC")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>
}

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}
```

---

## 🤖 Phase 3: Writing the AppFunction (AI Agent Bridge)

अब हम वो "Skill" बनाएंगे जिसे बाहर से Google Gemini या कोई अन्य AI एजेंट ट्रिगर कर सकता है।

### Step 6: Create the AppFunction Service (`ExpenseAppFunctionService.kt`)
**महत्वपूर्ण:** फ़ंक्शन के ऊपर लिखे कमेंट्स (KDoc) बहुत ज़रूरी हैं। AI इसी को पढ़कर समझता है कि उसे कब और क्यों इस फ़ंक्शन को चलाना है।

```kotlin
package com.example.nexusai.feature.appfunctions

import androidx.appfunctions.AppFunction
import androidx.appfunctions.AppFunctionService
import androidx.appfunctions.AppFunctionServiceEntryPoint
import com.example.nexusai.data.local.ExpenseDao
import com.example.nexusai.data.local.ExpenseEntity
import javax.inject.Inject

@AppFunctionServiceEntryPoint
class ExpenseAppFunctionService : AppFunctionService() {

    @Inject
    lateinit var expenseDao: ExpenseDao

    /**
     * यूजर के खर्चों को ट्रैक करके सीधे लोकल डेटाबेस में सेव करता है।
     * @param amount खर्च की गई रकम या राशि (रुपए में)
     * @param category खर्च की श्रेणी (उदा. Food, Transport, Entertainment)
     */
    @AppFunction
    suspend fun addExpenseFromAI(amount: Double, category: String): String {
        val newExpense = ExpenseEntity(amount = amount, category = category)
        expenseDao.insertExpense(newExpense)
        
        return "सफलतापूर्वक ₹amount को 'category' केटेगरी में जोड़ दिया गया है!"
    }
}
```

---

## 🎨 Phase 4: Jetpack Compose UI (Reactive State)

जैसे ही AI बैकग्राउंड में डेटा डालेगा, हमारी Compose स्क्रीन बिना ऐप रीस्टार्ट हुए तुरंत री-कम्पोज़ (अपडेट) होगी।

### Step 7: Create ViewModel
```kotlin
@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseDao: ExpenseDao
) : ViewModel() {
    // डेटाबेस में होने वाले हर बदलाव को यह Flow रीयल-टाइम में ट्रैक करेगा
    val expensesState: StateFlow<List<ExpenseEntity>> = expenseDao.getAllExpenses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
```

### Step 8: Build the Compose Screen (`ExpenseScreen.kt`)
```kotlin
@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = hiltViewModel()) {
    val expenses by viewModel.expensesState.collectAsState()

    Scaffold(
        topBar = { Text("NexusAI - Expense Ledger", style = MaterialTheme.typography.titleLarge) }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            items(expenses) { expense ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = expense.category, style = MaterialTheme.typography.bodyLarge)
                        Text(text = "₹\${expense.amount}", style = MaterialTheme.typography.bodyLarge, color = Color.Green)
                    }
                }
            }
        }
    }
}
```

---

## 🧪 Phase 5: Testing & Showcasing Your App

### Step 9: Use Android Studio AppFunctions Viewer
1. अपने फ़ोन या एमुलेटर को कंप्यूटर से कनेक्ट करें।
2. Android Studio में **Tools -> AppFunctions Viewer** खोलें।
3. यहाँ आपको अपना `addExpenseFromAI` फ़ंक्शन दिखाई देगा।
4. इनपुट बॉक्स में `amount: 250` और `category: "Dinner"` डालकर **Execute** पर क्लिक करें।
5. **जादू देखें:** आपके ऐप की स्क्रीन पर तुरंत ₹250 का डिनर एक्सपेंस बिना ऐप को मैन्युअली छुए दिखाई देने लगेगा! ✨
