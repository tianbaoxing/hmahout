package org.hmahout.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 处理翻页后，产生的翻页bean，记录了所有翻页和查询结果的信息。
 * 
 */
public class Page <T> implements Serializable {
	
	public static final Integer page_num = 0;
	public static final Integer per_num = 10;

	private Integer page = page_num; // 第几页
	private Integer per = per_num; // 每页多少记录数

	/**
	 * 总页数
	 */
	private int totalPages;
	/**
	 * 总记录数
	 */
	private int totalRecords;

	/**
	 * 结果
	 */
	private List<T> results;

	public Page() {
		super();
	}

	public Page(Integer currentPage, Integer pageSize) {
		this.page = currentPage;
		this.per = pageSize;
	}

	public List<T> getResults() {
		if(results == null)
			return new ArrayList<T>();
		else
			return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public int getPage() {
		
		return (page==null||page.compareTo(page_num)<0)?page_num:page;
		
	}

	public void setPage(Integer currentPage) {
		this.page = currentPage;
	}

	public int getPer() {
		return (per==null||per.compareTo( new Integer(0))<0)
		?per_num
		:per;
	}

	public void setPer(Integer pageSize) {
		this.per = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int count) {
		totalRecords = count;
		totalPages = totalRecords / this.getPer();
		if (totalRecords % this.getPer() != 0) {
			totalPages++;
		}

		if (totalPages != 0) {
			if (this.getPage() < 1) {
				page = 1;
			}
		} else {
			page = 0;
		}
	}

}
