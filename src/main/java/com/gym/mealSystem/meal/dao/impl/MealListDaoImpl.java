package com.gym.mealSystem.meal.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.mealSystem.init.util.GlobalService;
import com.gym.mealSystem.meal.dao.MealListDao;
import com.gym.mealSystem.meal.model.MealCategoryBean;
import com.gym.mealSystem.meal.model.MealListBean;

@Repository
public class MealListDaoImpl implements MealListDao {
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 預設值：每頁6筆
	private int totalPages = -1;

	@Autowired
	SessionFactory factory;

	public Session getSession() {
		return factory.getCurrentSession();
	}

	// 計算餐點總共有幾頁
	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}

	// 搜尋當頁Meallist(取6筆)
	@Override
	@SuppressWarnings("unchecked")
	public Map<Integer, MealListBean> getPageMealLists(int pageNo) {
		Map<Integer, MealListBean> map = new LinkedHashMap<>();
		String hql = "FROM MealListBean";
		Session session = factory.getCurrentSession();
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		List<MealListBean> list = session.createQuery(hql).setFirstResult(startRecordNo).setMaxResults(recordsPerPage)
				.getResultList();
		for (MealListBean bean : list) {
			map.put(bean.getMealId(), bean);
		}
		return map;
	}

	// 搜尋MealListBean有幾筆資料
	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*) FROM MealListBean";
		Session session = factory.getCurrentSession();
		count = (Long) session.createQuery(hql).getSingleResult();
		return count;
	}

	// 搜尋MealList不重複menuGroupId
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getCategory() {
		String hql = "SELECT DISTINCT mealCategoryBean FROM MealListBean";
		Session session = factory.getCurrentSession();
		List<String> list = session.createQuery(hql).getResultList();
		return list;
	}

	// 更新MealList
	@Override
	public void updateMealList(MealListBean mealListBean) {
//		if (mealListBean != null && mealListBean.getMealId() != null && mealListBean.getMealImgName() != null) {
		if (mealListBean != null && mealListBean.getMealId() != null) {
			Session session = getSession();
			session.saveOrUpdate(mealListBean);
//			System.out.println("updateMealList：" + mealListBean);
		}
	}

	// 刪除MealList
	@Override
	public void deleteMealList(Integer id) {
		Session session = getSession();
		MealListBean ml = getMealList(id);
		if (ml != null) {
			ml.setMealCategoryBean(null);
			session.delete(ml);
		}
	}
	
	// 新增MealList
	@Override
	public void saveMealList(MealListBean ml) {
		Session session = getSession();
		session.save(ml);
	}

	// 查詢所有MealList
	@Override
	@SuppressWarnings("unchecked")
	public List<MealListBean> getAllMealList() {
		String hql = "FROM MealListBean";
		Session session = getSession();
		List<MealListBean> list = session.createQuery(hql).getResultList();
		return list;
	}

	// 從MealList搜尋CategoryId
	@Override
	@SuppressWarnings("unchecked")
	public List<MealListBean> getCategoryNameByCategoryId(MealCategoryBean id) {
		String hql = "FROM MealListBean WHERE categoryId = :categoryId";
		List<MealListBean> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("categoryId", id.getCategoryName()).getResultList();
		return list;
	}

	@Override
	public MealListBean getMealList(Integer id) {
		return factory.getCurrentSession().get(MealListBean.class, id);
	}

}
