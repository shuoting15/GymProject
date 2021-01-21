package com.gym.news.service;

import java.util.List;

import com.gym.news.model.AuthorBean;
import com.gym.news.model.NewsBean;


public interface NewsService {
	List<NewsBean>  getAllNews(); 

	List<String>  getAllNewsCategories();

	List<NewsBean>  getNewsByCategory(String category);
	
	public NewsBean getNewsById(int newsId);
	void  addNewsone(NewsBean newsone);
	
	AuthorBean  getAuthorById(int authorId);
	
	List<AuthorBean>  getAuthorList();
	void deleteNewsById(int Id);
}
