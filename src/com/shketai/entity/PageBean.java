package com.shketai.entity;

import java.util.List;

public class PageBean<T> {

	private int pageSize = 10;//ÿҳ����������
	
	private int total;//�ܼ�¼��
	
	@SuppressWarnings("unused")
	private int pages;//��ҳ��
	
	private int current=1;//��ǰҳ��
	
	@SuppressWarnings("unused")
	private int start;//��ʼ��¼����
	
	private List<T> datas;//ÿҳ�����ݼ���

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return total%pageSize==0?total/pageSize:total/pageSize+1;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getStart() {
		return (current-1)*pageSize;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
}
