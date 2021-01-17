package com.gym.coach.dao;

import java.util.Date;
import java.util.List;

import com.gym.coach.model.ClanderBean;
import com.gym.coach.model.CoachBean;
import com.gym.coach.model.CoachOrderBean;

public interface CoachOrderDao {
	public List<ClanderBean> findTimeByCoachId(int coachId);
	void  addCoachTime(CoachOrderBean coachOrderBean);
	List<CoachOrderBean>  checkCoachTime(int coachid,Date OrderStartTime);
	CoachOrderBean getCoachTimeById(int orderId);
	void orderCoachTime(CoachOrderBean coachOrderBean);
	void deleteCoachTime(int orderId);
	List<CoachOrderBean> findBookingByMemberId(String memberId);
	void cancelBooking(CoachOrderBean coachOrderBean);
	void finishBooking(CoachOrderBean coachOrderBean);
	List<CoachOrderBean> findBookingByCoachId(int coachId);
}
