# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Eclipse\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript callback
# class:
#-keepclassmembers class fqcn.of.javascript.callback.for.webview {
#   public *;
#}
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**