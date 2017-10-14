package cn.cinqs.fin.fintrend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.cinqs.fin.fintrend.scanner.FinancialSecuritiesFileScanner;
import cn.cinqs.fin.fintrend.scanner.IFinancialSecuritiesFileScanner;
import cn.cinqs.fin.fintrend.scanner.SHAFileScanner;
import cn.cinqs.fin.fintrend.scanner.SZAFileScanner;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FinTrendConfiguration {
	
	@Value("${SHA.file.folder}") private String shaFileFolder;
	@Value("${SHA.file.extension}") private String shaFileExtension;
	
	@Value("${SZA.file.folder}") private String szaFileFolder;
	@Value("${SZA.file.extension}") private String szaFileExtension;
	
	@Bean(value="shaFileScanner")
	public IFinancialSecuritiesFileScanner getShaFileScanner() {		
		
		return new SHAFileScanner(shaFileFolder, 
				FinancialSecuritiesFileScanner.SupportedFormat.valueOf(shaFileExtension.toUpperCase())
				);		
	}
	
	@Bean(value="szaFileScanner")
	public IFinancialSecuritiesFileScanner getSzaFileScanner() {
		
		return new SZAFileScanner(szaFileFolder, 
				FinancialSecuritiesFileScanner.SupportedFormat.valueOf(szaFileExtension.toUpperCase())
				);
	}
}
