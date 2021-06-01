[33mcommit b00dd84fcb6a7c560557e0bf44d01c008202b2cb[m[33m ([m[1;36mHEAD -> [m[1;32mfeat/add-api-functionality-to-app[m[33m)[m
Author: MatheusCBonafe <warpmatheus@gmail.com>
Date:   Wed May 26 16:21:36 2021 -0300

    code refactor

[1mdiff --git a/.idea/gradle.xml b/.idea/gradle.xml[m
[1mindex 23a89bb..a7c1cd1 100644[m
[1m--- a/.idea/gradle.xml[m
[1m+++ b/.idea/gradle.xml[m
[36m@@ -12,6 +12,7 @@[m
           <set>[m
             <option value="$PROJECT_DIR$" />[m
             <option value="$PROJECT_DIR$/app" />[m
[32m+[m[32m            <option value="$PROJECT_DIR$/data" />[m
           </set>[m
         </option>[m
         <option name="resolveModulePerSourceSet" value="false" />[m
[1mdiff --git a/app/build.gradle b/app/build.gradle[m
[1mindex 5ce899c..844063c 100644[m
[1m--- a/app/build.gradle[m
[1m+++ b/app/build.gradle[m
[36m@@ -38,6 +38,7 @@[m [mandroid {[m
 }[m
 [m
 dependencies {[m
[32m+[m[32m    implementation (project(":data"))[m
     implementation("io.coil-kt:coil:1.1.1")[m
     implementation 'com.squareup.retrofit2:retrofit:2.6.2'[m
     implementation 'com.squareup.retrofit2:converter-gson:2.6.2'[m
[1mdiff --git a/app/src/main/java/com/example/videoapplication/list/ListFragment.kt b/app/src/main/java/com/example/videoapplication/list/ListFragment.kt[m
[1mindex ed9b1b9..b923b13 100644[m
[1m--- a/app/src/main/java/com/example/videoapplication/list/ListFragment.kt[m
[1m+++ b/app/src/main/java/com/example/videoapplication/list/ListFragment.kt[m
[36m@@ -2,12 +2,13 @@[m [mpackage com.example.videoapplication[m
 [m
 import android.content.Intent[m
 import android.os.Bundle[m
[32m+[m[32mimport android.util.Log[m
 import android.view.LayoutInflater[m
 import android.view.View[m
 import android.view.ViewGroup[m
[31m-import android.widget.ListView[m
[31m-import android.widget.TextView[m
[32m+[m[32mimport android.widget.Toast[m
 import androidx.fragment.app.Fragment[m
[32m+[m[32mimport androidx.lifecycle.Observer[m
 import androidx.lifecycle.ViewModelProvider[m
 import com.example.videoapplication.databinding.FragmentListBinding[m
 import com.example.videoapplication.list.ListViewModel[m
[36m@@ -20,7 +21,13 @@[m [mimport com.example.videoapplication.video.VideoActivity[m
 class ListFragment : Fragment(), RecyclerAdapter.OnItemClickListener {[m
     // TODO: Rename and change types of parameters[m
 [m
[31m-    private lateinit var listViewModel : ListViewModel[m
[32m+[m[32m    private lateinit var listViewModel: ListViewModel[m
[32m+[m
[32m+[m[32m    val movieAdapter by lazy {[m
[32m+[m[32m        RecyclerAdapter([m
[32m+[m[32m            this@ListFragment[m
[32m+[m[32m        )[m
[32m+[m[32m    }[m
 [m
     val binding by lazy {[m
         FragmentListBinding.inflate(layoutInflater)[m
[36m@@ -34,6 +41,12 @@[m [mclass ListFragment : Fragment(), RecyclerAdapter.OnItemClickListener {[m
         listViewModel =[m
             ViewModelProvider(this).get(ListViewModel::class.java)[m
 [m
[32m+[m[32m        listViewModel.recyclerItems. observe(viewLifecycleOwner, Observer{ items ->[m
[32m+[m[32m            movieAdapter.setItemList(items)[m
[32m+[m[32m        })[m
[32m+[m
[32m+[m[32m        listViewModel.text()[m
[32m+[m
         val recyclerList = generateDummyList()[m
         setUpAdapter(recyclerList)[m
 [m
[36m@@ -43,10 +56,7 @@[m [mclass ListFragment : Fragment(), RecyclerAdapter.OnItemClickListener {[m
     private fun setUpAdapter(recyclerList: List<RecyclerItem>) {[m
         with(binding) {[m
             recyclerView.apply {[m
[31m-                adapter = RecyclerAdapter([m
[31m-                    recyclerList,[m
[31m-                    this@ListFragment[m
[31m-                )[m
[32m+[m[32m                adapter = movieAdapter[m
                 layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)[m
             }[m
         }[m
[1mdiff --git a/app/src/main/java/com/example/videoapplication/list/ListViewModel.kt b/app/src/main/java/com/example/videoapplication/list/ListViewModel.kt[m
[1mindex 69e2159..7f4df19 100644[m
[1m--- a/app/src/main/java/com/example/videoapplication/list/ListViewModel.kt[m
[1m+++ b/app/src/main/java/com/example/videoapplication/list/ListViewModel.kt[m
[36m@@ -1,12 +1,46 @@[m
 package com.example.videoapplication.list;[m
 [m
[32m+[m[32mimport androidx.lifecycle.LiveData[m
 import androidx.lifecycle.MutableLiveData[m
 import androidx.lifecycle.ViewModel[m
[32m+[m[32mimport com.example.data.movieapi.MovieRepository[m
 [m
 [m
 class ListViewModel : ViewModel() {[m
[31m-    private val _text = MutableLiveData<String>().apply {[m
[31m-     //   value = "This is List Fragment"[m
[32m+[m[32m    private var _text = MutableLiveData<String>()[m
[32m+[m[32m    val text: LiveData<String> get() = _text[m
[32m+[m
[32m+[m[32m    private var _recyclerItems = MutableLiveData<List<RecyclerItem>>()[m
[32m+[m[32m    val recyclerItems: LiveData<List<RecyclerItem>> get() = _recyclerItems[m
[32m+[m
[32m+[m[32m    private val movieRepository by lazy {[m
[32m+[m[32m        MovieRepository()[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    fun text() {[m
[32m+[m[32m        movieRepository.getItemData()[m
[32m+[m[32m        _recyclerItems.value = listOf([m
[32m+[m[32m            RecyclerItem([m
[32m+[m[32m                "https://2.bp.blogspot.com/-bPIkBuAXsbY/XR76sW9L4RI/AAAAAAAAELA/UcveontcdhwsrsHt9V5IqpYe6tMp3QmlACKgBGAs/w0/fantasy-monster-cthulhu-uhdpaper.com-4K-158.jpg",[m
[32m+[m[32m                "Cthulu, The Sleeping God",[m
[32m+[m[32m                "Cthulu is a Great Old One of great power who lies in a death-like slumber beneath the Pacific Ocean in his sunken city of R'lyeh. He remains a dominant presence in the eldrich dealings on our world. ",[m
[32m+[m[32m                true[m
[32m+[m[32m            ),[m
[32m+[m
[32m+[m[32m            RecyclerItem([m
[32m+[m[32m                "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/52b5d914-8ec4-4b4d-a1bf-d6885de9f09c/d6ubvy6-50dcb45c-bb4e-40ca-a764-8a5f9ad06758.jpg/v1/fill/w_1024,h_614,q_75,strp/yog_sothoth_rising_by_tentaclesandteeth_d6ubvy6-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NjE0IiwicGF0aCI6IlwvZlwvNTJiNWQ5MTQtOGVjNC00YjRkLWExYmYtZDY4ODVkZTlmMDljXC9kNnVidnk2LTUwZGNiNDVjLWJiNGUtNDBjYS1hNzY0LThhNWY5YWQwNjc1OC5qcGciLCJ3aWR0aCI6Ijw9MTAyNCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.67hHdPxALt59AWH96g-2jzpx5xkWUTDGymId-ZsLEf8",[m
[32m+[m[32m                "Yog-Sothoth, The Lurker at The Threshold",[m
[32m+[m[32m                "Yog-Sothoth is a cosmic entity and Outer God. Born of the Nameless Mist, he is the progenitor of Cthulhu, Hastur the Unspeakable and the ancestor of the Voormi.",[m
[32m+[m[32m                false[m
[32m+[m[32m            ),[m
[32m+[m
[32m+[m[32m            RecyclerItem([m
[32m+[m[32m                "https://i0.wp.com/actualplay.roleplayingpublicradio.com/wp-content/uploads/blackwind.jpg",[m
[32m+[m[32m                "Nyarlathotep, The Crawling Chaos",[m
[32m+[m[32m                "Nyarlathotep, known to many by his epithet The Crawling Chaos, is an Outer God in the Cthulhu Mythos. He is the spawn of Azathoth.",[m
[32m+[m[32m                true[m
[32m+[m[32m            )[m
[32m+[m[32m        )[m
     }[m
 }[m
 [m
[1mdiff --git a/app/src/main/java/com/example/videoapplication/list/RecyclerAdapter.kt b/app/src/main/java/com/example/videoapplication/list/RecyclerAdapter.kt[m
[1mindex c130e8e..0975c0d 100644[m
[1m--- a/app/src/main/java/com/example/videoapplication/list/RecyclerAdapter.kt[m
[1m+++ b/app/src/main/java/com/example/videoapplication/list/RecyclerAdapter.kt[m
[36m@@ -11,10 +11,11 @@[m [mimport com.example.videoapplication.R[m
 import com.example.videoapplication.databinding.ItemRecyclerViewBinding[m
 [m
 class RecyclerAdapter([m
[31m-    private val itemList: List<RecyclerItem>,[m
     private val listener: OnItemClickListener[m
[31m-) :[m
[31m-    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {[m
[32m+[m[32m) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {[m
[32m+[m
[32m+[m[32m    private var itemList: MutableList<RecyclerItem> = mutableListOf()[m
[32m+[m
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {[m
         val itemView =[m
             ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)[m
[36m@@ -32,6 +33,11 @@[m [mclass RecyclerAdapter([m
 [m
     override fun getItemCount(): Int = itemList.size[m
 [m
[32m+[m[32m    fun setItemList(items: List<RecyclerItem>) {[m
[32m+[m[32m        itemList.clear()[m
[32m+[m[32m        itemList.addAll(items)[m
[32m+[m[32m        notifyDataSetChanged()[m
[32m+[m[32m    }[m
 [m
     inner class RecyclerViewHolder(private val item: ItemRecyclerViewBinding) :[m
         RecyclerView.ViewHolder(item.root),[m
[1mdiff --git a/app/src/main/java/com/example/videoapplication/movieapi/MovieData.kt b/app/src/main/java/com/example/videoapplication/movieapi/MovieData.kt[m
[1mdeleted file mode 100644[m
[1mindex a76f768..0000000[m
[1m--- a/app/src/main/java/com/example/videoapplication/movieapi/MovieData.kt[m
[1m+++ /dev/null[m
[36m@@ -1,3 +0,0 @@[m
[31m-package com.example.videoapplication.movieapi[m
[31m-[m
[31m-class MovieData : ArrayList<MovieDataClass>()[m
\ No newline at end of file[m
[1mdiff --git a/data/.gitignore b/data/.gitignore[m
[1mnew file mode 100644[m
[1mindex 0000000..42afabf[m
[1m--- /dev/null[m
[1m+++ b/data/.gitignore[m
[36m@@ -0,0 +1 @@[m
[32m+[m[32m/build[m
\ No newline at end of file[m
[1mdiff --git a/data/build.gradle b/data/build.gradle[m
[1mnew file mode 100644[m
[1mindex 0000000..176aad8[m
[1m--- /dev/null[m
[1m+++ b/data/build.gradle[m
[36m@@ -0,0 +1,46 @@[m
[32m+[m[32mplugins {[m
[32m+[m[32m    id 'com.android.library'[m
[32m+[m[32m    id 'kotlin-android'[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32mandroid {[m
[32m+[m[32m    compileSdkVersion 30[m
[32m+[m[32m    buildToolsVersion "30.0.3"[m
[32m+[m
[32m+[m[32m    defaultConfig {[m
[32m+[m[32m        minSdkVersion 16[m
[32m+[m[32m        targetSdkVersion 30[m
[32m+[m[32m        versionCode 1[m
[32m+[m[32m        versionName "1.0"[m
[32m+[m
[32m+[m[32m        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"[m
[32m+[m[32m        consumerProguardFiles "consumer-rules.pro"[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    buildTypes {[m
[32m+[m[32m        release {[m
[32m+[m[32m            minifyEnabled false[m
[32m+[m[32m            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m[32m    compileOptions {[m
[32m+[m[32m        sourceCompatibility JavaVersion.VERSION_1_8[m
[32m+[m[32m        targetCompatibility JavaVersion.VERSION_1_8[m
[32m+[m[32m    }[m
[32m+[m[32m    kotlinOptions {[m
[32m+[m[32m        jvmTarget = '1.8'[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32mdependencies {[m
[32m+[m
[32m+[m[32m    implementation 'com.squareup.retrofit2:retrofit:2.6.2'[m
[32m+[m[32m    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'[m
[32m+[m[32m    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"[m
[32m+[m[32m    implementation 'androidx.core:core-ktx:1.5.0'[m
[32m+[m[32m    implementation 'androidx.appcompat:appcompat:1.3.0'[m
[32m+[m[32m    implementation 'com.google.android.material:material:1.3.0'[m
[32m+[m[32m    testImplementation 'junit:junit:4.+'[m
[32m+[m[32m    androidTestImplementation 'androidx.test.ext:junit:1.1.2'[m
[32m+[m[32m    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/data/consumer-rules.pro b/data/consumer-rules.pro[m
[1mnew file mode 100644[m
[1mindex 0000000..e69de29[m
[1mdiff --git a/data/proguard-rules.pro b/data/proguard-rules.pro[m
[1mnew file mode 100644[m
[1mindex 0000000..481bb43[m
[1m--- /dev/null[m
[1m+++ b/data/proguard-rules.pro[m
[36m@@ -0,0 +1,21 @@[m
[32m+[m[32m# Add project specific ProGuard rules here.[m
[32m+[m[32m# You can control the set of applied configuration files using the[m
[32m+[m[32m# proguardFiles setting in build.gradle.[m
[32m+[m[32m#[m
[32m+[m[32m# For more details, see[m
[32m+[m[32m#   http://developer.android.com/guide/developing/tools/proguard.html[m
[32m+[m
[32m+[m[32m# If your project uses WebView with JS, uncomment the following[m
[32m+[m[32m# and specify the fully qualified class name to the JavaScript interface[m
[32m+[m[32m# class:[m
[32m+[m[32m#-keepclassmembers class fqcn.of.javascript.interface.for.webview {[m
[32m+[m[32m#   public *;[m
[32m+[m[32m#}[m
[32m+[m
[32m+[m[32m# Uncomment this to preserve the line number information for[m
[32m+[m[32m# debugging stack traces.[m
[32m+[m[32m#-keepattributes SourceFile,LineNumberTable[m
[32m+[m
[32m+[m[32m# If you keep the line number information, uncomment this to[m
[32m+[m[32m# hide the original source file name.[m
[32m+[m[32m#-renamesourcefileattribute SourceFile[m
\ No newline at end of file[m
[1mdiff --git a/data/src/androidTest/java/com/example/data/ExampleInstrumentedTest.kt b/data/src/androidTest/java/com/example/data/ExampleInstrumentedTest.kt[m
[1mnew file mode 100644[m
[1mindex 0000000..1ea0a20[m
[1m--- /dev/null[m
[1m+++ b/data/src/androidTest/java/com/example/data/ExampleInstrumentedTest.kt[m
[36m@@ -0,0 +1,24 @@[m
[32m+[m[32mpackage com.example.data[m
[32m+[m
[32m+[m[32mimport androidx.test.platform.app.InstrumentationRegistry[m
[32m+[m[32mimport androidx.test.ext.junit.runners.AndroidJUnit4[m
[32m+[m
[32m+[m[32mimport org.junit.Test[m
[32m+[m[32mimport org.junit.runner.RunWith[m
[32m+[m
[32m+[m[32mimport org.junit.Assert.*[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m * Instrumented test, which will execute on an Android device.[m
[32m+[m[32m *[m
[32m+[m[32m * See [testing documentation](http://d.android.com/tools/testing).[m
[32m+[m[32m */[m
[32m+[m[32m@RunWith(AndroidJUnit4::class)[m
[32m+[m[32mclass ExampleInstrumentedTest {[m
[32m+[m[32m    @Test[m
[32m+[m[32m    fun useAppContext() {[m
[32m+[m[32m        // Context of the app under test.[m
[32m+[m[32m        val appContext = InstrumentationRegistry.getInstrumentation().targetContext[m
[32m+[m[32m        assertEquals("com.example.data.test", appContext.packageName)[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/data/src/main/AndroidManifest.xml b/data/src/main/AndroidManifest.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..e5d8f49[m
[1m--- /dev/null[m
[1m+++ b/data/src/main/AndroidManifest.xml[m
[36m@@ -0,0 +1,5 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?>[m
[32m+[m[32m<manifest xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m    package="com.example.data">[m
[32m+[m
[32m+[m[32m</manifest>[m
\ No newline at end of file[m
[1mdiff --git a/data/src/main/java/com/example/data/movieapi/MovieData.kt b/data/src/main/java/com/example/data/movieapi/MovieData.kt[m
[1mnew file mode 100644[m
[1mindex 0000000..640a4b7[m
[1m--- /dev/null[m
[1m+++ b/data/src/main/java/com/example/data/movieapi/MovieData.kt[m
[36m@@ -0,0 +1,3 @@[m
[32m+[m[32mpackage com.example.data.movieapi[m
[32m+[m
[32m+[m[32mclass MovieData : ArrayList<MovieDataClass>()[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/videoapplication/movieapi/MovieDataClass.kt b/data/src/main/java/com/example/data/movieapi/MovieDataClass.kt[m
[1msimilarity index 70%[m
[1mrename from app/src/main/java/com/example/videoapplication/movieapi/MovieDataClass.kt[m
[1mrename to data/src/main/java/com/example/data/movieapi/MovieDataClass.kt[m
[1mindex 5ffadea..892b5f3 100644[m
[1m--- a/app/src/main/java/com/example/videoapplication/movieapi/MovieDataClass.kt[m
[1m+++ b/data/src/main/java/com/example/data/movieapi/MovieDataClass.kt[m
[36m@@ -1,4 +1,4 @@[m
[31m-package com.example.videoapplication.movieapi[m
[32m+[m[32mpackage com.example.data.movieapi[m
 [m
 data class MovieDataClass([m
     val body: String,[m
[1mdiff --git a/app/src/main/java/com/example/videoapplication/movieapi/MovieInterface.kt b/data/src/main/java/com/example/data/movieapi/MovieInterface.kt[m
[1msimilarity index 75%[m
[1mrename from app/src/main/java/com/example/videoapplication/movieapi/MovieInterface.kt[m
[1mrename to data/src/main/java/com/example/data/movieapi/MovieInterface.kt[m
[1mindex 60e6689..daa0a58 100644[m
[1m--- a/app/src/main/java/com/example/videoapplication/movieapi/MovieInterface.kt[m
[1m+++ b/data/src/main/java/com/example/data/movieapi/MovieInterface.kt[m
[36m@@ -1,4 +1,4 @@[m
[31m-package com.example.videoapplication.movieapi[m
[32m+[m[32mpackage com.example.data.movieapi[m
 [m
 import retrofit2.Call[m
 import retrofit2.http.GET[m
[1mdiff --git a/app/src/main/java/com/example/videoapplication/movieapi/MovieRepository.kt b/data/src/main/java/com/example/data/movieapi/MovieRepository.kt[m
[1msimilarity index 89%[m
[1mrename from app/src/main/java/com/example/videoapplication/movieapi/MovieRepository.kt[m
[1mrename to data/src/main/java/com/example/data/movieapi/MovieRepository.kt[m
[1mindex ca0bfdd..8265a3a 100644[m
[1m--- a/app/src/main/java/com/example/videoapplication/movieapi/MovieRepository.kt[m
[1m+++ b/data/src/main/java/com/example/data/movieapi/MovieRepository.kt[m
[36m@@ -1,4 +1,4 @@[m
[31m-package com.example.videoapplication.movieapi[m
[32m+[m[32mpackage com.example.data.movieapi[m
 [m
 import android.util.Log[m
 import retrofit2.Call[m
[36m@@ -29,8 +29,8 @@[m [mclass MovieRepository {[m
                 call: Call<List<MovieDataClass>?>,[m
                 response: Response<List<MovieDataClass>?>[m
             ) {[m
[31m-          /*      val responseBody = response.body()!![m
[31m-                val stringBuilder = StringBuilder()[m
[32m+[m[32m               val responseBody = response.body()!![m
[32m+[m[32m               /* val stringBuilder = StringBuilder()[m
 [m
                 for (Data in responseBody) {[m
                     stringBuilder.append(Data.id)[m
[1mdiff --git a/data/src/test/java/com/example/data/ExampleUnitTest.kt b/data/src/test/java/com/example/data/ExampleUnitTest.kt[m
[1mnew file mode 100644[m
[1mindex 0000000..8943744[m
[1m--- /dev/null[m
[1m+++ b/data/src/test/java/com/example/data/ExampleUnitTest.kt[m
[36m@@ -0,0 +1,17 @@[m
[32m+[m[32mpackage com.example.data[m
[32m+[m
[32m+[m[32mimport org.junit.Test[m
[32m+[m
[32m+[m[32mimport org.junit.Assert.*[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m * Example local unit test, which will execute on the development machine (host).[m
[32m+[m[32m *[m
[32m+[m[32m * See [testing documentation](http://d.android.com/tools/testing).[m
[32m+[m[32m */[m
[32m+[m[32mclass ExampleUnitTest {[m
[32m+[m[32m    @Test[m
[32m+[m[32m    fun addition_isCorrect() {[m
[32m+[m[32m        assertEquals(4, 2 + 2)[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/settings.gradle b/settings.gradle[m
[1mindex d9d9aa9..c2fb8d1 100644[m
[1m--- a/settings.gradle[m
[1m+++ b/settings.gradle[m
[36m@@ -1,2 +1,3 @@[m
[32m+[m[32minclude ':data'[m
 include ':app'[m
 rootProject.name = "VideoApplication"[m
\ No newline at end of file[m
