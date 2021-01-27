package com.gym.message.dao;

import java.util.List;

import com.gym.message.model.MailboxBean;

public interface MailboxDao {
	List<MailboxBean>  getAllMailboxContent();
	
	public MailboxBean getMailboxContentById(int mailboxId);
	
	void addMailbox(MailboxBean mailbox);
	
	void addMailboxContent(String mb);
	
	int deleteMailbox(int no);

	int updateMailbox(MailboxBean mb) ;
	
	void UpdateArticleId(MailboxBean mb) ;
	
	
}
