package com.gym.member.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "member")
public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String member_id;
	private String password;
	private String username;
	private int member_type;
	private String mobile;
	private int gender;
	private Date birth;
	private String address;
	private double point;
	private String facebook_account;
	private String google_account;
	private String detail;
	@JsonIgnore
	private Blob memberphoto;

	public MemberBean() {
	}

	public MemberBean(String member_id, String password, String username, int member_type, String mobile, int gender,
			Date birth, String address, double point, String facebook_account, String google_account, String detail,
			Blob memberphoto) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.username = username;
		this.member_type = member_type;
		this.mobile = mobile;
		this.gender = gender;
		this.birth = birth;
		this.address = address;
		this.point = point;
		this.facebook_account = facebook_account;
		this.google_account = google_account;
		this.detail = detail;
		this.memberphoto = memberphoto;
	}

	@Override
	public String toString() {
		return "MemberBean [" + member_id + "," + password + "," + username + "," + member_type + "," + mobile + ","
				+ gender + "," + birth + "," + address + "," + point + "," + facebook_account + "," + google_account
				+ "," + detail + "," + memberphoto + "]";
	}


	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMember_type() {
		return member_type;
	}

	public void setMember_type(int member_type) {
		this.member_type = member_type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public String getFacebook_account() {
		return facebook_account;
	}

	public void setFacebook_account(String facebook_account) {
		this.facebook_account = facebook_account;
	}

	public String getGoogle_account() {
		return google_account;
	}

	public void setGoogle_account(String google_account) {
		this.google_account = google_account;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Blob getMemberphoto() {
		return memberphoto;
	}

	public void setMemberphoto(Blob memberphoto) {
		this.memberphoto = memberphoto;
	}

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFacotry = (SessionFactory) context.getBean("sessionFactory");

		sessionFacotry.getCurrentSession().beginTransaction();
		Session session = sessionFacotry.getCurrentSession();

		List<MemberBean> member = session.createQuery("FROM MemberBean", MemberBean.class).getResultList();
		System.out.println("member=" + member);

		sessionFacotry.getCurrentSession().getTransaction().commit();
		sessionFacotry.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();

	}

}
