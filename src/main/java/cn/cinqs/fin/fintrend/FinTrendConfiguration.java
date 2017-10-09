package cn.cinqs.fin.fintrend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.cinqs.fin.fintrend.spider.HtmlFetcher;
import cn.cinqs.fin.fintrend.spider.ProfileFetcher;

@Configuration
public class FinTrendConfiguration {

	/*@Bean
	public HtmlFetcher htmlFetcher() {
		return new HtmlFetcher();
	}
	
	@Bean
	public ProfileFetcher profileFetcher(HtmlFetcher hf) {
		return new ProfileFetcher(hf.getCodeNameMapList());
	}*/
}
