package com.tedu.cloudnote.entity;

import java.io.Serializable;
/**
 * 活动状态
 * @author tarena
 *
 */
public class Activity implements Serializable {
	private String cn_activity_id;// 活动id    
	private String cn_activity_title;//活动标题   
	private String cn_activity_body;//活动介绍
	private Integer cn_activity_end_time;//活动结束时间
	public String getCn_activity_id() {
		return cn_activity_id;
	}
	public void setCn_activity_id(String cn_activity_id) {
		this.cn_activity_id = cn_activity_id;
	}
	public String getCn_activity_title() {
		return cn_activity_title;
	}
	public void setCn_activity_title(String cn_activity_title) {
		this.cn_activity_title = cn_activity_title;
	}
	public String getCn_activity_body() {
		return cn_activity_body;
	}
	public void setCn_activity_body(String cn_activity_body) {
		this.cn_activity_body = cn_activity_body;
	}
	public Integer getCn_activity_end_time() {
		return cn_activity_end_time;
	}
	public void setCn_activity_end_time(Integer cn_activity_end_time) {
		this.cn_activity_end_time = cn_activity_end_time;
	}
	@Override
	public String toString() {
		return "Activity [cn_activity_id=" + cn_activity_id + ", cn_activity_title=" + cn_activity_title
				+ ", cn_activity_body=" + cn_activity_body + ", cn_activity_end_time=" + cn_activity_end_time + "]";
	}
	
	
	
}
