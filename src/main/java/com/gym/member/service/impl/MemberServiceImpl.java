package com.gym.member.service.impl;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.member.common.CreatePassword;
import com.gym.member.common.GmailSendMail;
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
	public MemberBean createNewMember(String member_id, String password, String register_num) {
		MemberBean alreadyMember = memberDao.selectByMember_id(member_id);
		MemberBean newMember = new MemberBean();
		if (alreadyMember == null) {
			newMember.setMember_id(member_id);
			newMember.setPassword(password);
			newMember.setRegister_num(register_num);
			
			// 取@前的字當預設會員名稱
			String beforeAt = member_id.substring(0, member_id.indexOf("@"));
			System.out.println("預設會員名稱(username):"+beforeAt);
			newMember.setUsername(beforeAt);
			// 預設照片
			newMember.setMemberphoto("images/memberDefault.png");
			// TODO 看看還要存甚麼
			boolean yesOrNo = memberDao.insert(newMember);
			System.out.println("新加會員(尚未驗證): " + yesOrNo);
		} else {
			return null;
		}
		return newMember;
	}

	// 修改會員資料(MemberArea頁面:不含member_type,point,memberphoto,activate,register_num)
	@Override
	public MemberBean updateMemberInfo(String member_id, String password, String username, int member_height,
			int member_weight, String mobile, int gender, Date birth, String address, String facebook_account,
			String google_account, String detail) {
		
		MemberBean bean = memberDao.selectByMember_id(member_id);
		MemberBean result = null;
		if (bean != null) {
			if (password != null) {
				result = memberDao.update(bean.getMember_id(), password, username, bean.getMember_type(), member_height,
						member_weight, mobile, gender, birth, address, bean.getPoint(), facebook_account, google_account,
						detail, bean.getMemberphoto(), bean.getActivate(), bean.getRegister_num());
				System.out.println("會員更新後資料:"+result);
			}
		}
		return result;

	}

	// 修改照片(only 照片)
	@Override
	public MemberBean updateMemberPhoto(String member_id, String memberphoto) {
		MemberBean bean = memberDao.selectByMember_id(member_id);
		MemberBean result = null;
		if (bean != null) {
			result = memberDao.update(member_id, bean.getPassword(), bean.getUsername(), bean.getMember_type(),
					bean.getMember_height(), bean.getMember_weight(), bean.getMobile(), bean.getGender(), bean.getBirth(),
					bean.getAddress(), bean.getPoint(), bean.getFacebook_account(), bean.getGoogle_account(),
					bean.getDetail(), memberphoto, bean.getActivate(), bean.getRegister_num());
			System.out.println(result);
		}
		return result;

	}

	// 忘記密碼
	@Override
	public String forgotPwd(String member_id) {
		MemberBean bean = selectByMember_id(member_id);
		String emailOrNot = null;
		if (bean != null) {
			if ((bean.getMember_id()).equals(member_id)) {
				// 做一個臨時密碼
				CreatePassword pwdCreator = new CreatePassword();// 呼叫
				String tempPwd = pwdCreator.getRandomPassword();
				// 將臨時密碼代替原密碼存入資料庫
				MemberBean tempPwdMemberBean = memberDao.update(member_id, tempPwd, bean.getUsername(),
						bean.getMember_type(),bean.getMember_height(), bean.getMember_weight(),
						bean.getMobile(), bean.getGender(), bean.getBirth(), bean.getAddress(), bean.getPoint(),
						bean.getFacebook_account(), bean.getGoogle_account(), bean.getDetail(),
						bean.getMemberphoto(), bean.getActivate(), bean.getRegister_num());
				System.out.println("暫時密碼為:" + tempPwd);
				System.out.println("會員資料已更新(資料庫):" + tempPwdMemberBean);

				// 寄出暫時密碼
				GmailSendMail gmailSendMail = new GmailSendMail();
				emailOrNot = gmailSendMail.sendTempPassword(member_id, tempPwd);
			}
		}
		return emailOrNot;
	}
	
	// 啟動帳號(註冊)
		@Override
		public boolean activateMember(String member_id) {
			boolean ans = false;
			MemberBean memberbean = memberDao.selectByMember_id(member_id);
			memberbean = memberDao.update(member_id, memberbean.getPassword(), memberbean.getUsername(), memberbean.getMember_type(),
					memberbean.getMember_height(), memberbean.getMember_weight(), memberbean.getMobile(), memberbean.getGender(),
					memberbean.getBirth(), memberbean.getAddress(), memberbean.getPoint(), memberbean.getFacebook_account(),
					memberbean.getGoogle_account(), memberbean.getDetail(), memberbean.getMemberphoto(), 1, memberbean.getRegister_num());
			System.out.println("memberbean.getActivate():" + memberbean.getActivate());
			if (memberbean.getActivate() == 1) {
				ans = true;
			}
			return ans;
		}


	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		MemberServiceImpl memberService = (MemberServiceImpl) context.getBean("memberServiceImpl");

		// selectById
//		MemberBean member = memberService.selectByMember_id("sdx@gmail.com");
//		System.out.println(member);

		// Login
//		MemberBean bean = memberService.login("andy@gmail.com", "eeit121");
//		System.out.println("bean=" + bean);

		// 新增會員
//		MemberBean member = memberService.createNewMember("testxxx@gmail.com", "eeit121");
//		System.out.println("memberBean=" + member);

		// 修改會員資料
		MemberBean newinfo = memberService.updateMemberInfo("sdx@gmail.com", "ooxx123", "滷十二長工",120,120,"0922123456", 2, null,
				"101大樓", "", "", "最重要的事就是吃和咩咩叫");
		System.out.println(newinfo);

		// 忘記密碼
//		String email = memberService.forgotPwd("gymproject121@gmail.com");
//		System.out.println(email);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();

	}

	@Override
	public void updatePoint(MemberBean bean) {
		memberDao.updatePoint(bean);
		
	}

	@Override
	public void addPoint(String memberId, Double point) {
		memberDao.addPoint(memberId, point);
	}

	public List<MemberBean> selectAll() {
		// TODO Auto-generated method stub
		return memberDao.selectAll();
	}
}
