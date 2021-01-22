package com.gym.message.service;

import java.util.List;

import com.gym.message.model.MessageBean;

public interface MessageService {
	List<MessageBean> getAllMessage();


	List<String> getAllKanbanName();

	public MessageBean getMessageById(int articleId);

	List<MessageBean> getMessageByKanbanName(String kanbanName);

	void addMessage(MessageBean message);

	MessageBean getKanbannameById(int articleId);

	List<MessageBean> getKanbannameList();
	
	int deleteMessage(int no);
	
	int updateMessage(MessageBean mbean, long sizeInBytes) ;
	
	 List<MessageBean> SearchmessageById(String keyword);
}
