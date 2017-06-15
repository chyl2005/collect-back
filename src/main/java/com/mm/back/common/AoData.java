package com.mm.back.common;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/3/26
 * Time:10:13
 * Desc:分页
 */
public class AoData<T> {

	private String sEcho;

	private int iDisplayStart; // 起始索引
	private int iDisplayLength;// 每页显示的行数

	private int iTotalRecords;// 实际的行数
	private int iTotalDisplayRecords;// 显示的行数,这个要和上面写的一样


	private T datas;
	
	public String getsEcho() {
		return sEcho;
	}


	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}
}
