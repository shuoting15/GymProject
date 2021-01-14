package com.gym.member.service.impl;

import java.sql.Blob;
import java.sql.Date;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gym.member.dao.MemberDao;
import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	public MemberServiceImpl() {
	}

	// 查詢 by member_id
	@Override
	public MemberBean selectByMember_id(String member_id) {
		return memberDao.selectByMember_id(member_id);
	}

	// 登入會員
	@Override
	public MemberBean login(String member_id, String password) {
		MemberBean bean = memberDao.selectByMember_id(member_id);
		if (bean != null) {
			if (password != null && password.length() != 0) {
				if (password.equals((bean.getPassword()))) {
					return bean;
				}
			}
		}
		return null;
	}

	// 新增會員(註冊)
	@Override
	public MemberBean createNewMember(String member_id, String password) {
		MemberBean alreadyMember = memberDao.selectByMember_id(member_id);
		MemberBean newMember = new MemberBean();
		if (alreadyMember == null) {
			newMember.setMember_id(member_id);
			newMember.setPassword(password);

			// 取@前的字當預設會員名稱
			String beforeAt = member_id.substring(0, member_id.indexOf("@"));
			System.out.println("預設會員名稱(username):"+beforeAt);
			newMember.setUsername(beforeAt);
			// TODO 找預設照片
			// newMember.setMemberphoto("/images/defaultmemberphoto.jpg");
			// TODO 看看還要存甚麼
			boolean yesOrNo = memberDao.insert(newMember);
			System.out.println("註冊成功: " + yesOrNo);
		} else {
			return null;
		}
		return newMember;
	}

	// 修改會員資料(MemberArea頁面:不含照片,member_type,point)
	@Override
	public MemberBean updateMemberInfo(String member_id, String password, String username, String mobile, int gender,
			Date birth, String address, String facebook_account, String google_account, String detail) {
		// MemberBean bean = this.login(account, password);
		MemberBean bean = memberDao.selectByMember_id(member_id);
		MemberBean result = null;
		if (bean != null) {
			if (password != null) {
				result = memberDao.update(bean.getMember_id(), password, username, bean.getMember_type(), mobile, gender,
						birth, address, bean.getPoint(), facebook_account, google_account, detail,
						bean.getMemberphoto());
				System.out.println("會員更新後資料:"+result);
			}
		}
		return result;

	}
//
//	// 修改照片(only 照片)
//	public MemberBean updateMemberPhoto(String member_id, String password, String username, int member_type, String mobile,
//			int gender, Date birth, String address, double point, String facebook_account, String google_account,
//			String detail, Blob memberphoto) {
//		MemberBean bean = memberDao.selectByMember_id(member_id);
//		MemberBean result = null;
//		if (bean != null) {
//			result = memberDao.update(member_id, bean.getPassword(), bean.getUsername(), bean.getMember_type(),
//					bean.getMobile(), bean.getGender(), bean.getBirth(), bean.getAddress(), bean.getPoint(),
//					bean.getFacebook_account(), bean.getGoogle_account(), bean.getDetail(), memberphoto);
//			System.out.println(result);
//		}
//		return result;
//
//	}
//
//	// 忘記密碼
//	public String forgotPd(String account) {
//		MemberBean bean = selectByMember_id(account);
//		String afteremail = null;
//		if (bean != null) {
//			if ((bean.getMember_id()).equals(account)) {
//				EmailPd gmailSendMail = new EmailPd(memberDao);
//				afteremail = gmailSendMail.GmailsendEmail(account);
//			}
//		}
//		return afteremail;
//	}
//
//	// 修改密碼
//	public boolean changePassword(String username, String oldPassword, String newPassword) {
//		MemberBean bean = this.login(username, oldPassword);
//		if (bean != null) {
//			if (newPassword != null && newPassword.length() != 0) {
//				MemberBean result = memberDao.update(bean.getMember_id(), newPassword, bean.getFacebook_account(),
//						bean.getGoogle_account(), bean.getMember_type(), bean.getUsername(), bean.getGender(),
//						bean.getMobile(), bean.getAddress(), bean.getBirth(), bean.getDetail(), bean.getMemberphoto());
//				System.out.println(result);
//				return true;
//			}
//		}
//		return false;
//	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		MemberServiceImpl memberService = (MemberServiceImpl) context.getBean("memberServiceImpl");

		// Login
		MemberBean bean = memberService.login("andy@gmail.com", "eeit121");
		System.out.println("bean=" + bean);

//		// 新增會員
//		MemberBean member = memberService.createNewMember("flora@gmail.com", "eeit94");
//		System.out.println("memberBean=" + member);

		// //changePassword
		// boolean yorN =
		// memberService.changePassword("priceskitchen301@gmail.com",
		// "k9y9F7", "eeit94");
		// System.out.println("result=" + yorN);

		// //忘記密碼
		// String afteremail =
		// memberService.forgotPd("priceskitchen301@gmail.com");
		// System.out.println(afteremail);

		// // 修改會員資料
		// MemberBean newinfo =
		// memberService.updateMemberInfo("priceskitchen@gmail.com",
		// "eeit94", "PKManager",null,0,null,null,null,null,"test1","");
		// System.out.println(newinfo);

//		// selectById
//		MemberBean member = memberService.selectByMember_id("priceskitchen@gmail.com");
//		System.out.println(member);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();

	}
}
