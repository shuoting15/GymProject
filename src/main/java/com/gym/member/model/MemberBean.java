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
	private int member_height;
	private int member_weight;
	private String mobile;
	private int gender;
	private Date birth;
	private String address;
	private double point;
	private String facebook_account;
	private String google_account;
	private String detail;
	private String memberphoto;
	private int activate;
	private String register_num;

	public MemberBean() {
	}

	public MemberBean(String member_id, String password, String username, int member_type, int member_height, int member_weight, String mobile, int gender,
			Date birth, String address, double point, String facebook_account, String google_account, String detail,
			String memberphoto, int activate, String register_num) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.username = username;
		this.member_type = member_type;
		this.member_height = member_height;
		this.member_weight = member_weight;
		this.mobile = mobile;
		this.gender = gender;
		this.birth = birth;
		this.address = address;
		this.point = point;
		this.facebook_account = facebook_account;
		this.google_account = google_account;
		this.detail = detail;
		this.memberphoto = memberphoto;
		this.activate = activate;
		this.register_num = register_num;
	}

	@Override
	public String toString() {
		return "MemberBean [" + member_id + "," + password + "," + username + "," + member_type + "," + member_height + ","
				+ member_weight + "," + mobile + ","+ gender + "," + birth + "," + address + "," + point + ","
				+ facebook_account + "," + google_account+ "," + detail + "," + memberphoto + "," + activate + ","
				+ register_num + "]";
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

	public int getMember_height() {
		return member_height;
	}

	public void setMember_height(int member_height) {
		this.member_height = member_height;
	}

	public int getMember_weight() {
		return member_weight;
	}

	public void setMember_weight(int member_weight) {
		this.member_weight = member_weight;
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

	public String getMemberphoto() {
		return memberphoto;
	}

	public void setMemberphoto(String memberphoto) {
		this.memberphoto = memberphoto;
	}

	public int getActivate() {
		return activate;
	}

	public void setActivate(int activate) {
		this.activate = activate;
	}

	public String getRegister_num() {
		return register_num;
	}

	public void setRegister_num(String register_num) {
		this.register_num = register_num;
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
