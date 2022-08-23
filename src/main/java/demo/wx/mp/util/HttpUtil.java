package demo.wx.mp.util;

import com.alibaba.fastjson.JSONObject;
import demo.wx.mp.controller.IndexController;
import demo.wx.mp.pojo.Result;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: http request
 * @Author: zhanglin
 * @Date: 2022/8/22 09:38
 * @Package: com.sinosoft.geoinfosys.common.util.HttpUtil
 * @Version 1.0
 */
public class HttpUtil {
    /**
     * 处理get请求.
     *
     * @param url 请求路径
     * @return json
     * @author zhanglin
     */
    public static String getMap(String url, Map<String, String> params) {
        //请求结果
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        String content = "";
        try {
            //实例化httpclient
            httpClient = HttpClients.createDefault();
            //封装请求参数
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            //转化参数
            String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
            //创建HttpGet请求
            httpGet = new HttpGet(new StringBuilder(url).append("?").append(paramsStr).toString());
            //执行get方法
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            }
            httpGet.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != httpGet) {
                    httpGet.releaseConnection();
                }
                if (null != httpClient) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }


    /**
     * 处理get请求.
     *
     * @param url 请求路径
     * @return json
     * @author zhanglin  , Map<String, String> params
     */
    public static String get(String url) {
        //请求结果
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        String content = "";
        try {
            //实例化httpclient
            httpClient = HttpClients.createDefault();
            //封装请求参数
            //List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            //for (Map.Entry<String, String> entry : params.entrySet()) {
            //list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            //}
            //转化参数
            //String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
            //创建HttpGet请求
            //httpGet = new HttpGet(new StringBuilder(url).append("?").append(paramsStr).toString());
            httpGet = new HttpGet(new StringBuilder(url).toString());
            //执行get方法
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            }
            httpGet.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != httpGet) {
                    httpGet.releaseConnection();
                }
                if (null != httpClient) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 处理post请求.
     *
     * @param url    请求路径
     * @param params 参数
     * @return json
     * @author zhanglin
     */
    public static String post(String url, Map<String, String> params) {
        //结果
        CloseableHttpResponse response = null;
        HttpPost httpPost = null;
        CloseableHttpClient httpClient = null;
        String content = "";
        try {
            //实例化httpClient
            httpClient = HttpClients.createDefault();
            //实例化post方法
            httpPost = new HttpPost(url);
            //处理参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
            //提交的参数
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
            //将参数给post方法
            httpPost.setEntity(uefEntity);
            //执行post方法
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != httpPost) {
                    httpPost.releaseConnection();
                }
                if (null != httpClient) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    public static String postJson(String url, String parameters) {
        //处理参数
        String content = "";
        HttpPost httpPost = null;
        CloseableHttpClient httpClient = null;
        try {
            //实例化httpClient
            httpClient = HttpClients.createDefault();
			/*RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(60000)
					.setConnectTimeout(60000)
					.setConnectionRequestTimeout(60000)
					.build();
			httpClient = HttpClients.custom()
					.setDefaultRequestConfig(requestConfig)
					.build();*/
            //实例化post方法
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-type", "application/json; charset=UTF-8");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(new StringEntity(parameters, Consts.UTF_8));
            //设置超时时间
            //RequestTimeout（连接池获取到连接的超时时间）、ConnectTimeout（建立连接的超时）、SocketTimeout（获取数据的超时时间）
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                content = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            } else {
                content = "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
            try {
                if (null != httpPost) {
                    httpPost.releaseConnection();
                }
                if (null != httpClient) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
