package com.gym.member.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gym.member.model.ChangePhotoFormBean;
import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;

@Controller
@RequestMapping
public class ChangePhotoController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/changePhoto", method = RequestMethod.POST)
	public String register(HttpServletRequest request, @ModelAttribute ChangePhotoFormBean changePhotoFormBean,
			Model model) throws Exception {
		System.out.println("changePhotoFormBean:member_id:====" + changePhotoFormBean.getMember_id());

		// 若有抓到照片
		if (!changePhotoFormBean.getFile().isEmpty()) {
			// 上傳檔名
			String filename = changePhotoFormBean.getFile().getOriginalFilename();
			System.out.println("changePhotoFormBean:file:====" + filename);
			// TODO 上傳路徑可能要改
//			File filepath = new File(
//					"C:/_drive/_springmvc/workspace/GymProject/src/main/webapp/WEB-INF/views/images" + filename);

//			if (filepath.exists()) {
//				filepath.delete(); // 檔案已存在時先刪除
//			}

			// 將上傳檔案儲存到一個目標檔案當中 TODO 上傳路徑可能要改
			changePhotoFormBean.getFile().transferTo(new File("C:/Users/student/Documents/GitHub/GymProject/src/main/webapp/WEB-INF/views/images" + File.separator + filename));

			// 接收資料
			HttpSession session = request.getSession();
			String member_idSession = (String) session.getAttribute("member_id");
//			System.out.println("member_idSession:" + member_idSession);
//			String member_idFrom = changePhotoFormBean.getMember_id();
//			System.out.println("member_idFrom:" + member_idFrom);

			String memberphoto = "images/" + filename;
			System.out.println("===========memberphoto:" + memberphoto);

			// ================================================================================================
			// 呼叫Model
			MemberBean newinfo = memberService.updateMemberPhoto(member_idSession, memberphoto);
			System.out.println("===========(改圖檔後)newinfo= " + newinfo);
			session.setAttribute("memberphoto", newinfo.getMemberphoto());

			// 跳轉頁面
			return "member/memberarea";
		} else {
			// TODO changePhotoFormBean.getFile().isEmpty()若有抓到照片暫回首頁
			return "template01";
		}
	}
}
