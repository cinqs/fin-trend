package cn.cinqs.fin.fintrend.scanner;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SHAFileScanner extends FinancialSecuritiesFileScanner {
	
	@Value("${SHA.scan.schedule.cron:" + FinancialSecuritiesFileScanner.DEFAULT_CRON + "}") private String cron;

	public SHAFileScanner(String folderPath, SupportedFormat extension) {
		super(folderPath, extension);
	}

	@Override
	public List<Map<String, ?>> scan(String folderPath, String extension) {
		return null;
	}

	@Override
	@Scheduled(cron = "${SHA.scan.schedule.cron:" + FinancialSecuritiesFileScanner.DEFAULT_CRON + "}")
	public List<Map<String, ?>> scheduledScan() {
		if(this.determineScheduling(cron)) {
			log.info(FinancialSecuritiesFileScanner.YES_SCHEDULING_TASK);
		} else {
			log.warn(FinancialSecuritiesFileScanner.NO_SCHEDULING_TASK);
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
