package com.gym.member.service;

import java.sql.Date;
import com.gym.member.model.MemberBean;

public interface MemberService {
	// 查詢 by member_id
	public MemberBean selectByMember_id(String member_id);
	
    // 登入
	public MemberBean login(String member_id, String password);
	
	// 新增會員 
	public MemberBean createNewMember(String member_id, String password, String register_num);
	
	// 修改會員資料(MemberArea頁面:不含member_type,point,memberphoto,activate,register_num)
	public MemberBean updateMemberInfo(String member_id, String password, String username, int member_height,
			int member_weight, String mobile, int gender, Date birth, String address, String facebook_account,
			String google_account, String detail);
	
	// 修改照片(only 照片)
	public MemberBean updateMemberPhoto(String member_id, String memberphoto);
			
	// 忘記密碼
	public String forgotPwd(String member_id);

	// 啟動帳號(註冊)
	public boolean activateMember(String member_id);
	
	public void updatePoint(MemberBean bean);
}
