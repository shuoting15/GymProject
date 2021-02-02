package com.gym.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gym.coach.model.CoachBean;
import com.gym.news.dao.NewsDao;
import com.gym.news.model.NewsBean;
import com.gym.news.model.NewsPlaylistBean;
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
	public List<NewsBean> getSearchNews(String newskw) {
		return dao.getSearchNews(newskw);
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
		System.out.println();
		return dao.getNewsById(newsproductId);
	}
	
	@Transactional
	@Override
	public void addNewsone(NewsBean newsone) {
		dao.addNewsone(newsone);
	}
	@Transactional
	@Override
	public CoachBean getAuthorById(int authorId) {
		return dao.getAuthorById(authorId);
	}
	@Transactional
	@Override
	public List<CoachBean> getAuthorList() {
		return dao.getAuthorList();
	}

	@Transactional
	@Override
	public void deleteNewsById(int newsId) {
		dao.deleteNewsById(newsId);
		
	}
	
	@Transactional
	@Override
	public List<NewsBean> getNewsByViews() {
		return dao.getNewsByViews();
	}

	@Transactional
	@Override
	public void update(NewsBean newsbean) {
		dao.update(newsbean);
		
	}

	
	@Transactional
	@Override
	public void saveintoplaylist(NewsPlaylistBean nb){
		dao.saveintoplayliste(nb);
	}
	
	@Transactional
	@Override
	public List<NewsBean> getplaylistNews(String memberid){
		return dao.getplaylistNews(memberid);
	}
}
