package com.gym.coach.model;

import java.io.Serializable;
import java.util.Date;


public class ClanderBean  implements Serializable {
	private static final long serialVersionUID = 1L;

	private int groupId;
	private String title;
	private Date start;
	private Date end;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}

}
