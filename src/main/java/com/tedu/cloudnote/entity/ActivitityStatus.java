package com.tedu.cloudnote.entity;

import java.io.Serializable;
/**
 * 活动状态
 * @author tarena
 *
 */
public class ActivitityStatus implements Serializable {
	private String cn_activity_status_id;//活动状态 
	private String cn_activity_id;//活动id         
	private String cn_activity_status_code;//活动状态code
	private String cn_activity_status_name;//活动状态名字
	public String getCn_activity_status_id() {
		return cn_activity_status_id;
	}
	public void setCn_activity_status_id(String cn_activity_status_id) {
		this.cn_activity_status_id = cn_activity_status_id;
	}
	public String getCn_activity_id() {
		return cn_activity_id;
	}
	public void setCn_activity_id(String cn_activity_id) {
		this.cn_activity_id = cn_activity_id;
	}
	public String getCn_activity_status_code() {
		return cn_activity_status_code;
	}
	public void setCn_activity_status_code(String cn_activity_status_code) {
		this.cn_activity_status_code = cn_activity_status_code;
	}
	public String getCn_activity_status_name() {
		return cn_activity_status_name;
	}
	public void setCn_activity_status_name(String cn_activity_status_name) {
		this.cn_activity_status_name = cn_activity_status_name;
	}
	@Override
	public String toString() {
		return "ActivitityStatus [cn_activity_status_id=" + cn_activity_status_id + ", cn_activity_id=" + cn_activity_id
				+ ", cn_activity_status_code=" + cn_activity_status_code + ", cn_activity_status_name="
				+ cn_activity_status_name + "]";
	}

}
