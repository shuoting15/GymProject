package com.gym.news.dao;

import java.util.List;

import com.gym.coach.model.CoachBean;
import com.gym.news.model.NewsBean;
import com.gym.news.model.NewsPlaylistBean;


public interface NewsDao {

	List<NewsBean>  getAllNews(); 
	
	List<String>  getAllNewsCategories();

	List<NewsBean>  getNewsByCategory(String newsCategory);
	
	public NewsBean getNewsById(int newsId);

	void  addNewsone(NewsBean newsone);
	
	CoachBean  getAuthorById(int authorId);
	
	List<CoachBean>  getAuthorList();

	void deleteNewsById(int newsId);

	List<NewsBean> getNewsByViews();
	
	void update(NewsBean newsbean);

	
	
	public List<NewsBean> getSearchNews(String newskw);

	void saveintoplayliste(NewsPlaylistBean nb);

	List<NewsBean> getplaylistNews(String memberid);
	
	
}
