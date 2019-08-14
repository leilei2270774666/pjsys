package com.shketai.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Administrator
 *  词典
 */
@Entity
public class Dictionary {
	/**
	 *  构造方法	
	 */
	public Dictionary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Dictionary(String first_spell, String content) {
		super();
		this.first_spell = first_spell;
		this.content = content;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String first_spell;
	private String content;
	
	
	/**
	  * 赋值
	 */
	public void setValueDictionary(String first_spell, String content) {
		this.first_spell = first_spell;
		this.content = content;
	}
	
	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirst_spell() {
		return first_spell;
	}
	public void setFirst_spell(String first_spell) {
		this.first_spell = first_spell;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
