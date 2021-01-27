package com.gym.message.service;

import java.util.List;

import com.gym.message.model.ReportBean;

public interface ReportService {

	void addReportContent(String rb);
	
	int deleteMessageReport(int no);
	
	List<ReportBean>  getAllReport();
}
