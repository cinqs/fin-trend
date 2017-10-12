package cn.cinqs.fin.fintrend.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

@Service(value="htmlFetcher")
public class HtmlFetcher {
	
	private final String USER_AGENT = "Mozilla/5.0";
	protected final List<Map<String, String>> codeNameMapList = new ArrayList<>();

	@PostConstruct
	public void doStart() throws ClientProtocolException, IOException {
		
		
		
		String url = "http://resource.emagecompany.com/publiccompanies/listedcompanies_shanghai.html";
		String codeStart = "<TD class=xl2322179 style=\"HEIGHT: 14.25pt\" height=19 x:num>";
		String nameStart = "<TD class=xl2422179>";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent(), "gb2312"));

		String code = null;
		String name = null;
		
		String line = "";
		while ((line = rd.readLine()) != null) {
			
			if(line.startsWith(codeStart)) {
				code = line.substring(codeStart.length(), codeStart.length() + 6);
			}
			
			
			if(line.startsWith(nameStart)) {
				
				if(null != code) {
					name = line.replaceAll(
							nameStart 
								+ "|</TD></TR>" 
								+ "|</TBODY></TABLE></DIV><!-----------------------------><!--“从 EXCEL 发布网页”向导结束--><!-----------------------------></td>"
							, "");
					
					if(null != name) {
						Map<String, String> codeNameMap = new HashMap<>();
						codeNameMap.put("code", code);
						codeNameMap.put("name", name);
						codeNameMapList.add(codeNameMap);
					}
				}				
			}
		}
		
		System.out.println(codeNameMapList);
	}
	
	public List<Map<String, String>> getCodeNameMapList() {
		return codeNameMapList;
	}
}
