package com.athena.asm.util;

import org.apache.http.HttpVersion;
<<<<<<< HEAD
import org.apache.http.client.HttpClient;
=======
>>>>>>> 9c7890d3be66f443ba38d6f498fd53e7a0132a4d
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpClientHelper {
    private static HttpClientHelper mClientHelper;
<<<<<<< HEAD
    private static final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 1.7; .NET CLR 1.1.4322; CIBA; .NET CLR 2.0.50727)";
    private static final String DEFAULT_CHARSET = HTTP.UTF_8;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 10000;
=======
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36";
    private static final String DEFAULT_CHARSET = HTTP.UTF_8;
    private static final int CONNECT_TIMEOUT = 15000;
    private static final int READ_TIMEOUT = 15000;
>>>>>>> 9c7890d3be66f443ba38d6f498fd53e7a0132a4d

    private DefaultHttpClient mHttpClient;

    private HttpClientHelper(){

    }

    public static HttpClientHelper getInstance(){
        if(mClientHelper == null){
            synchronized (HttpClientHelper.class) {
                if(mClientHelper == null){
                    mClientHelper = new HttpClientHelper();
                }
            }
        }
        return mClientHelper;
    }

    public synchronized DefaultHttpClient getHttpClient(){
        if(mHttpClient == null){
            synchronized (HttpClientHelper.class) {
                if(mHttpClient == null){
                    SchemeRegistry sr = new SchemeRegistry();
                    Scheme http = new Scheme("http", PlainSocketFactory.getSocketFactory(), 80);
                    sr.register(http);

                    HttpParams params = new BasicHttpParams();
                    HttpProtocolParams.setUserAgent(params, USER_AGENT);
                    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
                    HttpProtocolParams.setContentCharset(params, DEFAULT_CHARSET);

<<<<<<< HEAD
                    ConnManagerParams.setMaxTotalConnections(params, 10);
                    ConnManagerParams.setTimeout(params, 1000);
=======
                    ConnManagerParams.setMaxTotalConnections(params, 50);
                    ConnManagerParams.setTimeout(params, 15000); // 15 seconds
>>>>>>> 9c7890d3be66f443ba38d6f498fd53e7a0132a4d

                    HttpConnectionParams.setConnectionTimeout(params, CONNECT_TIMEOUT);
                    HttpConnectionParams.setSoTimeout(params, READ_TIMEOUT);

                    // 支持多线程
                    ClientConnectionManager cm = new ThreadSafeClientConnManager(params, sr);
                    mHttpClient = new DefaultHttpClient(cm, params);
                }
            }
        }
        return mHttpClient;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 9c7890d3be66f443ba38d6f498fd53e7a0132a4d
