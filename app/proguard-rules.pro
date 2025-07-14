# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn java.awt.Component
-dontwarn java.awt.GraphicsEnvironment
-dontwarn java.awt.HeadlessException
-dontwarn java.awt.Window
-dontwarn java.lang.reflect.AnnotatedType

-keep class cn.spacexc.*
-keep class cn.spacexc.**

-keep class org.drinkless.tdlib.TdApi { *; }
-keep class org.drinkless.tdlib.TdApi { *; }
-keep class org.* { *; }
-keep class org.** { *; }
-keepclassmembers class org.drinkless.tdlib.TdApi { *; }
-keepclassmembers class org.* { *; }
-keepclassmembers class org.** { *; }
-keep class org.drinkless.tdlib.TdApi$* { *; }
-keep class org.drinkless.tdlib.Client
-keepclassmembers class org.drinkless.tdlib.Client { *; }
-keep class org.drinkless.tdlib.Client$* { *; }

-dontwarn java.lang.invoke.MethodHandleProxies

-keepclassmembers class * extends androidx.datastore.preferences.protobuf.GeneratedMessageLite {
    <fields>;
}

-keepclassmembers class cn.spacexc.* {
    <fields>;
}

-keepclassmembers class cn.spacexc.** {
    <fields>;
}

-keepclassmembers class androidx.datastore.preferences.PreferencesProto$PreferenceMap {
    private androidx.datastore.preferences.protobuf.MapFieldLite preferences_;
}

-keepclassmembers class androidx.datastore.preferences.PreferencesProto$Value {
     private java.lang.Object value_;
     private int valueCase_;
}

-keep class org.*
-keep class org.**
-keepclassmembers class org.**
-keepclassmembers class org.*
-keep class org.** {
    <fields>;
}
-keep class leveldb.*
-keep class leveldb.**
-keepclassmembers class leveldb.**
-keepclassmembers class leveldb.*
-keep class leveldb.** {
    <fields>;
}
-keep class androidx.media3.*
-keep class androidx.media3.**
-keepclassmembers class androidx.media3.*
-keepclassmembers class androidx.media3.**
-keep class androidx.media3.** {
    <fields>;
}

-dontwarn java.awt.geom.AffineTransform
-dontwarn sun.misc.Cleaner