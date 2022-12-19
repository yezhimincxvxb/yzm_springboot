package com.yzm.helper.http;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Yzm
 * @desc 发送Http请求
 * @date 22/08/30
 */
public class HttpRequestUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestUtil.class);

    private static final String CHARSET = "UTF-8";

    /**
     * 将Map的key<-->value拼接成字符串
     * key1=value1&key2=value2
     * connector：&
     */
    public static String joinMapValue(Map<String, Object> map, char connector) {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            buffer.append(entry.getKey());
            buffer.append('=');
            if (entry.getValue() != null) {
                buffer.append(entry.getValue());
            }
            buffer.append(connector);
        }
        return buffer.toString().substring(0, buffer.length() - 1);
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param requestUrl 发送请求的URL
     * @param charset    编码格式
     * @param params     请求参数
     * @return 远程资源的响应结果
     */
    public static String sendGet(String requestUrl, String params, String charset) {
        if (!StringUtils.hasLength(requestUrl)) throw new IllegalArgumentException("Request Url Empty");
        if (StringUtils.hasLength(params)) requestUrl += "?" + params;
        if (!StringUtils.hasLength(charset)) charset = CHARSET;

        String result = "";
        try {
            URL url = new URL(requestUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求属性
            connection.setRequestMethod("GET");// 设置请求方式为post,默认GET请求
            connection.setConnectTimeout(5000);// 连接主机的超时时间
            connection.setReadTimeout(5000);// 从主机读取数据的超时时间
            // 设置通用属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            connection.setRequestProperty("Charset", "UTF-8");
            // 建立TCP连接
            connection.connect();

            result = getResult(connection, charset);
        } catch (Exception e) {
            log.info("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向指定URL发送POST方法的请求
     *
     * @param requestUrl 发送请求的URL
     * @param params     请求参数,请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 远程资源的响应结果
     */
    public static String sendPost(String requestUrl, String params) {
        return sendPost(requestUrl, params, null);
    }

    public static String sendPost(String requestUrl, String params, String charset) {
        return post(requestUrl, params, charset, false);
    }

    /**
     * 向指定URL发送POST方法的请求
     *
     * @param requestUrl 发送请求的URL
     * @param params     请求参数为json格式
     * @return 远程资源的响应结果
     */
    public static String jsonPost(String requestUrl, String params) {
        return jsonPost(requestUrl, params, null);
    }

    public static String jsonPost(String requestUrl, String params, String charset) {
        return post(requestUrl, params, charset, true);
    }

    public static String post(String requestUrl, String params, String charset, boolean isJson) {
        if (!StringUtils.hasText(requestUrl)) throw new IllegalArgumentException("Request Url Empty");
        if (!StringUtils.hasText(charset)) charset = CHARSET;

        String result = "";
        try {
            URL url = new URL(requestUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求属性
            connection.setRequestMethod("POST");// 设置请求方式为post
            connection.setConnectTimeout(5000);// 连接主机的超时时间
            connection.setReadTimeout(5000);// 从主机读取数据的超时时间
            connection.setDoOutput(true);// 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,默认情况下是false
            connection.setDoInput(true); // 设置是否从connection读入，默认情况下是true;
            // 设置请求通用属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            connection.setRequestProperty("Charset", "UTF-8");
            if (isJson) {
                connection.setRequestProperty("Content-Type", "application/json");
            } else {
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            // 建立TCP连接
            connection.connect();

            // 获取URLConnection对象对应的输出流
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            out.close();

            result = getResult(connection, charset);
        } catch (Exception e) {
            log.info("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }

    private static String getResult(HttpURLConnection connection, String charset) throws IOException {
        StringBuffer result = new StringBuffer();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
        }
        // 断开与远程地址url的连接
        connection.disconnect();
        return result.toString();
    }

    /**
     * 以post方式调用第三方接口,以form-data 形式  发送 MultipartFile 文件数据
     *
     * @param url           post请求url
     * @param fileParamName 文件参数名称
     * @param multipartFile 文件
     * @param paramMap      表单里其他参数
     * @return
     */
    public static String doPostFormData(String url, String fileParamName, MultipartFile multipartFile, Map<String, String> paramMap) {
        // 创建Http实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 创建HttpPost实例
            HttpPost httpPost = new HttpPost(url);

            // 请求参数配置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(60000)
                    .setConnectTimeout(60000)
                    .setConnectionRequestTimeout(10000)
                    .build();
            httpPost.setConfig(requestConfig);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(StandardCharsets.UTF_8);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 文件流
            String fileName = multipartFile.getOriginalFilename();
            builder.addBinaryBody(fileParamName, multipartFile.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);

            //表单中其他参数
            if (paramMap != null) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    builder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.create("text/plain", Consts.UTF_8)));
                }
            }

            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);

            // 执行提交
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用HttpPost失败！" + e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error("关闭HttpPost连接失败！");
                }
            }
        }
        return null;
    }

    public static void download(String url, HttpServletResponse response){
        try {
            URL urls = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(50 * 1000);
            conn.setReadTimeout(50 * 1000);
            InputStream inStream = conn.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048 * 4];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            inStream.close();
            byte[] data = outStream.toByteArray();
            response.setContentType("application/pdf");
            OutputStream os = response.getOutputStream();
            os.write(data);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
