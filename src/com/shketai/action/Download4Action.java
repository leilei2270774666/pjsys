package com.shketai.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import com.shketai.service.AttenceService;
import com.shketai.service.LogService;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Download4Action extends ActionSupport {
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();

	private LogService logService;
	private static final long serialVersionUID = 1L;
	private AttenceService attenceService;
	private List<Object[]> attence;
	private InputStream inputStream;// 要下载文件的字节输入流
	private String filename;// 要下载文件的名字
	private String contentType;// 要下载的文件类型
	private int result;
	private String randomDir = UUID.randomUUID().toString();// 表示download目录中随机目录，每次下载都不一样
	private int classinfo_id;
	public AttenceService getAttenceService() {
		return attenceService;
	}

	public void setAttenceService(AttenceService attenceService) {
		this.attenceService = attenceService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public List<Object[]> getAttence() {
		return attence;
	}

	public void setAttence(List<Object[]> attence) {
		this.attence = attence;
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

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
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
	

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void download4() throws IOException, WriteException, ClassCastException, IllegalArgumentException, IllegalAccessException {
		logService.addLog("学生考勤数据统计", "导出");
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename=testRed.xls");// 设定输出文件头
		response.setContentType("application/msexcel;charset=UTF-8");// 设置文件编码格式
		// response.setContentType("application/msexcel");// 定义输出类型

		WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
		String tmptitle = "学生考勤汇总"; // 标题
		// WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称
		WritableSheet wsheet = wbook.createSheet(tmptitle, 0);// sheet名称
		wsheet.setColumnView(0, 15); // 设置列的宽度 

		wsheet.setColumnView(1, 30); // 设置列的宽度 

		wsheet.setColumnView(2, 15); // 设置列的宽度 

		wsheet.setColumnView(3, 15); // 设置列的宽度 
		wsheet.setColumnView(4, 15); // 设置列的宽度 
		//wsheet.setColumnView(5, 15); // 设置列的宽度 

	

		wsheet.setRowView(1, 800); // 设置行的高度 
		wsheet.setRowView(0, 800); // 设置行的高度 
		/*
		 * // 设置excel标题 WritableFont wfont = new
		 * WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
		 * UnderlineStyle.NO_UNDERLINE, Colour.BLACK); WritableCellFormat wcfFC
		 * = new WritableCellFormat(wfont); wfont = new
		 * jxl.write.WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD,
		 * false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); wcfFC = new
		 * WritableCellFormat(wfont);
		 */

		/** ************设置单元格字体************** */
		WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 12);
		WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD);

		/** ************以下设置三种单元格样式，灵活备用************ */
		// 用于标题居中
		WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
		wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
		wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
		wcf_center.setWrap(false); // 文字是否换行
		

		// 用于正文居左
		WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
		wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
		wcf_left.setAlignment(Alignment.RIGHT); // 文字水平对齐
		wcf_left.setWrap(false); // 文字是否换行
		
		// 开始生成主体内容
		wsheet.addCell(new Label(0, 0, tmptitle,wcf_center));
		wsheet.mergeCells(0, 0, 4, 0);
		wsheet.addCell(new Label(0, 1, "姓名",wcf_center));
		wsheet.addCell(new Label(1, 1, "班级",wcf_center));
		wsheet.addCell(new Label(2, 1, "身份证号码",wcf_center));
		wsheet.addCell(new Label(3, 1, "考勤状态",wcf_center));
		wsheet.addCell(new Label(4, 1, "缺勤原因",wcf_center));
		
		attence = attenceService.Attendancesummary1(classinfo_id);
		//List<Object[]> list=new ArrayList<Object[]>();
		
		 /** ***************以下是EXCEL正文数据********************* */  

		int i = 2;
		
		for (Object[] object : attence) {
			String Stuname = object[0].toString();
			wsheet.addCell(new Label(0, i, Stuname,wcf_left));
			wsheet.addCell(new Label(1, i, object[1]!=null?object[1].toString():"",wcf_left));
			wsheet.addCell(new Label(2, i, object[2]!=null?object[2].toString():"",wcf_left));
			wsheet.addCell(new Label(3, i, object[3]!=null?object[3].toString():"",wcf_left));
			wsheet.addCell(new Label(4, i, object[4]!=null?object[4].toString():"",wcf_left));
			//wsheet.addCell(new Label(5, i, object[5].toString(),wcf_left));
			//wsheet.addCell(new Label(6, i, object[4].toString(),wcf_left));
			
			i++;
		}
		
		
		// 主体内容生成结束
		wbook.write(); // 写入文件
		wbook.close();
		os.close(); // 关闭流

	}
	
}
