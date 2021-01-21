package com.gym.news.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="NewsAuthor")
public class AuthorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	private String  name;
	@OneToMany(mappedBy="authorBean")
	private Set<NewsBean> news = new LinkedHashSet<>();
	
	public AuthorBean(Integer id, String name) {
		this.id = id;
		this.name = name;

	}
	public AuthorBean() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Set<NewsBean> getNews() {
		return news;
	}
	public void setNews(Set<NewsBean> news) {
		this.news = news;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}