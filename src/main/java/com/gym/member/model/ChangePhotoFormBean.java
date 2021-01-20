package com.gym.member.model;

import org.springframework.web.multipart.MultipartFile;

public class ChangePhotoFormBean {
	
	private String member_id;
    private MultipartFile file;
    
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
    
}
