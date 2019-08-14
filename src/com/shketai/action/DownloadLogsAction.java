package com.shketai.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.DBLogger;
import com.shketai.service.LogService;
import com.shketai.service.PaymentService;

public class DownloadLogsAction extends ActionSupport {
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();

	private static final long serialVersionUID = 1L;
	private List<DBLogger> info;
	private InputStream inputStream;// 要下载文件的字节输入流
	private String filename;// 要下载文件的名字
	private String contentType;// 要下载的文件类型
	private int result;
	
	private int classinfo_id;
	private String randomDir = UUID.randomUUID().toString();// 表示download目录中随机目录，每次下载都不一样
	private LogService logService;
	
	private Integer type;     //0则导出全部,1则按日期导出
	private Date startDate;
	private Date endDate;    
	
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
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

	public List<DBLogger> getInfo() {
		return info;
	}

	public void setInfo(List<DBLogger> info) {
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

	public void download1() throws IOException, WriteException, ClassCastException {
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename=testRed.xls");// 设定输出文件头
		response.setContentType("application/msexcel;charset=UTF-8");// 设置文件编码格式
		// response.setContentType("application/msexcel");// 定义输出类型

		WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
		String tmptitle = "操作日志"; // 标题
		// WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称
		WritableSheet wsheet = wbook.createSheet(tmptitle, 0);// sheet名称
		wsheet.setColumnView(0, 30); // 设置列的宽度 

		wsheet.setColumnView(1, 30); // 设置列的宽度 
		
		wsheet.setColumnView(2, 35); // 设置列的宽度 
		
		wsheet.setColumnView(3, 35); // 设置列的宽度 
		
		wsheet.setColumnView(4, 35); // 设置列的宽度 


		wsheet.setRowView(1, 800); // 设置行的高度 
		wsheet.setRowView(0, 800); // 设置行的高度 

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
		wsheet.mergeCells(0, 0, 5, 0);
		wsheet.addCell(new Label(0, 1, "编号",wcf_center));
		wsheet.addCell(new Label(1, 1, "用户名",wcf_center));
		wsheet.addCell(new Label(2, 1, "操作模块",wcf_center));
		wsheet.addCell(new Label(3, 1, "操作类型",wcf_center));
		wsheet.addCell(new Label(4, 1, "操作时间",wcf_center));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(this.getType()==1) {
			info=logService.findDate(this.getStartDate(),this.getEndDate());
			logService.addLog("操作日志", "按日期导出");
		}else {
			info=logService.findAlls();
			logService.addLog("操作日志", "导出");
		}
		int i = 2;
		for (DBLogger object : info) {
			String Sid=String.valueOf(object.getId());
			wsheet.addCell(new Label(0, i, Sid,wcf_left));
			wsheet.addCell(new Label(1, i, object.getTeacher().getUsername(),wcf_left));
			wsheet.addCell(new Label(2, i, object.getMokuai(),wcf_left));
			wsheet.addCell(new Label(3, i, object.getEventType(),wcf_left));
			wsheet.addCell(new Label(4, i, object.getTime(),wcf_left));
			i++;
		}

		// 主体内容生成结束
		wbook.write(); // 写入文件
		wbook.close();
		os.close(); // 关闭流

	}

}

