package com.gym.message.service;

import java.util.List;

import com.gym.message.model.MailboxBean;

public interface MailboxService {
	
List<MailboxBean>  getAllMailboxContent();
	
	public MailboxBean getMailboxContentById(int mailboxId);
	
	void addMailbox(MailboxBean mailbox);
	
	int deleteMailbox(int no);

	int updateMailbox(MailboxBean mb) ;

	void addMailboxContent(String mb);


	
	void UpdateArticleId(MailboxBean mb) ;
	
}
