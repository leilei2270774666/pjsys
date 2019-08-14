package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Dictionary;
import com.shketai.service.DictionaryService;
import com.shketai.service.LogService;
import com.shketai.util.PingYinUtil;

/**
 *  词典
 */
public class DictionaryAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DictionaryService dictionaryService;

	private Integer flag;
	
	private LogService logService;
	
	/**
	  * 更新数据库 首字母拼音
	 * 
	 */
	public String updateAllDictionary() {
		logService.addLog("学生考勤管理", "更新学生(词典)");
		dictionaryService.updateAllDictionary();
		
		flag = 1;
		
		return SUCCESS;
	}
	
 
	
	/**
	 * @return
	 */
	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Integer getFlag() {
		return flag;
	}



	public void setFlag(Integer flag) {
		this.flag = flag;
	}



	public LogService getLogService() {
		return logService;
	}



	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
}
