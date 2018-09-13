package com.atlp.rsgl.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * HttpClient工具类 使用 HttpClient 需要以下 6 个步骤： 1. 创建 HttpClient 的实例 2. 创建某种连接方法的实例 3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例 4. 读 response 5. 释放连接。无论执行方法是否成功，都必须释放连接 6. 对得到后的内容进行处理
 *
 * @author CTC
 * @date 2016-02-03 12:16:51
 */
public class HttpClientUtil {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 发送post请求--基础方法
     *
     * @param url                      请求地址
     * @param inEntity                 请求参数
     * @param decodeCharset            解码字符集,默认UTF-8
     * @param socketTimeout            响应超时
     * @param connectTimeout           连接超时
     * @param connectionRequestTimeout 请求超时
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> post(String url, HttpEntity inEntity, Map<String, String> headers, String decodeCharset, int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        // 创建 HttpClient 的实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建post连接方法的实例
            HttpPost httpPost = new HttpPost(url);
            // HttpGet
            // Request 配置
            RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT) //
                    .setSocketTimeout(socketTimeout) // 响应超时
                    .setConnectTimeout(connectTimeout) // 连接超时
                    .setConnectionRequestTimeout(connectionRequestTimeout) // 请求超时
                    .build();
            httpPost.setConfig(requestConfig);
            // 设置参数
            httpPost.setEntity(inEntity);
            // 设置header
            for (String headerKey : headers.keySet()) {
                httpPost.setHeader(headerKey, headers.get(headerKey));
            }
            // 执行请求
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                // 获取返回代码
                int statusCode = response.getStatusLine().getStatusCode();
                retMap.put("statusCode", statusCode);
                // 获取会话Cookie
                Header[] header_Cookie = response.getHeaders("Set-Cookie");
                if (header_Cookie.length > 0) {
                    String cookie = header_Cookie[0].getValue();
                    // System.out.println("Set-Cookie===" + cookie);
                    retMap.put("cookie", cookie);
                }
                // 获取返回值
                HttpEntity outEntity = response.getEntity();
                if (outEntity != null) {
                    String responseContent = EntityUtils.toString(outEntity, decodeCharset == null ? "UTF-8" : decodeCharset);
                    retMap.put("responseContent", responseContent);
                }
                // 销毁HttpEntity
                EntityUtils.consume(outEntity);
            } finally {
                // 关闭请求
                response.close();
            }
        } catch (Exception e) {
            log.error("与[" + url + "]通信过程中发生异常,堆栈信息为：", e);
            e.printStackTrace();
        } finally {
            // 关闭实例
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error("与[" + url + "]通信,关闭实例发生异常,堆栈信息为：", e);
                e.printStackTrace();
            }
        }
        return retMap;
    }

    /**
     * 发送post请求-Form格式
     *
     * @param url                      请求地址
     * @param pmap                     请求参数
     * @param encodeCharset            编码字符集,默认UTF-8
     * @param decodeCharset            解码字符集,默认UTF-8
     * @param socketTimeout            响应超时
     * @param connectTimeout           连接超时
     * @param connectionRequestTimeout 请求超时
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> postForm(String url, Map<String, Object> pmap, Map<String, String> headers, String encodeCharset, String decodeCharset, int socketTimeout, int connectTimeout,
                                               int connectionRequestTimeout) {
        try {
            // 入参
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (String key : pmap.keySet()) {
                nvps.add(new BasicNameValuePair(key, pmap.get(key).toString()));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, encodeCharset == null ? "UTF-8" : encodeCharset);
            // 设置header
            // Map<String, String> headers = new HashMap<String, String>();
            // headers.put("Content-Type", "text/xml; charset=UTF-8");
            // 调用
            return HttpClientUtil.post(url, formEntity, headers, decodeCharset, socketTimeout, connectTimeout, connectionRequestTimeout);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送post请求-Form格式
     *
     * @param url  请求地址
     * @param pmap 请求参数
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> postForm(String url, Map<String, Object> pmap, Map<String, String> headers) {
        return HttpClientUtil.postForm(url, pmap, headers, "utf-8", "utf-8", 60000, 60000, 60000);
    }

    /**
     * 发送post请求-JSON格式
     *
     * @param url                      请求地址
     * @param pmap                     请求参数
     * @param encodeCharset            编码字符集,默认UTF-8
     * @param decodeCharset            解码字符集,默认UTF-8
     * @param socketTimeout            响应超时
     * @param connectTimeout           连接超时
     * @param connectionRequestTimeout 请求超时
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> postJson(String url, Map<String, Object> pmap, Map<String, String> headers, String encodeCharset, String decodeCharset, int socketTimeout, int connectTimeout,
                                               int connectionRequestTimeout) {
        try {
            // 入参
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(pmap), "utf-8");
            stringEntity.setContentType("application/json");
            stringEntity.setContentEncoding(encodeCharset);
            // 设置header
            // Map<String, String> headers = new HashMap<String, String>();
            // headers.put("Content-Type", "application/json; charset=UTF-8");
            // 调用
            return HttpClientUtil.post(url, stringEntity, headers, decodeCharset, socketTimeout, connectTimeout, connectionRequestTimeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送post请求-JSON格式
     *
     * @param url  请求地址
     * @param pmap 请求参数
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> postJson(String url, Map<String, Object> pmap, Map<String, String> headers) {
        return HttpClientUtil.postJson(url, pmap, headers, "utf-8", "utf-8", 60000, 60000, 60000);
    }

    /**
     * 发送post请求-SOAP格式
     *
     * @param url                      请求地址
     * @param namespace                命名空间
     * @param methodName               调用方法名称
     * @param paramsXml                参数xml格式
     * @param encodeCharset            编码字符集,默认UTF-8
     * @param decodeCharset            解码字符集,默认UTF-8
     * @param socketTimeout            响应超时
     * @param connectTimeout           连接超时
     * @param connectionRequestTimeout 请求超时
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> postSoap(String url, String namespace, String methodName, String paramsXml, String contentType, String encodeCharset, String decodeCharset, int socketTimeout,
                                               int connectTimeout, int connectionRequestTimeout) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            // 入参
            StringBuilder soapRequestData = new StringBuilder();
            // soapRequestData.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            soapRequestData.append("<soap:Envelope ");
            soapRequestData.append(" 	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
            soapRequestData.append(" 	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ");
            soapRequestData.append("	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" ");
            soapRequestData.append("	xmlns:sam=\"" + namespace + "\" "); // 命名空间，前缀,这一串由服务端提供
            soapRequestData.append(">");
            soapRequestData.append("<soap:Header/>");
            soapRequestData.append("<soap:Body>");
            soapRequestData.append("<sam:" + methodName + ">"); // 调用方法名
            soapRequestData.append(paramsXml); // 拼接参数
            soapRequestData.append("</sam:" + methodName + ">");
            soapRequestData.append("</soap:Body>");
            soapRequestData.append("</soap:Envelope>");
            // System.out.println(soapRequestData.toString());
            StringEntity stringEntity = new StringEntity(soapRequestData.toString(), encodeCharset);
            // 设置header
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", contentType);
            // String SOAPAction = namespace + method;
            // headers.put("SOAPAction", soapAction);
            // 调用
            retMap = HttpClientUtil.post(url, stringEntity, headers, decodeCharset, socketTimeout, connectTimeout, connectionRequestTimeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }

    /**
     * 发送post请求-SOAP格式
     *
     * @param url        请求地址
     * @param namespace  命名空间
     * @param methodName 调用方法名称
     * @param paramsXml  参数xml格式
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> postSoap(String url, String namespace, String methodName, String paramsXml) {
        return HttpClientUtil.postSoap(url, namespace, methodName, paramsXml, "text/xml; charset=UTF-8", "utf-8", "utf-8", 60000, 60000, 60000);
    }

    /**
     * 描述：HttpClient请求--POST方式--基础方法
     *
     * @param url         请求地址
     * @param requestData 请求参数
     * @return Map格式（status：返回代码，responseData：返回数据）
     * @throws Exception
     * @date 2016-01-05 18:36:50
     * @author CTC
     */
    public static Map<String, Object> postTest(String url, String requestData, String contentType, String soapAction) throws Exception {
        Map<String, Object> retMap = new HashMap<String, Object>();
        // 创建 HttpClient 的实例
        // DefaultHttpClient httpclient = new DefaultHttpClient();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建post连接方法的实例
            HttpPost httpPost = new HttpPost(url);
            // Request 配置
            RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT) //
                    .setSocketTimeout(5000) //
                    .setConnectTimeout(5000) // 连接超时
                    .setConnectionRequestTimeout(5000) // 请求超时
                    .build();
            httpPost.setConfig(requestConfig);

            // System.out.println(httpPost.getAllHeaders());
            // System.out.println(httpPost.getFirstHeader("Accept-Encoding"));

            // 设置参数
            Map<String, String> param = new LinkedHashMap<String, String>();
            param.put("channel_id", "8a828b454d89c27f014d9a19490a0020");
