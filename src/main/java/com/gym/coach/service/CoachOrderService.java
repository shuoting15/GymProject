package com.gym.coach.service;

import java.util.List;

import com.gym.coach.model.ClanderBean;
import com.gym.coach.model.CoachOrderBean;

public interface CoachOrderService {
	
	public List<ClanderBean> findTimeByCoachId(int coachId);
	
	void  addCoachTime(CoachOrderBean coachOrderBean);
	
	public CoachOrderBean getCoachTimeById(int orderId);
	
	public void orderCoachTime(CoachOrderBean coachOrderBean);
	
	void deleteCoachTime(int coachId);
	
	List<CoachOrderBean> findBookingByMemberId(String memberId);
	
	void cancelBooking(CoachOrderBean coachOrderBean);
	
	public void finishBooking(CoachOrderBean coachOrderBean);
	
	List<CoachOrderBean> findBookingByCoachId(int coachId);
}
