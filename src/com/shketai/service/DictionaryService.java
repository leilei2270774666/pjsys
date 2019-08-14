package com.shketai.service;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.DictionaryDao;
import com.shketai.dao.StudentDao;
import com.shketai.entity.Dictionary;
import com.shketai.entity.Student;
import com.shketai.util.PingYinUtil;

/**
 * @author Administrator
 *	词典
 */
public class DictionaryService {
	/**
	 *  构造方法	
	 */
	public DictionaryService() {
		super();
		// TODO Auto-generated constructor stub
	}

	private DictionaryDao dictionaryDao;
	private StudentDao studentDao;
	
	
	/**
	 *   更新词典
	 */
	public void updateAllDictionary() {
		List<Student> studentList = studentDao.findAll();
		
		//把学生姓名全部合并
		String stuNamSum = "";
		for (int i = 0; i < studentList.size(); i++) {
			stuNamSum = stuNamSum + studentList.get(i).getStuName();
		}
		
		if(stuNamSum==null || "".equals(stuNamSum)) {
			return ;
		}
		
		//删除字符串中相同的字符
		stuNamSum = PingYinUtil.removeSameString(stuNamSum);
		
		//删除全部词典
		dictionaryDao.deleteAll();
		
		//字符串转换成字符数组
		char[] stuNamSumArr = stuNamSum.toCharArray();
		for (int i = 0; i < stuNamSumArr.length; i++) {  
			String content = stuNamSumArr[i]+"";
			//把汉字转换成拼音首字母
			String first_spell = PingYinUtil.getFirstSpell(content);
			//向数据库添加
			Dictionary dictionary = new Dictionary( first_spell,  content);
			dictionaryDao.saveObj(dictionary);
		}
	
		//删除词典里first_spell等于空或者空字符串
		dictionaryDao.deleteByfirst_spell_IsNullOrNotStr();
	}
	
	/**
	 * @return
	 */
	public DictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}

	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}


	public StudentDao getStudentDao() {
		return studentDao;
	}


	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	
	
}
