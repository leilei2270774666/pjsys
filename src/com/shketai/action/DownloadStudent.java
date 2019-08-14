package com.shketai.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Attence;
import com.shketai.entity.Classinfo;
import com.shketai.entity.HeadMaster;
import com.shketai.entity.Student;
import com.shketai.service.AttenceService;
import com.shketai.service.ClassinfoService;
import com.shketai.service.HeadMasterService;
import com.shketai.service.InfoService;
import com.shketai.service.LogService;
import com.shketai.service.StudentService;

import jxl.Cell;
import jxl.Sheet;
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

/**
 * @author Administrator
 *  下载 student
 *
 */
public class DownloadStudent extends ActionSupport{
	/**
	 *  构造方法	
	 */
	public DownloadStudent() {
		super();
	}

	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	
	private LogService logService;
	private List<Object[]> attence;
	private InputStream inputStream;// 要下载文件的字节输入流
	private String filename;// 要下载文件的名字
	private String contentType;// 要下载的文件类型
	private int result;
	private String randomDir = UUID.randomUUID().toString();// 表示download目录中随机目录，每次下载都不一样
	
	private int flag;
	
	private File file;
	
	private int classinfo_id;
	
	private List<Student> studentList;
	
	private Date startTime;
	private Date endTime;
	
	
//	学生 业务对象
	private StudentService studentService;
	
//	学生课程及备注 业务对象
	private InfoService infoService;
	
//	班级 业务对象
	private ClassinfoService classinfoService;
	
//	班主任 业务对象
	private HeadMasterService headMasterService;
	
//	考勤 业务对象
	private AttenceService attenceService;
	
	
	/**
	 *  下载模板
	 * 
	 */
	public void downloadStudent() throws IOException, WriteException, ClassCastException, IllegalArgumentException, IllegalAccessException {
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename=student.xls");// 设定输出文件头
		response.setContentType("application/msexcel;charset=UTF-8");// 设置文件编码格式
		// response.setContentType("application/msexcel");// 定义输出类型

		WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
		String tmptitle = "学生信息"; // 标题
		
		WritableSheet wsheet = wbook.createSheet(tmptitle, 0);// sheet名称
		wsheet.setColumnView(0, 50); // 设置列的宽度 
		wsheet.setColumnView(1, 100); // 设置列的宽度 
		wsheet.setColumnView(2, 20); // 设置列的宽度 

		wsheet.setRowView(0, 800); // 设置行的高度 
		wsheet.setRowView(1, 500); // 设置行的高度 
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
		WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 14);
		WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);

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
		wsheet.mergeCells(0, 0, 2, 0);
		wsheet.addCell(new Label(0, 1, "姓名",wcf_center));
		wsheet.addCell(new Label(1, 1, "身份证号码",wcf_center));
		wsheet.addCell(new Label(2, 1, "性别",wcf_center));
		
		//List<Object[]> list=new ArrayList<Object[]>();
		
		 /** ***************以下是EXCEL正文数据********************* */  
		
		
		// 主体内容生成结束
		wbook.write(); // 写入文件
		wbook.close();
		os.close(); // 关闭流

	}
	
	
	/**
	 * 读取学生excel
	 * 
	 * classinfo_id 班级id
	 * 
	 */
	public String loadStudentExcel() {
		
		if(file == null) {
			System.out.println("file is null");
			return SUCCESS;
		}
		
//		导入已存在的Excel文件，获得只读的工作薄对象
		FileInputStream fis = null;
		
		Workbook wk = null;
		
		try {
			fis = new FileInputStream(file);
			
			wk=Workbook.getWorkbook(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		创建集合对象
		List<Student> list_Student = new ArrayList<Student>();
		
//		获取第一张Sheet表 
		Sheet sheet=wk.getSheet(0);
//		获取总行数
		int rowNum=sheet.getRows();
		
//		从数据行开始迭代每一行
		for(int i=2;i<rowNum;i++){
			//getCell(column,row)，表示取得指定列指定行的单元格（Cell）
			//getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格
			//使用实体类封装单元格数据
			
			Cell cell0 = sheet.getCell(0, i);
			
			Cell cell1 = sheet.getCell(1, i);
			
			Cell cell2 = sheet.getCell(2, i);
			
//			姓名
			String stuName = cell0.getContents();
//			身份证
			String personalNum = cell1.getContents();
//			性别
			String sex = cell2.getContents();
			
			
//			1=关联成功  0=关联失败  -1=不存在
			Integer flag = -1;
//			返回信息
			String flag_info="";
//			创建学生对象
			Student my_Student = new Student();
			
//			查询指定 身份证号 的 学生 返回数量
			int count = studentService.queryPersonalNum(personalNum);
//			小于等于0 判定不存在学生
			if(count<=0) {
//				1=关联成功  0=关联失败  -1=不存在
				flag = -1;
//				返回信息
				flag_info = "学生不存在(身份证号不存在)" ;		
			}else {
//				存在
				Student student = studentService.queryPersonalNumReturnObj(personalNum);
//				把查询的学生对象 赋给创建的学生对象
				my_Student = student;
				
//				标识 0失败 1成功
				int index = 1;
				
//				判断姓名是否一致
				if(student.getStuName().equals( stuName )) {
					
				}else {
//					标识 0失败 1成功
					index = 0;
//					返回信息
					flag_info = flag_info + " 学生姓名不一致 " ;	
					
				}
//				 判断性别是否错误
				if("男".equals(sex) || "女".equals(sex) ) {
//				 判断性别是否一致
					if("男".equals(sex)) {
						//性别:1男2女
						int sexFlag = 1 ;
						if(sexFlag != student.getSex()) {
//							返回信息
							flag_info = flag_info + " 性别有误 " ;	
//							标识 0失败 1成功
							index = 0;
						}
					}
					if("女".equals(sex)) {
						//性别:1男2女
						int sexFlag = 2 ;
						if(sexFlag != student.getSex()) {
//							返回信息
							flag_info = flag_info + " 性别有误 " ;	
//							标识 0失败 1成功
							index = 0;
						}	
					}
					
				}else {
//					标识 0失败 1成功
					index = 0;
//					返回信息
					flag_info = flag_info + " 性别有误 " ;	
				}
				
				
//				判断index 决定 信息正确 开始 关联学生
//				标识 0失败 1成功
				if(index == 1) {
//					查询指定 学生id 和 班级 id 返回数量
					int result = infoService.querystu_idInclassinfo_id(student.getId(), classinfo_id);
//					存在关联
					if(result>0) {
//						返回信息
						flag_info = flag_info + " 班级已有该学生 " ;	
//						1=关联成功  0=关联失败  -1=不存在
						flag = 1;
					}else {
//						不存在关联 , 添加 学生和班级的关联
						infoService.addInfo( student.getId(), classinfo_id);
							
//						返回信息
						flag_info = flag_info + " 关联成功 " ;	
//						1=关联成功  0=关联失败  -1=不存在
						flag = 1;
						
					}
				}
//				标识 0失败 1成功
				if(index == 0) {
//					1=关联成功  0=关联失败  -1=不存在
					flag = 0;
				}
				
			}
			
			my_Student.setValueStudent( stuName,  personalNum,  sex,  flag,  flag_info);
			
//			添加
			list_Student.add(my_Student);
			
		}
		
//		关闭读取
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		wk.close();
		
//		把创建的学生集合 发送出去
		studentList = list_Student;
		
		return SUCCESS ;
	}
	
	
	/**
	 *  下载 本星期所有班级考勤
	 */
	public void downloadStudentAttence() throws IOException, WriteException, ClassCastException, IllegalArgumentException, IllegalAccessException {
		logService.addLog("班级考勤数据统计", "导出当天考勤");
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename=studentAttence.xls");// 设定输出文件头
		response.setContentType("application/msexcel;charset=UTF-8");// 设置文件编码格式
		// response.setContentType("application/msexcel");// 定义输出类型

		WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
		String tmptitle = "班级考勤"; // 标题
		
		WritableSheet wsheet = wbook.createSheet(tmptitle, 0);// sheet名称
		wsheet.setColumnView(0, 10); // 设置列的宽度 
		wsheet.setColumnView(1, 25); // 设置列的宽度 
		wsheet.setColumnView(2, 10); // 设置列的宽度 
		wsheet.setColumnView(3, 10); // 设置列的宽度 
		wsheet.setColumnView(4, 50); // 设置列的宽度 
		wsheet.setColumnView(5, 15); // 设置列的宽度 

		wsheet.setRowView(0, 800); // 设置行的高度 
		wsheet.setRowView(1, 500); // 设置行的高度 
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
		WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 14);
		WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);

		/** ************以下设置三种单元格样式，灵活备用************ */
		// 用于标题居中
		WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
		wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
		wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
		wcf_center.setWrap(false); // 文字是否换行
		

		// 用于正文居中
		WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
		wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
		wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
		wcf_left.setWrap(false); // 文字是否换行
		
//		第一行标题
		String titleName = "每周上课记录 ";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		标题加上日期
		titleName = titleName + sdf.format(new Date());
		
		// 开始生成主体内容
		wsheet.addCell(new Label(0, 0, titleName,wcf_center));
		wsheet.mergeCells(0, 0, 5, 0);
		wsheet.addCell(new Label(0, 1, "编号",wcf_center));
		wsheet.addCell(new Label(1, 1, "班名",wcf_center));
		wsheet.addCell(new Label(2, 1, "应到人数",wcf_center));
		wsheet.addCell(new Label(3, 1, "缺席人数",wcf_center));
		wsheet.addCell(new Label(4, 1, "缺席原因",wcf_center));
		wsheet.addCell(new Label(5, 1, "班主任",wcf_center));
		
		
		 /** ***************以下是EXCEL正文数据********************* */  
		
//		班级考勤数据汇总 全部
		List<Classinfo> classinfoList =	classinfoService.queryClassinfoInAttence();
//		开始
		for (int i = 0; i < classinfoList.size(); i++) {
//			得到班级对象
			Classinfo classinfo = classinfoList.get(i);
			
//			编号
			Integer index = i + 1;
			
//			班级名称
			String classname = classinfo.getClassname();
			
//			应到人数
			Integer count = classinfo.getCount();
			
//			缺席人数
			Integer unPerson = classinfo.getUnPerson();
			
//			缺席原因
			String message = attenceService.querUnAttenceMessage(classinfo.getId());
			
//			班主任
			String headmaster_name = "";
			
			Integer headmaster_id = classinfo.getHeadmaster_id();
//			判断班级是否有班主任
			if(headmaster_id != null) {
				HeadMaster headMaster = headMasterService.sqlQueryId(headmaster_id);
				headmaster_name= headMaster.getName();
			}
			
			
			wsheet.addCell( new Label(0, i+2,  index.toString() ) );
			wsheet.addCell( new Label(1, i+2,  classname) );
			wsheet.addCell( new Label(2, i+2,  count.toString() ) );
			wsheet.addCell( new Label(3, i+2,  unPerson.toString() ) );
			wsheet.addCell( new Label(4, i+2,  message ) );
			wsheet.addCell( new Label(5, i+2,  headmaster_name) );
			
		}
		
		
		// 主体内容生成结束
		wbook.write(); // 写入文件
		wbook.close();
		os.close(); // 关闭流

	}
	
	
	/**
	 *  下载 开始 到 结束 时间所有班级考勤
	 *  
	 *  传递参数
	 *  	startTime 开始时间
	 *  	endTime	结束时间
	 */
	public void downloadStudentAttenceStartAndEnd() throws IOException, WriteException, ClassCastException, IllegalArgumentException, IllegalAccessException {
		logService.addLog("班级考勤数据统计", "按时间导出考勤");
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename=studentAttence.xls");// 设定输出文件头
		response.setContentType("application/msexcel;charset=UTF-8");// 设置文件编码格式
		// response.setContentType("application/msexcel");// 定义输出类型

		WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
		String tmptitle = "班级考勤"; // 标题
		
		WritableSheet wsheet = wbook.createSheet(tmptitle, 0);// sheet名称
		wsheet.setColumnView(0, 10); // 设置列的宽度 
		wsheet.setColumnView(1, 25); // 设置列的宽度 
		wsheet.setColumnView(2, 10); // 设置列的宽度 
		wsheet.setColumnView(3, 10); // 设置列的宽度 
		wsheet.setColumnView(4, 50); // 设置列的宽度 
		wsheet.setColumnView(5, 15); // 设置列的宽度 

		wsheet.setRowView(0, 800); // 设置行的高度 
		wsheet.setRowView(1, 500); // 设置行的高度 
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
		WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 14);
		WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);

		/** ************以下设置三种单元格样式，灵活备用************ */
		// 用于标题居中
		WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
		wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
		wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
		wcf_center.setWrap(false); // 文字是否换行
		

		// 用于正文居中
		WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
		wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
		wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
		wcf_left.setWrap(false); // 文字是否换行
		
