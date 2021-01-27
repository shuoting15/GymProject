package com.gym.message.dao;

import java.util.List;

import com.gym.message.model.ReportBean;


public interface ReportDao {

	void addReportContent(String rb);
	
	int deleteMessageReport(int no);
	
	List<ReportBean>  getAllReport();
}
