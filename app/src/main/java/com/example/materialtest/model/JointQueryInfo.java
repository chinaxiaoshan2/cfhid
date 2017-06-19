package com.example.materialtest.model;

import java.io.Serializable;

public class JointQueryInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int imageId;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	private String yonghu;      //用户
	private String gengxinrq;   //更新日期
	private String xiangmumc;   //项目名称
	private String xiangmugk;   //项目概况
	private String genzongqk;   //跟踪操作情况
	private String yujizb;      //预计招标日期
	private String jingzhengds; //竞争对手
	private String xinxijib;    //信息级别
	private String fuzebm;      //负责部门
	private String fuzery;      //负责人员
	private String jinzhanjd;   //进展阶段
	private String xinxilb;     //信息类别
	private String chuangjiansj;//创建时间
	private String xinxijg;     //信息结果
	private String beizhu;      //备注
	private String xuhao;       //序号
	private String keduan;      //科段
	private String diqu;        //地区
	private String lurur;       //录入人
	private String lurur_id; //录入人ID
	private String shebeizl;    //设备重量
	private String yujihte;     //预计合同额
	private String jiaohuoqyq ; //交货期要求
	private String zhaobiaofs;  //招标方式
	private String shifouxyxt;  //是否需要协调
	private String yonghulxr;   //用户联系人
	private String yonghulxdh ; //用户联系人电话
	private String yonghuemail; //用户E-mail
	private String weizhongbyy; //未中标原因
	private String zhongjians;  //中间商
	private String zhongjianslxr;//中间商联系人
	private String zhongjiansdh;//中间商联系电话
	private String zhongjiansemail;//中间商Emial
	private String suoshuly;     //所属领域
	private int    to_ministor;  //是否发送到部长
	private int    to_manager;   //是否发送到领导
	private int    to_recorder;  //是否发送到记录员
	private int    ministor_status;//部长状态
	private int    manager_status;//管理员状态
	private int    recorder_status;//记录员状态
	private String ministor_id;   //部长ID
	private String manager_id;    //管理员ID
	private String record_id;     //记录员ID
	private String ministor_name; //部长ID
	private String manager_name;  //管理员ID
	private String record_name;   //记录员ID
	private String create_id;     //登录者ID
	private int    modify_status; //是否修改
	private int    to_kdministor; //是否发送到负责部门部长
	private int    kdministor_status;//负责部门部长状态
	private String kdministor_id;   //负责部门部长ID
	private int    keduan_id;      //负责部门
	private String caiji_id;       //采集者ID
	private String fuzery_id;       //负责者ID
	public String getYonghu() {
		return yonghu;
	}
	public void setYonghu(String yonghu) {
		this.yonghu = yonghu;
	}
	public String getGengxinrq() {
		return gengxinrq;
	}
	public void setGengxinrq(String gengxinrq) {
		this.gengxinrq = gengxinrq;
	}
	public String getXiangmumc() {
		return xiangmumc;
	}
	public void setXiangmumc(String xiangmumc) {
		this.xiangmumc = xiangmumc;
	}
	public String getXiangmugk() {
		return xiangmugk;
	}
	public void setXiangmugk(String xiangmugk) {
		this.xiangmugk = xiangmugk;
	}
	public String getGenzongqk() {
		return genzongqk;
	}
	public void setGenzongqk(String genzongqk) {
		this.genzongqk = genzongqk;
	}
	public String getYujizb() {
		return yujizb;
	}
	public void setYujizb(String yujizb) {
		this.yujizb = yujizb;
	}
	public String getJingzhengds() {
		return jingzhengds;
	}
	public void setJingzhengds(String jingzhengds) {
		this.jingzhengds = jingzhengds;
	}
	public String getXinxijib() {
		return xinxijib;
	}
	public void setXinxijib(String xinxijib) {
		this.xinxijib = xinxijib;
	}
	public String getFuzebm() {
		return fuzebm;
	}
	public void setFuzebm(String fuzebm) {
		this.fuzebm = fuzebm;
	}
	public String getFuzery() {
		return fuzery;
	}
	public void setFuzery(String fuzery) {
		this.fuzery = fuzery;
	}
	public String getJinzhanjd() {
		return jinzhanjd;
	}
	public void setJinzhanjd(String jinzhanjd) {
		this.jinzhanjd = jinzhanjd;
	}
	public String getXinxilb() {
		return xinxilb;
	}
	public void setXinxilb(String xinxilb) {
		this.xinxilb = xinxilb;
	}
	public String getChuangjiansj() {
		return chuangjiansj;
	}
	public void setChuangjiansj(String chuangjiansj) {
		this.chuangjiansj = chuangjiansj;
	}
	public String getXinxijg() {
		return xinxijg;
	}
	public void setXinxijg(String xinxijg) {
		this.xinxijg = xinxijg;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getXuhao() {
		return xuhao;
	}
	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
	public String getKeduan() {
		return keduan;
	}
	public void setKeduan(String keduan) {
		this.keduan = keduan;
	}
	public String getDiqu() {
		return diqu;
	}
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}
	public String getLurur() {
		return lurur;
	}
	public void setLurur(String lurur) {
		this.lurur = lurur;
	}
	public String getLurur_id() {
		return lurur_id;
	}
	public void setLurur_id(String lurur_id) {
		this.lurur_id = lurur_id;
	}
	public String getShebeizl() {
		return shebeizl;
	}
	public void setShebeizl(String shebeizl) {
		this.shebeizl = shebeizl;
	}
	public String getYujihte() {
		return yujihte;
	}
	public void setYujihte(String yujihte) {
		this.yujihte = yujihte;
	}
	public String getJiaohuoqyq() {
		return jiaohuoqyq;
	}
	public void setJiaohuoqyq(String jiaohuoqyq) {
		this.jiaohuoqyq = jiaohuoqyq;
	}
	public String getZhaobiaofs() {
		return zhaobiaofs;
	}
	public void setZhaobiaofs(String zhaobiaofs) {
		this.zhaobiaofs = zhaobiaofs;
	}
	public String getShifouxyxt() {
		return shifouxyxt;
	}
	public void setShifouxyxt(String shifouxyxt) {
		this.shifouxyxt = shifouxyxt;
	}
	public String getYonghulxr() {
		return yonghulxr;
	}
	public void setYonghulxr(String yonghulxr) {
		this.yonghulxr = yonghulxr;
	}
	public String getYonghulxdh() {
		return yonghulxdh;
	}
	public void setYonghulxdh(String yonghulxdh) {
		this.yonghulxdh = yonghulxdh;
	}
	public String getYonghuemail() {
		return yonghuemail;
	}
	public void setYonghuemail(String yonghuemail) {
		this.yonghuemail = yonghuemail;
	}
	public String getWeizhongbyy() {
		return weizhongbyy;
	}
	public void setWeizhongbyy(String weizhongbyy) {
		this.weizhongbyy = weizhongbyy;
	}
	public String getZhongjians() {
		return zhongjians;
	}
	public void setZhongjians(String zhongjians) {
		this.zhongjians = zhongjians;
	}
	public String getZhongjianslxr() {
		return zhongjianslxr;
	}
	public void setZhongjianslxr(String zhongjianslxr) {
		this.zhongjianslxr = zhongjianslxr;
	}
	public String getZhongjiansdh() {
		return zhongjiansdh;
	}
	public void setZhongjiansdh(String zhongjiansdh) {
		this.zhongjiansdh = zhongjiansdh;
	}
	public String getZhongjiansemail() {
		return zhongjiansemail;
	}
	public void setZhongjiansemail(String zhongjiansemail) {
		this.zhongjiansemail = zhongjiansemail;
	}
	public String getSuoshuly() {
		return suoshuly;
	}
	public void setSuoshuly(String suoshuly) {
		this.suoshuly = suoshuly;
	}
	public int getTo_ministor() {
		return to_ministor;
	}
	public void setTo_ministor(int to_ministor) {
		this.to_ministor = to_ministor;
	}
	public int getTo_manager() {
		return to_manager;
	}
	public void setTo_manager(int to_manager) {
		this.to_manager = to_manager;
	}
	public int getTo_recorder() {
		return to_recorder;
	}
	public void setTo_recorder(int to_recorder) {
		this.to_recorder = to_recorder;
	}
	public int getMinistor_status() {
		return ministor_status;
	}
	public void setMinistor_status(int ministor_status) {
		this.ministor_status = ministor_status;
	}
	public int getManager_status() {
		return manager_status;
	}
	public void setManager_status(int manager_status) {
		this.manager_status = manager_status;
	}
	public int getRecorder_status() {
		return recorder_status;
	}
	public void setRecorder_status(int recorder_status) {
		this.recorder_status = recorder_status;
	}
	public String getMinistor_id() {
		return ministor_id;
	}
	public void setMinistor_id(String ministor_id) {
		this.ministor_id = ministor_id;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getMinistor_name() {
		return ministor_name;
	}
	public void setMinistor_name(String ministor_name) {
		this.ministor_name = ministor_name;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getRecord_name() {
		return record_name;
	}
	public void setRecord_name(String record_name) {
		this.record_name = record_name;
	}
	public String getCreate_id() {
		return create_id;
	}
	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}
	public int getModify_status() {
		return modify_status;
	}
	public void setModify_status(int modify_status) {
		this.modify_status = modify_status;
	}
	public int getTo_kdministor() {
		return to_kdministor;
	}
	public void setTo_kdministor(int to_kdministor) {
		this.to_kdministor = to_kdministor;
	}
	public int getKdministor_status() {
		return kdministor_status;
	}
	public void setKdministor_status(int kdministor_status) {
		this.kdministor_status = kdministor_status;
	}
	public String getKdministor_id() {
		return kdministor_id;
	}
	public void setKdministor_id(String kdministor_id) {
		this.kdministor_id = kdministor_id;
	}
	public int getKeduan_id() {
		return keduan_id;
	}
	public void setKeduan_id(int keduan_id) {
		this.keduan_id = keduan_id;
	}
	public String getCaiji_id() {
		return caiji_id;
	}
	public void setCaiji_id(String caiji_id) {
		this.caiji_id = caiji_id;
	}
	public String getFuzery_id() {
		return fuzery_id;
	}
	public void setFuzery_id(String fuzery_id) {
		this.fuzery_id = fuzery_id;
	}

	public JointQueryInfo() {
	}

	public JointQueryInfo(int imageId, String yonghu, String gengxinrq, String xiangmumc, String xiangmugk, String genzongqk, String yujizb, String jingzhengds, String xinxijib, String fuzebm, String fuzery, String jinzhanjd, String xinxilb, String chuangjiansj, String xinxijg, String beizhu, String xuhao, String keduan, String diqu, String lurur, String lurur_id, String shebeizl, String yujihte, String jiaohuoqyq, String zhaobiaofs, String shifouxyxt, String yonghulxr, String yonghulxdh, String yonghuemail, String weizhongbyy, String zhongjians, String zhongjianslxr, String zhongjiansdh, String zhongjiansemail, String suoshuly, int to_ministor, int to_manager, int to_recorder, int ministor_status, int manager_status, int recorder_status, String ministor_id, String manager_id, String record_id, String ministor_name, String manager_name, String record_name, String create_id, int modify_status, int to_kdministor, int kdministor_status, String kdministor_id, int keduan_id, String caiji_id, String fuzery_id) {
		this.imageId = imageId;
		this.yonghu = yonghu;
		this.gengxinrq = gengxinrq;
		this.xiangmumc = xiangmumc;
		this.xiangmugk = xiangmugk;
		this.genzongqk = genzongqk;
		this.yujizb = yujizb;
		this.jingzhengds = jingzhengds;
		this.xinxijib = xinxijib;
		this.fuzebm = fuzebm;
		this.fuzery = fuzery;
		this.jinzhanjd = jinzhanjd;
		this.xinxilb = xinxilb;
		this.chuangjiansj = chuangjiansj;
		this.xinxijg = xinxijg;
		this.beizhu = beizhu;
		this.xuhao = xuhao;
		this.keduan = keduan;
		this.diqu = diqu;
		this.lurur = lurur;
		this.lurur_id = lurur_id;
		this.shebeizl = shebeizl;
		this.yujihte = yujihte;
		this.jiaohuoqyq = jiaohuoqyq;
		this.zhaobiaofs = zhaobiaofs;
		this.shifouxyxt = shifouxyxt;
		this.yonghulxr = yonghulxr;
		this.yonghulxdh = yonghulxdh;
		this.yonghuemail = yonghuemail;
		this.weizhongbyy = weizhongbyy;
		this.zhongjians = zhongjians;
		this.zhongjianslxr = zhongjianslxr;
		this.zhongjiansdh = zhongjiansdh;
		this.zhongjiansemail = zhongjiansemail;
		this.suoshuly = suoshuly;
		this.to_ministor = to_ministor;
		this.to_manager = to_manager;
		this.to_recorder = to_recorder;
		this.ministor_status = ministor_status;
		this.manager_status = manager_status;
		this.recorder_status = recorder_status;
		this.ministor_id = ministor_id;
		this.manager_id = manager_id;
		this.record_id = record_id;
		this.ministor_name = ministor_name;
		this.manager_name = manager_name;
		this.record_name = record_name;
		this.create_id = create_id;
		this.modify_status = modify_status;
		this.to_kdministor = to_kdministor;
		this.kdministor_status = kdministor_status;
		this.kdministor_id = kdministor_id;
		this.keduan_id = keduan_id;
		this.caiji_id = caiji_id;
		this.fuzery_id = fuzery_id;
	}
}

