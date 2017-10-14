package cn.cinqs.fin.fintrend.scanner;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bytedeco.javacpp.freenect2.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SZAFileScanner extends FinancialSecuritiesFileScanner {
	
	@Value("${SZA.scan.schedule.cron:" + FinancialSecuritiesFileScanner.DEFAULT_CRON + "}") private String cron;

	public SZAFileScanner(String folderPath, SupportedFormat extension) {
		super(folderPath, extension);
	}

	@Override
	public List<Map<String, ?>> scan(String folderPath, String extension) {
		return null;
	}

	@Override
	@Scheduled(cron = "${SZA.scan.schedule.cron:" + FinancialSecuritiesFileScanner.DEFAULT_CRON + "}")
	public List<Map<String, ?>> scheduledScan() {
		if(this.determineScheduling(cron)) {
			log.info(FinancialSecuritiesFileScanner.YES_SCHEDULING_TASK);
		} else {
			log.info(FinancialSecuritiesFileScanner.NO_SCHEDULING_TASK);
		}
		
		return Collections.emptyList();
	}

	@Override
	public List<Map<String, Object>> parseXML() {
		return null;
	}

	@Override
	public List<Map<String, Object>> parseCSV() {
		return null;
	}

	@Override
	public List<Map<String, Object>> parseXls() {
		return null;
	}

	@Override
	public List<Map<String, Object>> parseXlsx() {
		return null;
	}

}
