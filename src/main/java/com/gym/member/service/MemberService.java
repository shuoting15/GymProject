package com.gym.member.service;

import java.sql.Date;
import com.gym.member.model.MemberBean;

public interface MemberService {
	// 查詢 by member_id
	public MemberBean selectByMember_id(String member_id);
    //登入
	public MemberBean login(String member_id, String password);
    //新增會員
	public MemberBean createNewMember(String member_id, String password);
	// 修改會員資料(不含照片)
	public MemberBean updateMemberInfo(String member_id, String password, String username, String mobile, int gender,
			Date birth, String address, String facebook_account, String google_account, String detail);
}
