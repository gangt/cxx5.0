package com.xi6666.app.di.modules;

import android.content.Context;

import com.baidu.platform.comapi.map.E;
import com.google.gson.Gson;
import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.network.ApiRest;
import com.xi6666.network.LogInterceptor;
import com.xi6666.network.Repository;
import com.xi6666.network.RepositoryImpl;
import com.xi6666.network.RetrofitCallBackUtil;
import com.xi6666.network.cookie.PersistentCookieJar;
import com.xi6666.network.cookie.cache.SetCookieCache;
import com.xi6666.network.cookie.persistence.SharedPrefsCookiePersistor;
import com.xi6666.utils.AppUitls;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr_yang on 2016/10/17.
 */
@Module
public class ApiModule {
    private BaseApplication mBaseApplication;


    public ApiModule(BaseApplication mApplication) {
        this.mBaseApplication = mApplication;
    }


    private OkHttpClient makeHttpClient() {
        int[] certficates = new int[]{R.raw.book};

        OkHttpClient httpClient;
        if (AppUitls.isApkInDebug(mBaseApplication)) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            LogInterceptor logInterceptor = new LogInterceptor();
            httpClient = new OkHttpClient.Builder().sslSocketFactory
                    (getSSLSocketFactory(mBaseApplication, certficates)).addInterceptor(logging).
                    addInterceptor(logInterceptor).cookieJar(new PersistentCookieJar
                    (new SetCookieCache(), new SharedPrefsCookiePersistor(mBaseApplication))).build();
        } else {
            httpClient = new OkHttpClient.Builder().sslSocketFactory
                    (getSSLSocketFactory(mBaseApplication, certficates)).cookieJar(new PersistentCookieJar
                    (new SetCookieCache(), new SharedPrefsCookiePersistor(mBaseApplication))).build();
        }

        return httpClient;
    }

    @Provides
    @Singleton
    ApiRest providesRetrofit() {
        return new Retrofit.Builder().baseUrl(ApiRest.baseUrl).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(makeHttpClient())
                .build().create(ApiRest.class);
    }

    @Provides
    @Singleton
    RetrofitCallBackUtil<E> providesRetrofitUtils() {
        return new RetrofitCallBackUtil<>(mBaseApplication.getApplicationContext());
    }

    @Provides
    @Singleton
    Repository provideRepository(ApiRest apiRest) {
        return new RepositoryImpl(apiRest);
    }

    @Provides
    @Singleton
    Gson providesGson() {
        return new Gson();
    }

    private SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        //CertificateFactory用来证书生成
        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                //读取本地证书
                InputStream is = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(is));

                if (is != null) {
                    is.close();
                }
            }
            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {

        }
        return null;
    }
}