//		第一行标题
		String titleName = "上课记录 ";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		标题加上日期
		titleName = titleName + sdf.format(startTime);
		titleName = titleName + "~" + sdf.format(endTime);
		
		// 开始生成主体内容
		wsheet.addCell(new Label(0, 0, titleName,wcf_center));
		wsheet.mergeCells(0, 0, 5, 0);
		wsheet.addCell(new Label(0, 1, "编号",wcf_center));
		wsheet.addCell(new Label(1, 1, "班名",wcf_center));
		wsheet.addCell(new Label(2, 1, "应到人数",wcf_center));
		wsheet.addCell(new Label(3, 1, "缺席人数",wcf_center));
		wsheet.addCell(new Label(4, 1, "缺席原因",wcf_center));
		wsheet.addCell(new Label(5, 1, "班主任",wcf_center));
		
		
		 /** ***************以下是EXCEL正文数据********************* */  
		
//		班级考勤数据汇总 全部
		List<Classinfo> classinfoList =	classinfoService.queryClassinfoInAttenceByStartTimeAndEndtime( startTime ,  endTime);
//		开始
		for (int i = 0; i < classinfoList.size(); i++) {
//			得到班级对象
			Classinfo classinfo = classinfoList.get(i);
			
//			编号
			Integer index = i + 1;
			
//			班级名称
			String classname = classinfo.getClassname();
			
//			应到人数
			Integer count = classinfo.getCount();
			
//			缺席人数
			Integer unPerson = classinfo.getUnPerson();
			
//			缺席原因
			String message = attenceService.querUnAttenceMessageByStartTimeAndEndTime(classinfo.getId(),  startTime ,  endTime);
			
//			班主任
			String headmaster_name = "";
			
			Integer headmaster_id = classinfo.getHeadmaster_id();
//			判断班级是否有班主任
			if(headmaster_id != null) {
				HeadMaster headMaster = headMasterService.sqlQueryId(headmaster_id);
				headmaster_name= headMaster.getName();
			}
			
			
			wsheet.addCell( new Label(0, i+2,  index.toString() ) );
			wsheet.addCell( new Label(1, i+2,  classname) );
			wsheet.addCell( new Label(2, i+2,  count.toString() ) );
			wsheet.addCell( new Label(3, i+2,  unPerson.toString() ) );
			wsheet.addCell( new Label(4, i+2,  message ) );
			wsheet.addCell( new Label(5, i+2,  headmaster_name) );
			
		}
		
		
		// 主体内容生成结束
		wbook.write(); // 写入文件
		wbook.close();
		os.close(); // 关闭流

	}
	
	/**
	 * get set
	 * @return
	 */
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


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	public int getClassinfo_id() {
		return classinfo_id;
	}


	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}


	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}


	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}


	public List<Student> getStudentList() {
		return studentList;
	}


	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}


	public void setClassinfoService(ClassinfoService classinfoService) {
		this.classinfoService = classinfoService;
	}


	public void setHeadMasterService(HeadMasterService headMasterService) {
		this.headMasterService = headMasterService;
	}


	public void setAttenceService(AttenceService attenceService) {
		this.attenceService = attenceService;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public StudentService getStudentService() {
		return studentService;
	}


	public InfoService getInfoService() {
		return infoService;
	}


	public ClassinfoService getClassinfoService() {
		return classinfoService;
	}


	public HeadMasterService getHeadMasterService() {
		return headMasterService;
	}


	public AttenceService getAttenceService() {
		return attenceService;
	}


	public LogService getLogService() {
		return logService;
	}


	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	
	
	
}
