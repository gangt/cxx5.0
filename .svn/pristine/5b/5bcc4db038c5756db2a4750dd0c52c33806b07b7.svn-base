# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Users\Mr_yang\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#retrofit2混淆设置
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8


#友盟统计代码混淆
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

#极光tuisong混淆设置
-dontoptimize
-dontpreverify
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

#支付宝相关混淆设置
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }

-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-keep class com.alipay.** {*;}
-dontwarn com.taobao.**
-dontwarn com.alibaba.**
-dontwarn com.alipay.**

-keep class com.ut.** {*;}
-dontwarn com.ut.**

-keep class com.ta.** {*;}
-dontwarn com.ta.**

-keep class anet.**{*;}
-keep class org.android.spdy.**{*;}
-keep class org.android.agoo.**{*;}
-dontwarn anet.**
-dontwarn org.android.spdy.**
-dontwarn org.android.agoo.**

#微信支付混淆设置
-keep class com.tencent.wxop.** { *; }

#百度地图
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

#毕加索图片加载库混淆
-dontwarn com.squareup.okhttp.**

#网络连接桥
-keep class com.xi6666.html5show.JsCallAndroid.**{*;}

#拼音4j混淆
-dontwarn demo.**
-keep class demo.**{*;}

-dontwarn net.sourceforge.pinyin4j.**
-keep class net.sourceforge.pinyin4j.**{*;}
-keep class net.sourceforge.pinyin4j.format.**{*;}
-keep class net.sourceforge.pinyin4j.format.exception.**{*;}

-keep class com.xi6666.home.HomeBannerBean {*;}
-keep class com.xi6666.home.HomeSpeCialBean {*;}

-keep class com.xi6666.login.bean.TokenBean {*;}

#数据bean
-keep class com.xi6666.databean.** {*;}
#eventbus数据
-keep class com.xi6666.eventbus.** {*;}

#eventbus混淆设置
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode {*;}
# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#数据bean
-keep class com.xi6666.common.UserData{*;}

# 使用注解
-keepattributes *Annotation*,Signature
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation { *; }

#广告ubk
-keep class com.feelwx.ubk.sdk.** {*;}

#城市
-keep class com.xi6666.common.CityBean {*;}

#经纬度
-keep class com.xi6666.common.LocationBean{*;}

#个人信息
-keep class com.xi6666.common.UserData{*;}

#微信分享
-keep class com.tencent.mm.sdk.** {*;}

#
-keep class com.xi6666.network.cookie.PersistentCookieJar{*;}
-keep class com.xi6666.network.cookie.cache.SetCookieCache{*;}
-keep class com.xi6666.network.cookie.persistence.SharedPrefsCookiePersistor{*;}

-keep class com.xi6666.evaluate.bean.** {*;}
-keep class com.xi6666.illegal.bean.** {*;}
-keep class com.xi6666.order.bean.** {*;}


-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontwarn java.lang.invoke.*


-keep class com.xi6666.carWash.base.network.**{
*;
}
-keep public class * extends com.xi6666.carWash.base.network.BaseModel
-keep public class * extends com.xi6666.carWash.base.network.BasePresenter
-keep public class * extends com.xi6666.carWash.base.network.BaseView

-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.xi6666.**.bean.**{ *; }

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#glidemodule
-keep class com.xi6666.network.OkHttpGlideModule{*;}
-keep class com.xi6666.network.OkHttpStreamFetcher{*;}
-keep class com.xi6666.network.OkHttpUrlLoader{*;}
-keep class com.xi6666.databean.WeChatPayBean{*;}


