package com.shketai.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.service.CountService;
import com.shketai.service.LogService;

public class CountAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	private CountService countService;
	
	private LogService logService;
	
	private int result ;
	
	private String results;
	
	private Map<String,Integer> counts= new HashMap<String,Integer>();
	
	private Map<String,String> count= new HashMap<String,String>();
	private int classinfo_id;

	private int major_id;
	public void setCountService(CountService countService) {
		this.countService = countService;
	}
	
	/**
	 * 统计学生总人数
	 * @return
	 */
	public String CountStudent(){
		
		logService.addLog("数据汇总", "统计学生总人数");
		
		result = countService.countStudent();
		return SUCCESS;
	}
	
	/**
	 * 统计在读学生
	 * @return
	 */
	public String countStudentOfInfo(){
		
		logService.addLog("数据汇总", "统计在读学生");
		
		result = countService.countStudentOfInfo();
		return SUCCESS;
	}
	
	/**
	 * 统计科目总数
	 * @return
	 */
	public String CountSubject(){
		
		logService.addLog("数据汇总", "统计科目总数");
		
		result = countService.countSubject();
		return SUCCESS;
	}

	/**
	 * 统计班级总数
	 * @return
	 */
	public String CountClassinfo(){
		
		logService.addLog("数据汇总", "统计班级总数");
		
		result = countService.countSubjectClassinfo();
		return SUCCESS;
	}
	/**
	 * 汇总结果（学生、科目、班级）
	 * @return
	 */
	public String findAll(){
		
		logService.addLog("教务数据汇总", "汇总结果（学生、科目、班级）");
		
		Integer first = countService.countStudent();
		Integer second = countService.countSubject();
		Integer three = countService.countSubjectClassinfo();
		Integer four = countService.countStudentOfInfo();
		
		counts.put("student", first);
		counts.put("subject", second);
		counts.put("classinfo", three);
		counts.put("studentOf", four);
		return SUCCESS;
	}
	
	/**
	 * 男女比例
	 * @return
	 */
	public String countpercent1(){
		results = countService.countpercent1();
		return SUCCESS;
	}
	public String countpercent2(){
		results = countService.countpercent2();
		return SUCCESS;
	}
	public String countpercent3(){
		results = countService.countpercent3();
		return SUCCESS;
	}
	public String countpercent4(){
		results = countService.countpercent4();
		return SUCCESS;
	}
	public String countpercent5(){
		results = countService.countpercent5();
		return SUCCESS;
	}
	public String countpercent6(){
		results = countService.countpercent6();
		return SUCCESS;
	}
	public String countpercent7(){
		results = countService.countpercent7();
		return SUCCESS;
	}
	public String countpercent8(){
		results = countService.countpercent8();
		return SUCCESS;
	}
	public String countpercent(){
		logService.addLog("教务数据汇总", "男女比例");
		String first = countService.countpercent1();
		String second = countService.countpercent2();
		String three = countService.countpercent3();
		String four = countService.countpercent4();
		String five = countService.countpercent5();
		String six = countService.countpercent6();
		String seven = countService.countpercent7();
		String eight = countService.countpercent8();
		
		count.put("first", first);
		count.put("second", second);
		count.put("three", three);
		count.put("four", four);
		count.put("five", five);
		count.put("six", six);
		count.put("seven", seven);
		count.put("eight", eight);
		return SUCCESS;
	}
	
	public String CountStudentByClassInfoId(){
		result = countService.CountStudentByClassInfoId(classinfo_id);
		return SUCCESS;
	}
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Map<String, Integer> getCounts() {
		return counts;
	}

	public void setCounts(Map<String, Integer> counts) {
		this.counts = counts;
	}

	public int getClassinfo_id() {
		return classinfo_id;
	}

	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}


	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public int getMajor_id() {
		return major_id;
	}

	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}

	public Map<String, String> getCount() {
		return count;
	}

	public void setCount(Map<String, String> count) {
		this.count = count;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public CountService getCountService() {
		return countService;
	}

}
