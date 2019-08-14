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
import com.shketai.service.LogService;
import com.shketai.service.PaymentService;

public class DownloadMoneyAction extends ActionSupport {
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();

	private static final long serialVersionUID = 1L;
	private PaymentService paymentService;
	private List<Object[]> info;
	private InputStream inputStream;// 要下载文件的字节输入流
	private String filename;// 要下载文件的名字
	private String contentType;// 要下载的文件类型
	private int result;
	private Date dt;       //时间
	private Integer flag;    //缴费状态
	private int classinfo_id;
	private String randomDir = UUID.randomUUID().toString();// 表示download目录中随机目录，每次下载都不一样
	private LogService logService;

	
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
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
		String str=null;
		if(flag==3) {
			str="缴费";
		}
		if(flag==6) {
			str="退费";
		}
		if(flag==9) {
			str="续费";
		}
		logService.addLog("财务数据汇总-"+str+"明细", "导出当前班级excel");
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename=testRed.xls");// 设定输出文件头
		response.setContentType("application/msexcel;charset=UTF-8");// 设置文件编码格式
		// response.setContentType("application/msexcel");// 定义输出类型

		WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
		String tmptitle = str+"明细"; // 标题
		// WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称
		WritableSheet wsheet = wbook.createSheet(tmptitle, 0);// sheet名称
		wsheet.setColumnView(0, 30); // 设置列的宽度 

		wsheet.setColumnView(1, 35); // 设置列的宽度 
		
		wsheet.setColumnView(2, 25); // 设置列的宽度 
		
		wsheet.setColumnView(3, 15); // 设置列的宽度 

		wsheet.setColumnView(4, 15); // 设置列的宽度 

		wsheet.setColumnView(5, 30); // 设置列的宽度 
		wsheet.setColumnView(6, 20); // 设置列的宽度 
		wsheet.setColumnView(7, 20); // 设置列的宽度 
		wsheet.setColumnView(8, 20); // 设置列的宽度 


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
		wsheet.addCell(new Label(0, 1, "学生姓名",wcf_center));
		wsheet.addCell(new Label(1, 1, "身份证号",wcf_center));
		wsheet.addCell(new Label(2, 1, "家长手机号",wcf_center));
		wsheet.addCell(new Label(3, 1, "科目",wcf_center));
		wsheet.addCell(new Label(4, 1, "班级",wcf_center));
		wsheet.addCell(new Label(5, 1, str+"金额",wcf_center));
		wsheet.addCell(new Label(6, 1, "申请时间",wcf_center));
		wsheet.addCell(new Label(7, 1, "审核时间",wcf_center));
		wsheet.addCell(new Label(8, 1, "支付方式",wcf_center));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info = paymentService.getPayment1(classinfo_id,dt,flag);
		int i = 2;
		for (Object[] object : info) {
			String Stuname =object[0].toString();
			wsheet.addCell(new Label(0, i, Stuname,wcf_left));
			wsheet.addCell(new Label(1, i, object[1].toString(),wcf_left));
			wsheet.addCell(new Label(2, i, object[2].toString(),wcf_left));
			wsheet.addCell(new Label(3, i, object[3].toString(),wcf_left));
			wsheet.addCell(new Label(4, i, object[4].toString(),wcf_left));
			wsheet.addCell(new Label(5, i, object[5].toString(),wcf_left));
			try {
				wsheet.addCell(new Label(6, i, object[6].toString(),wcf_left));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				wsheet.addCell(new Label(6, i, null,wcf_left));
			}
			try {
				wsheet.addCell(new Label(7, i, object[7].toString(),wcf_left));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				wsheet.addCell(new Label(7, i, null,wcf_left));
			}
			try {
				String zf=null;
				Integer il=Integer.parseInt(object[8].toString());
				if(il==1) {
					zf="微信支付";
				}
				if(il==2) {
					zf="支付宝支付";
				}
				if(il==3) {
					zf="现金支付";
				}
				if(il==4) {
					zf="pos机支付";
				}
				wsheet.addCell(new Label(8, i,zf,wcf_left));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				wsheet.addCell(new Label(8, i, null,wcf_left));
			}
			i++;
		}

		// 主体内容生成结束
		wbook.write(); // 写入文件
		wbook.close();
		os.close(); // 关闭流

	}

	public int getClassinfo_id() {
		return classinfo_id;
	}

	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}

}

