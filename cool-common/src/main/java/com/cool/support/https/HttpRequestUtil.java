package com.cool.support.https;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class HttpRequestUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);
	//连接池
	private static PoolingHttpClientConnectionManager poolManager;
	//连接超时时间，默认6秒
    private static int socketTimeout = 6000;
    //传输超时时间，默认6秒
    private static int connectTimeout = 6000;
    //请求器的配置
    private static RequestConfig requestConfig;
	
	private static final Integer MAX_TOTAL = 200;
	private static final Integer MAX_PERROUTE = 20;
	
	/**
	 * 初始化连接池
	 */
	static {
		poolManager = new PoolingHttpClientConnectionManager();
		// 设置连接池的最大连接数
		poolManager.setMaxTotal(MAX_TOTAL);
		// 将每个路由基础的连接增加到
		poolManager.setDefaultMaxPerRoute(MAX_PERROUTE);
		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	}
	
	/**
	 * 获取HttpClient
	 * 
	 * @return
	 */
	public static CloseableHttpClient getHttpClient() {
		return HttpClients.custom().setConnectionManager(poolManager).build();
	}
	
	/**
	 * 获取SSLHttpClient
	 * 
	 * @return
	 */
	public static CloseableHttpClient getSSLHttpClient(SSLConnectionSocketFactory sslsf) {
		return HttpClients.custom().setConnectionManager(poolManager).setSSLSocketFactory(sslsf).build();
	}
	
	/**
	 * 无参的get请求 默认utf-8
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url,null,Consts.UTF_8.name());
	}
	/**
	 * 默认utf-8
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doGet(String url, Map<String,Object> param) {
		return doGet(url,param,Consts.UTF_8.name());
	}
	/**
	 * 
	 * @param url
	 * @param param
	 * @param decrypt 编码方式
	 * @return
	 */
	public static String doGet(String url,Map<String, Object> param, String decrypt) {
		CloseableHttpClient httpClient = HttpRequestUtil.getHttpClient();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key).toString());
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 执行请求
			response = httpClient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), decrypt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
	
	/**
	 * 无参数的post请求,默认utf-8编码
	 * @param url
	 * @return
	 */
	public static String doPost(String url) {
		return doPost(url,null,Consts.UTF_8.name(),Consts.UTF_8.name());
	}
	
	/**
	 * 带map参数的post请求
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doPost(String url, Map<String, Object> param, String encode, String decrypt) {
		CloseableHttpClient httpClient = HttpRequestUtil.getHttpClient();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			HttpPost post = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key).toString()));
				}
				// 模拟表单
				UrlEncodedFormEntity entity;
				try {
					entity = new UrlEncodedFormEntity(paramList, encode);
					post.setEntity(entity);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			// 执行http请求
			response = httpClient.execute(post);
			resultString = EntityUtils.toString(response.getEntity(), decrypt);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
	/**
	 * 默认json请求
	 * @param url
	 * @param json 
	 * @return
	 */
	public static String doPostJson(String url,String json) {
		return doPost(url,json,ContentType.APPLICATION_JSON, Consts.UTF_8.name());
	}
	/**
	 * 
	 * @param url
	 * @param json json格式字符串
	 * @param decrypt response编码
	 * @return
	 */
	public static String doPostJsonDecrypt(String url,String json,String decrypt) {
		return doPost(url,json,ContentType.APPLICATION_JSON, decrypt);
	}
	/**
	 * 默认text/xml请求 ISO_8859_1返回值编码
	 * @param url
	 * @param xml xml字符串
	 * @return
	 */
	public static String doPostXml(String url,String xml) {
		return doPost(url,xml,ContentType.TEXT_XML, Consts.ISO_8859_1.name());
	}
	/**
	 * 
	 * @param url
	 * @param xml xml字符串
	 * @param decrypt response编码解码
	 * @return
	 */
	public static String doPostXmlDecrypt(String url,String xml,String decrypt) {
		return doPost(url,xml,ContentType.TEXT_XML, decrypt);
	}

	/**
	 * 
	 * @param url
	 * @param requestContext 字符串
	 * @param contentType 类型
	 * @param decrypt response编码解码
	 * @return
	 */
	public static String doPost(String url, String requestContext, ContentType contentType, String decrypt) {
		CloseableHttpClient httpClient = HttpRequestUtil.getHttpClient();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			HttpPost post = new HttpPost(url);
			// 创建请求头
			// Header header = new BasicHeader("", "");
			// post.addHeader(header);
			// 创建请求内容
			StringEntity entity = new StringEntity(requestContext, contentType);
			post.setEntity(entity);
			// 执行请求
			response = httpClient.execute(post);
			// 转换成String
			resultString = EntityUtils.toString(response.getEntity(), decrypt);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
	
	/**
     * 通过Https往API post xml数据
     * @param url    API地址
     * @param xmlObj 要提交的XML数据对象
     * @return API回包的实际数据
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static String sendPost( String url, Object xmlObj) throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);
        //设置请求器的配置
        httpPost.setConfig(requestConfig);
        try {
            CloseableHttpClient httpClient = HttpRequestUtil.getHttpClient();//使用连接池
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)");
        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException");
        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException");
        } catch (Exception e) {
            logger.error("http get throw Exception",e);
        } finally {
            httpPost.abort();
        }
        return result;
    }

}
