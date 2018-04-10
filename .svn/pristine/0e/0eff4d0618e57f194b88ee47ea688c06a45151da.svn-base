package com.xi6666.network;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.xi6666.R;
import com.xi6666.app.BaseApplication;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;

/**
 * A {@link GlideModule} implementation to replace Glide's default
 * {@link java.net.HttpURLConnection} based {@link com.bumptech.glide.load.model.ModelLoader} with an OkHttp based
 * {@link com.bumptech.glide.load.model.ModelLoader}.
 * <p>
 * <p>
 * If you're using gradle, you can include this module simply by depending on the aar, the module will be merged
 * in by manifest merger. For other build systems or for more more information, see
 * {@link GlideModule}.
 * </p>
 */
public class OkHttpGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Do nothing.
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        int[] certficates = new int[]{R.raw.book};
        OkHttpClient httpClient;
        OkHttpClient build = new OkHttpClient.Builder().sslSocketFactory
                (getSSLSocketFactory(BaseApplication.getmAppContext(), certficates)).build();
        httpClient = build;
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(build));

        //  glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
    protected SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

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
