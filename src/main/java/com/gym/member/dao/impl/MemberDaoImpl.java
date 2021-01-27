package com.gym.member.dao.impl;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import com.gym.member.dao.MemberDao;
import com.gym.member.model.MemberBean;
import com.gym.product.model.ProductBean;


@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SessionFactory sessionFactory;

	public MemberDaoImpl() {
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// 新增
	@Override
	public boolean insert(MemberBean bean) {
		if (bean != null) {
			MemberBean alreadyMember = this.selectByMember_id(bean.getMember_id());
			if (alreadyMember == null) {
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	// 刪除
	@Override
	public boolean delete(String member_id) {
		MemberBean bean = this.selectByMember_id(member_id);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	// 更新
	@Override
	public MemberBean update(String member_id, String password, String username, int member_type, int member_height,
			int member_weight, String mobile, int gender, Date birth, String address, double point, String facebook_account,
			String google_account, String detail, String memberphoto, int activate, String register_num) {

		MemberBean bean = this.selectByMember_id(member_id);
		if (bean != null) {
			bean.setMember_id(member_id);
			bean.setPassword(password);
			bean.setUsername(username);
			bean.setMember_type(member_type);
			bean.setMember_height(member_height);
			bean.setMember_weight(member_weight);
			bean.setMobile(mobile);
			bean.setGender(gender);
			bean.setBirth(birth);
			bean.setAddress(address);
			bean.setPoint(point);
			bean.setFacebook_account(facebook_account);
			bean.setGoogle_account(google_account);
			bean.setDetail(detail);
			bean.setMemberphoto(memberphoto);
			bean.setActivate(activate);
			bean.setRegister_num(register_num);
		}
		return bean;
	}

	// 查詢 by member_id
	@Override
	public MemberBean selectByMember_id(String member_id) {
		return getSession().get(MemberBean.class, member_id);
	}

	// 查全部
	@Override
	public List<MemberBean> selectAll() {
		return this.getSession().createQuery("FROM MemberBean", MemberBean.class).getResultList();
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFacotry = (SessionFactory) context.getBean("sessionFactory");

		sessionFacotry.getCurrentSession().beginTransaction();
		MemberDao memberDAO = (MemberDao) context.getBean("memberDaoImpl");

		// selectAll
		List<MemberBean> selects = memberDAO.selectAll();
		System.out.println("selects=" + selects);

//		 //selectByMember_id
//		 MemberBean member=memberDAO.selectByMember_id("sdx@gmail.com");
//		 System.out.println("member="+member);

//		 // insert
//		 MemberBean bean = new MemberBean();
//		 bean.setMember_id("test@gmail.com");
//		 bean.setPassword("123");
//		 boolean yorN = memberDAO.insert(bean);
//		 System.out.println("insert:" + yorN);

		// update
		MemberBean update = memberDAO.update("gymproject121@gmail.com", "eeit121", "管理者", 1, 180, 80, "0911111111", 0, null, "",
				100.5, "", "", "願風指引你的方向", null, 1, "hu75d3");
		System.out.println("update=" + update);

//		 // delete
//		 boolean delete = memberDAO.delete("test@gmail.com");
//		 System.out.println("delete:" + delete);

		sessionFacotry.getCurrentSession().getTransaction().commit();
		sessionFacotry.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();

	}

	@Override
	public void updatePoint(MemberBean bean) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(bean);	
	}
	
	@Override
	public void addPoint(String memberId,Double point) {
		Session session = sessionFactory.getCurrentSession();
		MemberBean bean=session.get(MemberBean.class, memberId);
		double totalPoint = bean.getPoint()+point;
		bean.setPoint(totalPoint);
		session.saveOrUpdate(bean);	
	}

}
