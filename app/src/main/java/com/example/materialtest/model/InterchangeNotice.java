package com.example.materialtest.model;

import java.io.Serializable;

/**
 * 下发到经营人员的交流通知
 * @author yb
 *
 */
public class InterchangeNotice  implements Serializable {
	private int imageId;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String id; //id
	private String ic_id;  //交流id
	private String ic_desc;  //描述
	private String pro_id; //项目id
	private String ic_creator; //创建人
	//private Date ic_createDate; //创建时间
    private String ic_createDate; //创建时间
	private String ic_task_id; //任务id
	private String minstor_id; //部长id
	private String user_id;
	private String dep_type; //任务类型
	private String manager_id; //项目部部长id
	private int manager_status;//项目部部长状态
	private int minstor_status;//部长状态
	private String current_status; //当前状态
	private int to_minstor;
	private int dep_id;

	/*---------------------------------------------------
	以下字段为显示而定义*/


	private String pro_name; //项目名称
	private String pro_num;//项目编号
	private String user_name;//用户名
	private String pricer_id; // 技术报价单id
	private String dep_type_name;

	private String recived_dep_name; //已提交部门
	private String unRecived_dep_name;//未提交部门
	private int allowSubmit1;//是否允许结束技术报价


	private String minstor_name; //部长姓名
	//private Date icu_create_date; //任务创建时间
    private String icu_create_date; //任务创建时间

	public String getIcu_create_date() {
		return icu_create_date;
	}

	public void setIcu_create_date(String icu_create_date) {
		this.icu_create_date = icu_create_date;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String header_user; //负责人id
	private String header_user_name; //负责人name
	private float project_total_cost;
	private String project_number;

	private float reim_travel_expense;

	private float reim_material_fee;

	private float reim_hospitality_business;

	private float reim_other_expense;

	private float reim_other_remark;

	private float reim_basic_performance;

	private float ic_cost; //交流总成本

	public String getIc_id() {
		return ic_id;
	}
	public void setIc_id(String ic_id) {
		this.ic_id = ic_id;
	}
	public String getIc_desc() {
		return ic_desc;
	}
	public void setIc_desc(String ic_desc) {
		this.ic_desc = ic_desc;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getIc_creator() {
		return ic_creator;
	}
	public void setIc_creator(String ic_creator) {
		this.ic_creator = ic_creator;
	}
/*	//public Date getIc_createDate() {
		return ic_createDate;
	}*/
	/*public void setIc_createDate(Date ic_createDate) {
		this.ic_createDate = ic_createDate;
	}
	*/public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getIc_task_id() {
		return ic_task_id;
	}
	public void setIc_task_id(String ic_task_id) {
		this.ic_task_id = ic_task_id;
	}
	public String getMinstor_id() {
		return minstor_id;
	}
	public void setMinstor_id(String minstor_id) {
		this.minstor_id = minstor_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDep_type() {
		return dep_type;
	}
	public void setDep_type(String dep_type) {
		this.dep_type = dep_type;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_num() {
		return pro_num;
	}
	public void setPro_num(String pro_num) {
		this.pro_num = pro_num;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPricer_id() {
		return pricer_id;
	}
	public void setPricer_id(String pricer_id) {
		this.pricer_id = pricer_id;
	}
	public String getRecived_dep_name() {
		return recived_dep_name;
	}
	public void setRecived_dep_name(String recived_dep_name) {
		this.recived_dep_name = recived_dep_name;
	}
	public String getUnRecived_dep_name() {
		return unRecived_dep_name;
	}
	public void setUnRecived_dep_name(String unRecived_dep_name) {
		this.unRecived_dep_name = unRecived_dep_name;
	}
	public int getAllowSubmit1() {
		return allowSubmit1;
	}
	public void setAllowSubmit1(int allowSubmit1) {
		this.allowSubmit1 = allowSubmit1;
	}
	public String getMinstor_name() {
		return minstor_name;
	}
	public void setMinstor_name(String minstor_name) {
		this.minstor_name = minstor_name;
	}
	/*public Date getIcu_create_date() {
		return icu_create_date;
	}
	public void setIcu_create_date(Date icu_create_date) {
		this.icu_create_date = icu_create_date;
	}*/

    public String getIc_createDate() {
        return ic_createDate;
    }

    public void setIc_createDate(String ic_createDate) {
        this.ic_createDate = ic_createDate;
    }

    public float getProject_total_cost() {
		return project_total_cost;
	}
	public void setProject_total_cost(float project_total_cost) {
		this.project_total_cost = project_total_cost;
	}
	public String getProject_number() {
		return project_number;
	}
	public void setProject_number(String project_number) {
		this.project_number = project_number;
	}
	public float getReim_travel_expense() {
		return reim_travel_expense;
	}
	public void setReim_travel_expense(float reim_travel_expense) {
		this.reim_travel_expense = reim_travel_expense;
	}
	public float getReim_material_fee() {
		return reim_material_fee;
	}
	public void setReim_material_fee(float reim_material_fee) {
		this.reim_material_fee = reim_material_fee;
	}
	public float getReim_hospitality_business() {
		return reim_hospitality_business;
	}
	public void setReim_hospitality_business(float reim_hospitality_business) {
		this.reim_hospitality_business = reim_hospitality_business;
	}
	public float getReim_other_expense() {
		return reim_other_expense;
	}
	public void setReim_other_expense(float reim_other_expense) {
		this.reim_other_expense = reim_other_expense;
	}
	public float getReim_other_remark() {
		return reim_other_remark;
	}
	public void setReim_other_remark(float reim_other_remark) {
		this.reim_other_remark = reim_other_remark;
	}
	public float getReim_basic_performance() {
		return reim_basic_performance;
	}
	public void setReim_basic_performance(float reim_basic_performance) {
		this.reim_basic_performance = reim_basic_performance;
	}
	public float getIc_cost() {
		return ic_cost;
	}
	public void setIc_cost(float ic_cost) {
		this.ic_cost = ic_cost;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public int getManager_status() {
		return manager_status;
	}
	public void setManager_status(int manager_status) {
		this.manager_status = manager_status;
	}
	public int getMinstor_status() {
		return minstor_status;
	}
	public void setMinstor_status(int minstor_status) {
		this.minstor_status = minstor_status;
	}
	public String getHeader_user() {
		return header_user;
	}
	public void setHeader_user(String header_user) {
		this.header_user = header_user;
	}
	public String getHeader_user_name() {
		return header_user_name;
	}
	public void setHeader_user_name(String header_user_name) {
		this.header_user_name = header_user_name;
	}
	public String getDep_type_name() {
		return dep_type_name;
	}
	public void setDep_type_name(String dep_type_name) {
		this.dep_type_name = dep_type_name;
	}
	public String getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}
	public int getTo_minstor() {
		return to_minstor;
	}
	public void setTo_minstor(int to_minstor) {
		this.to_minstor = to_minstor;
	}
	public int getDep_id() {
		return dep_id;
	}
	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}








}