//            param.put("username", MD5Util.parseMD5Code("shqdxxywfgs"));
//            param.put("password", MD5Util.parseMD5Code("shqdmm123456789"));
//            param.put("client_ip", MD5Util.parseMD5Code("127.0.0.1"));
            param.put("service_num", ""); // 号码
            param.put("order_id", "");
            param.put("qssj", "2016-01-01");
            param.put("jssj", "2016-01-31");

            // List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // nvps.add(new BasicNameValuePair("channel_id", "8a828b454d89c27f014d9a19490a0020"));
            // nvps.add(new BasicNameValuePair("username", "shqdxxywfgs"));
            // nvps.add(new BasicNameValuePair("password", "shqdmm123456789"));
            // nvps.add(new BasicNameValuePair("client_ip", "3"));
            // nvps.add(new BasicNameValuePair("service_num", ""));
            // nvps.add(new BasicNameValuePair("order_id", ""));
            // nvps.add(new BasicNameValuePair("qssj", "2016-01-01"));
            // nvps.add(new BasicNameValuePair("jssj", "2016-01-31"));
            // httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            StringBuilder soapRequestData = new StringBuilder();
            // soapRequestData.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            soapRequestData.append("<soap:Envelope ");
            soapRequestData.append(" 	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
            soapRequestData.append(" 	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ");
            soapRequestData.append("	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" ");
            soapRequestData.append("	xmlns:sam=\"http://common.empower.aitlamp.com/\" "); // 前缀,这一串由服务端提供
            soapRequestData.append(">");
            soapRequestData.append("<soap:Header/>");
            soapRequestData.append("<soap:Body>");
            soapRequestData.append("<sam:sayHello>"); // “testRtJson”调用方法名
            soapRequestData.append("<json>123</json>");
            soapRequestData.append("</sam:sayHello>");
            soapRequestData.append("</soap:Body>");
            soapRequestData.append("</soap:Envelope>");
            // System.out.println(soapRequestData.toString());
            StringEntity entiy = new StringEntity(soapRequestData.toString(), "UTF-8");
            httpPost.setEntity(entiy);

            // 设置header
            httpPost.setHeader("Content-Type", "text/xml; charset=UTF-8");
            // httpPost.setHeader("SOAPAction", "sayHello");

            // 执行请求
            // log.info("1");
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            // log.info("2");
            try {
                // System.out.println(response2.getStatusLine());
                // System.out.println(EntityUtils.toString(response2.getEntity()));

                HttpEntity entity2 = response2.getEntity();

                if (entity2 != null) {
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + EntityUtils.toString(entity2, "UTF-8"));
                    System.out.println("--------------------------------------");
                }

                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                // 关闭请求
                response2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭实例
            httpclient.close();
        }
        return retMap;
    }

    /***************************** get方法 *****************************/

    /**
     * 发送post请求--基础方法
     *
     * @param url                      请求地址
     * @param headers                  请求参数
     * @param decodeCharset            解码字符集,默认UTF-8
     * @param socketTimeout            响应超时
     * @param connectTimeout           连接超时
     * @param connectionRequestTimeout 请求超时
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> get(String url, Map<String, String> headers, String decodeCharset, int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        // 创建 HttpClient 的实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建post连接方法的实例
            HttpGet httpGet = new HttpGet(url);
            // Request 配置
            RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT) //
                    .setSocketTimeout(socketTimeout) // 响应超时
                    .setConnectTimeout(connectTimeout) // 连接超时
                    .setConnectionRequestTimeout(connectionRequestTimeout) // 请求超时
                    .build();
            httpGet.setConfig(requestConfig);
            // 设置header
            if (!StringUtils.isEmpty(headers)) {
                for (String headerKey : headers.keySet()) {
                    httpGet.setHeader(headerKey, headers.get(headerKey));
                }
            }
            // 执行请求
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                // 获取返回代码
                int statusCode = response.getStatusLine().getStatusCode();
                retMap.put("statusCode", statusCode);
                // 获取会话Cookie
                Header[] header_Cookie = response.getHeaders("Set-Cookie");
                if (header_Cookie.length > 0) {
                    String cookie = header_Cookie[0].getValue();
                    // System.out.println("Set-Cookie===" + cookie);
                    retMap.put("cookie", cookie);
                }
                // 获取返回值
                HttpEntity outEntity = response.getEntity();
                if (outEntity != null) {
                    String responseContent = EntityUtils.toString(outEntity, decodeCharset == null ? "UTF-8" : decodeCharset);
                    retMap.put("responseContent", responseContent);
                }
                // 销毁HttpEntity
                EntityUtils.consume(outEntity);
            } finally {
                // 关闭请求
                response.close();
            }
        } catch (Exception e) {
            log.error("与[" + url + "]通信过程中发生异常,堆栈信息为：", e);
            e.printStackTrace();
        } finally {
            // 关闭实例
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error("与[" + url + "]通信,关闭实例发生异常,堆栈信息为：", e);
                e.printStackTrace();
            }
        }
        return retMap;
    }

    /**
     * 发送get请求
     *
     * @param url 请求地址
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> get(String url, Map<String, String> headers) {
        return HttpClientUtil.get(url, headers, "utf-8", 60000, 60000, 60000);
    }

    /**
     * 发送get请求
     *
     * @param url 请求地址
     * @return responseContent 远程主机响应正文
     */
    public static Map<String, Object> get(String url) {
        return HttpClientUtil.get(url, null, "utf-8", 60000, 60000, 60000);
    }

    /**
     * 发送get请求
     *
     * @param url 请求地址
     * @return responseContent 远程主机响应正文
     */
    public static String getRequest(String url, Map<String, String> headers) {
        return HttpClientUtil.get(url, headers, "utf-8", 60000, 60000, 60000).get("responseContent").toString();
    }

    /**
     * 发送get请求
     *
     * @param url 请求地址
     * @return responseContent 远程主机响应正文
     */
    public static String getRequest(String url) {
        return HttpClientUtil.get(url, null, "utf-8", 60000, 60000, 60000).get("responseContent").toString();
    }

    public static void main(String[] args) {
        // webservice测试
        String url2 = "http://127.0.0.1:8080/Empower/services/EmpowerWebService?wsdl";
        String requestData2 = "";
        String contentType2 = "";
        String soapAction2 = "";
        try {
            HttpClientUtil.postTest(url2, requestData2, contentType2, soapAction2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
