package com.gym.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gym.news.dao.NewsDao;
import com.gym.news.model.AuthorBean;
import com.gym.news.model.NewsBean;
import com.gym.news.service.NewsService;


@Service
@EnableTransactionManagement
public class NewsServiceImpl implements NewsService {
	@Autowired
	NewsDao dao;

	@Transactional
	@Override
	public List<NewsBean> getAllNews() {
		return dao.getAllNews();
	}


	@Transactional
	@Override
	public List<String> getAllNewsCategories() {
		return dao.getAllNewsCategories();
	}

	@Transactional
	@Override
	public List<NewsBean> getNewsByCategory(String newsCategory) {
		return dao.getNewsByCategory(newsCategory);
	}


	@Transactional
	@Override
	public NewsBean getNewsById(int newsproductId) {
		return dao.getNewsById(newsproductId);
	}
	
	@Transactional
	@Override
	public void addNewsone(NewsBean newsone) {
		dao.addNewsone(newsone);
	}
	@Transactional
	@Override
	public AuthorBean getAuthorById(int authorId) {
		return dao.getAuthorById(authorId);
	}
	@Transactional
	@Override
	public List<AuthorBean> getAuthorList() {
		return dao.getAuthorList();
	}


	@Override
	public void deleteNewsById(int id) {
		dao.deleteNewsById(id);
		
	}

}
