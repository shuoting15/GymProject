package com.gym.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gym.message.dao.ReportDao;
import com.gym.message.model.ReportBean;
@Service
@EnableTransactionManagement
public class ReportServiceImpl implements com.gym.message.service.ReportService {

	@Autowired
	ReportDao rbdao;
	
	
	@Transactional
	@Override
	public void addReportContent(String rb) {
		rbdao.addReportContent(rb);

	}
	@Transactional
	@Override
	public int deleteMessageReport(int no) {
		int n = 0;
//      Session session = factory.getCurrentSession();
//      Transaction tx = null;
//      try {
//          tx = session.beginTransaction();
		n = rbdao.deleteMessageReport(no);
//          tx.commit();
//      } catch (Exception ex) {
//          if (tx != null)  
//          	tx.rollback();
//          ex.printStackTrace();
//          throw new RuntimeException(ex);
//      }
		return n;
	}
	
	@Transactional
	@Override
	public List<ReportBean> getAllReport() {
		return rbdao.getAllReport();
	}

}
