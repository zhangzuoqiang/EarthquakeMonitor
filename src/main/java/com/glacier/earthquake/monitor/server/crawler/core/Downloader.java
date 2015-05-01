package com.glacier.earthquake.monitor.server.crawler.core;

import com.glacier.earthquake.monitor.server.util.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by glacier on 15-5-1.
 */
public class Downloader {

    private static Logger logger = Logger.getLogger(Downloader.class.getName());
    private static DefaultHttpClient httpClient;
    public static final int HTTP_GET = 0;
    public static final int HTTP_POST = 1;

    /**
     * 设置Downloader模块所需的HttpClient
     * @param client 经过登陆操作返回的HttpClient
     * */
    public static void setClient(DefaultHttpClient client) {
        httpClient = client;
    }

    /**
     * 对相应地址使用相应方法返回抓取得到的dom树
     * @param url 需要获取的地址
     * @param method 访问该地址需要使用的HTTP请求方法
     * @return 返回获取得到的Document文档树
     * */
    public Document doucment(String url, int method) {
        try {

            HttpResponse response = null;
            if ( method == HTTP_GET ) {
                HttpGet httpGet = new HttpGet(url);

                httpGet.setHeader("Connection", "keep-alive");
                httpGet.setHeader("Cache-Control", "max-age=0");
                httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");

                httpGet.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
                httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
                httpGet.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
                httpGet.getParams().setBooleanParameter("http.tcp.nodelay", true);
                httpGet.getParams().setParameter("http.connection.stalecheck", false);
                httpGet.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
                response = httpClient.execute(httpGet);
            }
            else if ( method == HTTP_POST ) {
                HttpPost httpPost = new HttpPost(url);

                httpPost.setHeader("Connection", "keep-alive");
                httpPost.setHeader("Cache-Control", "max-age=0");
                httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                httpPost.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");

                httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
                httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
                httpPost.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
                httpPost.getParams().setBooleanParameter("http.tcp.nodelay", true);
                httpPost.getParams().setParameter("http.connection.stalecheck", false);
                httpPost.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
                response = httpClient.execute(httpPost);
            }
            HttpEntity entity = response.getEntity();
            Document document = Jsoup.parse(getContent(entity, "UTF-8"));
            document.setBaseUri(url);   //设置document的来源地址

            return document;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    /**
     * 为保证文档树不产生乱码情况
     * @param entity HTTP请求后得到的HttpEntity
     * @param encode 需要指定的最终文字编码
     * @return 返回按照指定编码转码后的页面源码, 已进行全角转半角处理
     * */
    private static String getContent(HttpEntity entity, String encode) {
        BufferedReader reader = null;
        StringBuffer buffer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(entity.getContent(), encode));
            buffer = new StringBuffer();
            String temp = null;
            while( (temp = reader.readLine()) != null ) {
                buffer.append(temp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.full2half(buffer.toString());
    }

}
