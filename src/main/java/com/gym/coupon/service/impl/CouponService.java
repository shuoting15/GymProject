package com.gym.coupon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.coupon.dao.ICouponDao;
import com.gym.coupon.model.CouponBean;
import com.gym.coupon.service.ICouponService;



@Service
public class CouponService implements ICouponService {
    
	@Autowired
	ICouponDao cDao;
	
	public CouponService() {}

	@Override @Transactional
	public CouponBean insert(CouponBean cBean) {		
		return cDao.insert(cBean);
	}

	@Override @Transactional
	public CouponBean selectbyId(Integer dicountId) {
		
		return cDao.selectbyId(dicountId);
	}

	@Override @Transactional
	public List<CouponBean> selectAll() {
		return cDao.selectAll();
	}

	@Override @Transactional
	public boolean delete(int dicountId) {	
		return cDao.delete(dicountId);
	}

	@Override @Transactional
	public CouponBean selectByCode(String Code) {		
		return cDao.selectByCode(Code);
	}

	@Override @Transactional
	public boolean checkCode(String Code) {		
		return cDao.checkCode(Code);
	}

}
