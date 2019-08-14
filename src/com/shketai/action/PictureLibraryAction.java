package com.shketai.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.PictureLibrary;
import com.shketai.service.LogService;
import com.shketai.service.PictureLibraryService;

/**
 * @author Administrator
 *	图片库控制层
 */
public class PictureLibraryAction extends ActionSupport{
	/**
	 *  构造方法	
	 */
	public PictureLibraryAction() {
		super();
	}

	//图片库 业务层对象
	private PictureLibraryService pictureLibraryService ;
	
	private LogService logService;
	
	private static final long serialVersionUID = 1L;

	private int teacher_id;
	
	private int pictureFlag;
	
	private PictureLibrary pictureLibrary ;
	
	private File file;  
	private String fileContentType;  
    private String fileFileName; 
	
	/**
	 *   上传图片
	 * @return
	 */
	public String pictureUpLoad() {
		
		logService.addLog("日历管理", "上传");
		
		 //获取要保存文件夹的物理路径(绝对路径)
        String realPath = ServletActionContext.getServletContext().getRealPath("/uploadPicture");
        File filePath = new File(realPath);
        
        String newPath = filePath.getAbsolutePath().replace("apache-tomcat-7", "apache-tomcat-7-other");
        File newFilePath = new File(newPath);
        
        //-------------------------
        String pictureName = System.currentTimeMillis()+fileFileName;
        
        String picturePath = realPath + "\\" + pictureName ;
        
        String pictureContentType = fileContentType;
        
        Date uploadtime = new Date();
        //------------------------
        
        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        if(!filePath.exists()) {
        	filePath.mkdirs();
        }
        
        if(!newFilePath.exists()) {
        	newFilePath.mkdirs();
        }
        
        
        try {
            //保存文件
            FileUtils.copyFile(file, new File(filePath,pictureName));
            
            FileUtils.copyFile(file, new File(newFilePath,pictureName));
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        PictureLibrary pictureLibrary = new PictureLibrary();
        pictureLibrary.setValuePictureLibrary(pictureName, pictureContentType, picturePath, uploadtime);
        
        pictureLibraryService.save(pictureLibrary, teacher_id);
        
		pictureFlag = 1 ;
		
		return SUCCESS;
	}
	
	
	/**
	 * @return
	 * 	第一张 图片 
	 */
	public String firstOnePicture() {
		
		logService.addLog("日历管理", "查询");
		
		 pictureLibrary = pictureLibraryService.firstOnePicture();
		 String picturepath =  pictureLibrary.getPicturepath();
		 picturepath = picturepath.replaceAll("\\\\", "/");
		 
		 picturepath = picturepath.substring( picturepath.indexOf("/pjsys") , picturepath.length() );
		 
		 pictureLibrary.setPicturepath("https://www.pjxqw-edu.com:8443"+picturepath);
		 
		return SUCCESS;
	}
	
	
	
	/**
	 * @param pictureLibraryService
	 */
	public void setPictureLibraryService(PictureLibraryService pictureLibraryService) {
		this.pictureLibraryService = pictureLibraryService;
	}

	public int getPictureFlag() {
		return pictureFlag;
	}

	public void setPictureFlag(int pictureFlag) {
		this.pictureFlag = pictureFlag;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	public void setPictureLibrary(PictureLibrary pictureLibrary) {
		this.pictureLibrary = pictureLibrary;
	}


	public PictureLibrary getPictureLibrary() {
		return pictureLibrary;
	}


	public LogService getLogService() {
		return logService;
	}


	public void setLogService(LogService logService) {
		this.logService = logService;
	}


	public PictureLibraryService getPictureLibraryService() {
		return pictureLibraryService;
	}

	
	
}
