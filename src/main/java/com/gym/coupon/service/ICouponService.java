package com.gym.coupon.service;

import java.util.List;

import com.gym.coupon.model.CouponBean;



public interface ICouponService {
	public CouponBean insert(CouponBean cBean);
	public CouponBean selectbyId(Integer dicountId);
	public List<CouponBean> selectAll();
	public boolean delete(int dicountId);
	public CouponBean selectByCode(String Code);
	public boolean checkCode(String Code);


}
