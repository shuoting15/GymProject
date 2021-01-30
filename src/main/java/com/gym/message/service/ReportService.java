package com.gym.message.service;

import java.util.List;

import com.gym.message.model.ReportBean;

public interface ReportService {

	void addReportContent(ReportBean reportbean);
	
	int deleteMessageReport(int no);
	
	List<ReportBean>  getAllReport();
	
	public ReportBean getReportContentById(int reportId);
}
