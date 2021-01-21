package com.gym.news.dao;

import java.util.List;

import com.gym.news.model.AuthorBean;
import com.gym.news.model.NewsBean;


public interface NewsDao {

	List<NewsBean>  getAllNews(); 
	
	List<String>  getAllNewsCategories();

	List<NewsBean>  getNewsByCategory(String newsCategory);
	
	public NewsBean getNewsById(int newsId);

	void  addNewsone(NewsBean newsone);
	
	AuthorBean  getAuthorById(int authorId);
	
	List<AuthorBean>  getAuthorList();

	void deleteNewsById(int id);

}
