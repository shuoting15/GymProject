package com.gym.message.dao;

import java.util.List;

import com.gym.message.model.MessageBean;

public interface MessageDao {
	
	List<MessageBean>  getAllMessage(); 
	
	public MessageBean getMessageById(int articleId);
	
	List<String>  getAllKanbanName();
	
	List<MessageBean>  getMessageByKanbanName(String kanbanName);
	
	void addMessage(MessageBean message);
	
	MessageBean getKanbannameById(int articleId);
	
	List<MessageBean> getKanbannameList();
	
	int deleteMessage(int no);
	
	int updateMessage(MessageBean mbean, long sizeInBytes) ;
	
	public MessageBean getMemberidById(int articleId);
	
	List<MessageBean> SearchmessageById(String keyword);
}
