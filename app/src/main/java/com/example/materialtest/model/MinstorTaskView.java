package com.example.materialtest.model;

import java.io.Serializable;

/**
 * 部长任务显示视图
 * @author yb
 *
 */
public class MinstorTaskView implements Serializable {
	private String ic_task_id;
	private String ic_id;
	private String pro_id;
	private String ic_desc;
	private int ic_creator;
	private String ic_createDate;
	private int imageId;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	private String ic_creator_name; //任务创建者姓名
	private String pro_name;
	private String pro_number;
	private String dep_type; //任务类型

	private int depId;
	private String ic_header; //牵头部门
	private String dep_type_name; //任务类型名称
	private String ic_header_name; //牵头部门名称

	public String getIc_task_id() {
		return ic_task_id;
	}
	public void setIc_task_id(String ic_task_id) {
		this.ic_task_id = ic_task_id;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getIc_desc() {
		return ic_desc;
	}
	public void setIc_desc(String ic_desc) {
		this.ic_desc = ic_desc;
	}
	public int getIc_creator() {
		return ic_creator;
	}
	public void setIc_creator(int ic_creator) {
		this.ic_creator = ic_creator;
	}
	public String getIc_createDate() {
		return ic_createDate;
	}
	public void setIc_createDate(String ic_createDate) {
		this.ic_createDate = ic_createDate;
	}
	public String getIc_id() {
		return ic_id;
	}
	public void setIc_id(String ic_id) {
		this.ic_id = ic_id;
	}
	public String getIc_creator_name() {
		return ic_creator_name;
	}
	public void setIc_creator_name(String ic_creator_name) {
		this.ic_creator_name = ic_creator_name;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_number() {
		return pro_number;
	}
	public void setPro_number(String pro_number) {
		this.pro_number = pro_number;
	}
	public void setDepId(int int1) {
		// TODO Auto-generated method stub

	}
	public int getDepId() {
		return depId;
	}
	public String getDep_type() {
		return dep_type;
	}
	public void setDep_type(String dep_type) {
		this.dep_type = dep_type;
	}
	public String getIc_header() {
		return ic_header;
	}
	public void setIc_header(String ic_header) {
		this.ic_header = ic_header;
	}
	public String getDep_type_name() {
		return dep_type_name;
	}
	public void setDep_type_name(String dep_type_name) {
		this.dep_type_name = dep_type_name;
	}
	public String getIc_header_name() {
		return ic_header_name;
	}
	public void setIc_header_name(String ic_header_name) {
		this.ic_header_name = ic_header_name;
	}




}
