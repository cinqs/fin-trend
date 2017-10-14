package cn.cinqs.fin.fintrend.scanner;

import java.util.List;
import java.util.Map;

public interface IFinancialSecuritiesFileScanner {

	/**
	 * manually scans a specified folder for a specified extension
	 * @param folderPath
	 * @return
	 */
	List<Map<String, ?>> scan(String folderPath, String extension);
	
	/**
	 * schedules a task to scan the configured folder, for a configured extension
	 * @return
	 */
	List<Map<String, ?>> scheduledScan();
	
	List<Map<String, Object>> parseXML();
	List<Map<String, Object>> parseCSV();
	List<Map<String, Object>> parseXls();
	List<Map<String, Object>> parseXlsx();
}
