package com.shketai.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.HeadMaster;
import com.shketai.service.HeadMasterService;
import com.shketai.service.InfoService;
import com.shketai.service.LogService;

public class DownloadAllStudentInfoAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private InfoService infoService;
	private HeadMasterService headMasterService;
	
	private  List<Object[]> info;
	private int classinfo_id;
	private InputStream inputStream;//要下载文件的字节输入 流
	private String filename;//要下载文件的名字
	private String contentType;//要下载文件的类型
	private LogService logService;
	
	private String randomDir = UUID.randomUUID().toString();//表示download目录中随机目录，每次下载都不一样
	@Override
	public String execute() throws Exception {
		info = infoService.findAllBySubject();
		//生成excel
		generateExcel();
		return SUCCESS;
	}
	
	public void generateExcel() throws Exception{
		//创建excel book
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("当前班级学生信息列表");  
		//生成表头
		createHeader(wb,sheet);
	    //生成excel中的数据
	    writeDataExcel(wb,sheet,info);
	}
	
	/**
	 * 生成excel表头
	 * @param sheet
	 */
	private void createHeader(XSSFWorkbook wb,XSSFSheet sheet) {
		XSSFRow  row = sheet.createRow(0);
		XSSFCellStyle  style = wb.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("科目");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("班级");  
	    cell.setCellStyle(style); 
	    
	    cell = row.createCell(2);
		cell.setCellValue("班主任");  
	    cell.setCellStyle(style); 
	    
		cell = row.createCell(3);  
	    cell.setCellValue("学生姓名");  
	    cell.setCellStyle(style);
	    
	    cell = row.createCell(4);
		cell.setCellValue("性别");  
	    cell.setCellStyle(style); 
	    
		cell = row.createCell(5);  
	    cell.setCellValue("身份证号");  
	    cell.setCellStyle(style);
	    
	    cell = row.createCell(6);  
	    cell.setCellValue("出生年月");  
	    cell.setCellStyle(style);
	    
	    cell = row.createCell(7);  
	    cell.setCellValue("家庭地址");  
	    cell.setCellStyle(style);
	    
	    cell = row.createCell(8);
		cell.setCellValue("邮编");  
	    cell.setCellStyle(style); 
	    
		cell = row.createCell(9);  
	    cell.setCellValue("家长姓名");  
	    cell.setCellStyle(style);
	    
	    cell = row.createCell(10);  
	    cell.setCellValue("备用联系人姓名");  
	    cell.setCellStyle(style);
	    
	    cell = row.createCell(11);  
	    cell.setCellValue("家长手机号");  
	    cell.setCellStyle(style);
	    
	    cell = row.createCell(12);  
	    cell.setCellValue("备用联系人电话");  
	    cell.setCellStyle(style);
	    
	    cell = row.createCell(13);
		cell.setCellValue("工作单位");  
	    cell.setCellStyle(style); 
	    
	    cell = row.createCell(14);
		cell.setCellValue("学校名称");  
	    cell.setCellStyle(style); 
	    
	    
	}
	/**
	 * 把数据写到excel表格中
	 * @param wb
	 * @param sheet
	 * @param works
	 * @throws Exception
	 */
	private void writeDataExcel(XSSFWorkbook wb,XSSFSheet sheet, List<Object[]> info) throws Exception {
		logService.addLog("教务数据汇总", "导出所有在读学生");
		CreationHelper createHelper = wb.getCreationHelper();
		XSSFCellStyle hlinkstyle = wb.createCellStyle();
		XSSFFont hlinkfont = wb.createFont();
		hlinkfont.setUnderline(XSSFFont.U_SINGLE);
		hlinkfont.setColor(HSSFColor.BLUE.index);
	    hlinkstyle.setFont(hlinkfont);
	    // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		XSSFRow row = null;
		XSSFCell  cell = null;
		
		if(info == null) {
			info = new ArrayList<Object[]>();
		}
		
		for (int i = 0; i < info.size(); i++)  {
			Object[] array =  info.get(i);  
			
		    row = sheet.createRow(i + 1);  
		    
		    
		    cell = row.createCell(0);
		    cell.setCellValue(array[0].toString());
		    
		    cell = row.createCell(1);
		    cell.setCellValue(array[1].toString());
		    
		    cell = row.createCell(2);
		    
		    int classinfo_id = (int) array[14];
		    HeadMaster headMaster = headMasterService.sqlQueryclassinfo_id(classinfo_id);
		    if(headMaster != null) {
		    	cell.setCellValue(headMaster.getName());
		    }
		    
		    
		    cell = row.createCell(3);
		    cell.setCellValue(array[2].toString());
		    
		    cell = row.createCell(4);
		    cell.setCellValue(array[3].toString());
		    
		    cell = row.createCell(5);
		    cell.setCellValue(array[4].toString());
		    
		    cell = row.createCell(6);
		    cell.setCellValue(array[5].toString());
		    
		    cell = row.createCell(7);
		    cell.setCellValue(array[6].toString());
		    
		    cell = row.createCell(8);
		    cell.setCellValue(array[7].toString());
		    
		    cell = row.createCell(9);
		    cell.setCellValue(array[8].toString());
		    
		    cell = row.createCell(10);
		    cell.setCellValue(array[9].toString());
		    
		    cell = row.createCell(11);
		    cell.setCellValue(array[10].toString());
		    
		    cell = row.createCell(12);
		    cell.setCellValue(array[11].toString());
		    
		    cell = row.createCell(13);
		    cell.setCellValue(array[12].toString());
		    
		    cell = row.createCell(14);
		    cell.setCellValue(array[13].toString());
		    
		}
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
	
		FileOutputStream fos = new FileOutputStream(realpath + "student.xls");
		wb.write(fos);

		
		//下载
		inputStream = new FileInputStream(realpath + "student.xls");
		contentType =  ServletActionContext.getServletContext().getMimeType(realpath + "student.xls");
		filename = "student.xls";
	}

	public InfoService getInfoService() {
		return infoService;
	}


	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}


	public List<Object[]> getInfo() {
		return info;
	}


	public void setInfo(List<Object[]> info) {
		this.info = info;
	}


	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public String getRandomDir() {
		return randomDir;
	}


	public void setRandomDir(String randomDir) {
		this.randomDir = randomDir;
	}


	
	public int getClassinfo_id() {
		return classinfo_id;
	}


	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}

	public HeadMasterService getHeadMasterService() {
		return headMasterService;
	}

	public void setHeadMasterService(HeadMasterService headMasterService) {
		this.headMasterService = headMasterService;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
}
