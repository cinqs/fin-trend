package cn.cinqs.fin.fintrend.scanner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public abstract class FinancialSecuritiesFileScanner implements IFinancialSecuritiesFileScanner {
	
	protected String folderPath;
	protected SupportedFormat extension;
	
	protected final static String NO_SCHEDULING_TASK = "no scheduling task is enabled, skipping...";
	protected final static String YES_SCHEDULING_TASK = "scheduling task is enabled, continuing...";
	
	@Getter
	protected static final String DEFAULT_CRON = "0 0 0 1 2 *";
	
	protected boolean determineScheduling(String cron) {
		return !DEFAULT_CRON.equals(cron);
	}
	
	public enum SupportedFormat {
		CSV("csv"),
		XML("xml"),
		XLS("xls"),
		XLSX("xlsx");
		
		private String extension;
		SupportedFormat(String extension) {
			this.extension = extension;
		}
		
		public String getExtension() {
			return this.extension;
		}
	}
}
