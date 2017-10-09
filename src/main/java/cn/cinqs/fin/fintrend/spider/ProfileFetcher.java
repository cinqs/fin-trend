package cn.cinqs.fin.fintrend.spider;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProfileFetcher {

	protected final List<Map<String, String>> codeNameList;
	
	@Autowired
	public ProfileFetcher(HtmlFetcher htmlFetcher) {
		this.codeNameList = htmlFetcher.getCodeNameMapList();
	}

	@Scheduled(fixedRate = 5000, initialDelay = 5000)
	public void fetch() {
		for(Map<String, String> codeNameMap : codeNameList) {
			System.out.println(codeNameMap);
		}
	}
}
