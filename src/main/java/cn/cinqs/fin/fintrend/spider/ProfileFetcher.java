package cn.cinqs.fin.fintrend.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Service
public class ProfileFetcher {

	protected final List<Map<String, String>> codeNameList;
	
	@Autowired
	public ProfileFetcher(HtmlFetcher htmlFetcher) {
		this.codeNameList = htmlFetcher.getCodeNameMapList();
	}

	//@Scheduled(fixedRate = 50000000, initialDelay = 5000)
	@PostConstruct
	public void fetch() throws ClientProtocolException, IOException, URISyntaxException {
		for(Map<String, String> codeNameMap : codeNameList) {
			
			System.out.println(codeNameMap);
			
			String code = codeNameMap.get("code");
			
			OkHttpClient client = new OkHttpClient();

			MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
			RequestBody body = RequestBody.create(mediaType, 
					"------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
					+ "name=\"market\"\r\n\r\nsh\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
					+ "name=\"type\"\r\n\r\nhq\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
					+ "name=\"code\"\r\n\r\n" + code + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
					+ "name=\"orgid\"\r\n\r\n9900009589\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
					+ "name=\"minYear\"\r\n\r\n2015\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
					+ "name=\"maxYear\"\r\n\r\n2017\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
			Request request = new Request.Builder()
			  .url("http://www.cninfo.com.cn/cninfo-new/data/download")
			  .post(body)
			  .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
			  .addHeader("enctype", "multipart/form-data")
			  .addHeader("cache-control", "no-cache")
			  .addHeader("postman-token", "4a03302a-42f0-820c-fa2a-a6b18ab4904c")
			  .build();

			Response response = client.newCall(request).execute();
			
			System.out.println(response.code());
			ResponseBody rb = response.body();
			
			
			
			//Reader reader = rb.charStream();
			InputStream is = rb.byteStream();
			
			/*BasicCookieStore cookieStore = new BasicCookieStore();
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore)
	                .build();
			
			HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI("http://www.cninfo.com.cn/cninfo-new/data/download"))
                    .addHeader("User-Agent", "Mozilla/5.0")
                    .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .addHeader("enctype", "multipart/form-data")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "c9bc7a65-330c-00a8-d31f-86c1ba58aa03")
                    .addParameter("market", "sz")
                    .addParameter("type", "hq")
                    .addParameter("code", code)
                    .addParameter("orgid", "9900009589")
                    .addParameter("minYear", "2015")
                    .addParameter("maxYear", "2017")
                    .build();
            CloseableHttpResponse response = httpclient.execute(login);
            
            System.out.println(response.getStatusLine());*/
            
			
			/*HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("http://www.cninfo.com.cn/cninfo-new/data/download");

			// add request header
			request.addHeader("User-Agent", "Mozilla/5.0");
			request.addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
			request.addHeader("enctype", "multipart/form-data");
			request.addHeader("cache-control", "no-cache");
			request.addHeader("postman-token", "c9bc7a65-330c-00a8-d31f-86c1ba58aa03");
			
//			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//            nameValuePairs.add(new BasicNameValuePair("market", "sz"));
//            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response = client.execute(request);
			
			System.out.println(response.getStatusLine());*/

            //BufferedReader br = new BufferedReader(reader);
            
            /*try(BufferedReader br = new BufferedReader(isr)) {
            	String line = null;
            	
            	while((line = br.readLine()) != null) {
            		System.out.println(line);
            	}
            }*/
            String filePath = "C:\\Users\\SOONG\\Desktop\\fin-trend\\" + code + ".zip";
			byte[] bbuff = new byte[4096];
			int length = 0;
            try(OutputStream os = new FileOutputStream(filePath)) {
            	//char[] cbuf = new char[1024];
            	while((length = is.read(bbuff)) > 0) {
            		byte[] rbytes = new byte[length];
            		
            		System.arraycopy(bbuff, 0, rbytes, 0, length);
            		
            		os.write(rbytes);
            	}
            	
            	os.flush();
            	os.close();
            } finally {
            	is.close();
            }
            String dest = "C:\\Users\\SOONG\\Desktop\\fin-trend\\" + code + "\\";
            try {
				ZipFile zipFile = new ZipFile(filePath);
				
				
				zipFile.extractAll(dest);
			} catch (ZipException e) {
				e.printStackTrace();
				File file = new File(dest);
				if(file.isDirectory() && file.exists()) {
					file.delete();
				}
			}
            
		}
	}
}
