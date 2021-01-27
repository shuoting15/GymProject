package com.gym.coupon.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.coupon.dao.ICouponDao;
import com.gym.coupon.model.CouponBean;


@Repository
public class CouponDao implements ICouponDao {
	@Autowired
	SessionFactory factory;

	public CouponDao() {}
	
	public CouponBean update(Integer discountId,String code,String amount,String condition) {
		Session session = factory.getCurrentSession();
		CouponBean cBean = session.get(CouponBean.class, discountId);
		if(cBean!=null) {
			cBean.setCode(code);
			cBean.setCondition(condition);
			cBean.setAmount(amount);
		}
		return cBean;
	}
	public CouponBean insert(CouponBean cBean) { 
		Session session = factory.getCurrentSession();
		if (cBean != null) {
			session.save(cBean);
			return cBean;
		}
		return null;
	}

	public CouponBean selectbyId(Integer dicountId) { 
		Session session = factory.getCurrentSession();
		CouponBean dBean = session.get(CouponBean.class, dicountId);
		return dBean;
	}

	public List<CouponBean> selectAll() { 
		Session session = factory.getCurrentSession();
		Query<CouponBean> query = session.createQuery("from CouponBean", CouponBean.class);
		return query.list();
	}

	public boolean delete(int dicountId) { 
		Session session = factory.getCurrentSession();
		CouponBean cBean = session.get(CouponBean.class, dicountId);
		if (cBean != null) {
			session.delete(cBean);
			return true;
		}
		return false;
	}

	public CouponBean selectByCode(String Code) { // 依代碼尋找
		Session session = factory.getCurrentSession();
		Query<CouponBean> query = session.createQuery("from CouponBean where code=:inputCode",
				CouponBean.class);
		query.setParameter("inputCode", Code);
		CouponBean result = query.uniqueResult();

		return result;

	}

	public boolean checkCode(String Code) { // 查詢代碼是否存在
		Session session = factory.getCurrentSession();
		Query<CouponBean> query = session.createQuery("from CouponBean", CouponBean.class);
		for(CouponBean cBean : query.list()) {
			if(cBean.getCode().equals(Code)) {
				return true;
			}
		}return false;

	}

}
