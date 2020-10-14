package _03_listProducts.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _03_listProducts.controller.RetrieveProductController;
import _03_listProducts.dao.ProductDao;
import _03_listProducts.model.ProductBean;

@Repository
public class ProductDaoImpl_H implements ProductDao {
	
	private static final int MAX_FILENAME_LENGTH = 255;
	private int recordsPerPage = RetrieveProductController.RECORDS_PER_PAGE; // 預設值：每頁2筆
	private int totalPages = -1;
	
	String selected = "";
	
	@Autowired
	SessionFactory factory;
	
	public ProductDaoImpl_H() {
		
	}
	
	//取全部的商品
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, ProductBean> getAllProducts() {
//		List<ProductBean> list = new LinkedList<>();
		Map<Integer, ProductBean> map = new LinkedHashMap<>();
		String hql = "FROM ProductBean";
		Session session = factory.getCurrentSession();
//		list = session.createQuery(hql).getResultList();
//		return list;
		Query query = session.createQuery(hql);
		List<ProductBean> list = query.getResultList();
		for(ProductBean bean: list) {
			map.put(bean.getP_Id(), bean);
		}
		return map;
		
//		Map<Integer, ProductBean> map = new LinkedHashMap<>();
//		String hql;
//		if (category == null)
//			hql = "FROM ProductBean";
//		else hql = "FROM ProductBean bb WHERE bb.p_Category = :category";
//		Session session = factory.getCurrentSession();
//		int startRecordNo = (pageNo - 1) * recordsPerPage;
//		Query query = session.createQuery(hql);
//		query.setFirstResult(startRecordNo)
//		.setMaxResults(recordsPerPage);
//		if (category != null)
//			query.setParameter("category", category);
//		List<ProductBean> list = query.getResultList();
//		for(ProductBean bean: list) {
//			map.put(bean.getP_Id(), bean);
//		}
//		return map;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, ProductBean> getAllProducts(String category, int pageNo) {
//		List<ProductBean> list = new LinkedList<>();
//		String hql = "FROM ProductBean";
//		Session session = factory.getCurrentSession();
//		list = session.createQuery(hql).getResultList();
//		return list;
		
		Map<Integer, ProductBean> map = new LinkedHashMap<>();
		String hql;
		if (category == null)
			hql = "FROM ProductBean";
		else hql = "FROM ProductBean bb WHERE bb.p_Category = :category";
        Session session = factory.getCurrentSession();
        int startRecordNo = (pageNo - 1) * recordsPerPage;
        Query query = session.createQuery(hql);
        query.setFirstResult(startRecordNo)
        .setMaxResults(recordsPerPage);
        if (category != null)
        	query.setParameter("category", category);
        List<ProductBean> list = query.getResultList();
		for(ProductBean bean: list) {
			map.put(bean.getP_Id(), bean);
		}
		return map;
		
	}

	//取全部的類別
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllCategories() {
		String hql = "SELECT DISTINCT b.p_Category FROM ProductBean b";
		Session session = factory.getCurrentSession();
		List<String> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	//依類別取商品
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, ProductBean> getProductsByCategory(String category, int pageNo) {
//		String hql = "FROM ProductBean bb WHERE bb.p_Category = :category";
//		List<ProductBean> list = new ArrayList<>();
//		Session session = factory.getCurrentSession();
//		list = session.createQuery(hql)
//					.setParameter("category", category)
//					.getResultList();
		
		Map<Integer, ProductBean> map = new LinkedHashMap<>();
		String hql = "FROM ProductBean bb WHERE bb.p_Category = :category";
        Session session = factory.getCurrentSession();
        int startRecordNo = (pageNo - 1) * recordsPerPage;
        List<ProductBean> list = session.createQuery(hql)
        		.setParameter("category", category)
                .setFirstResult(startRecordNo)
                .setMaxResults(recordsPerPage)
                .getResultList();
		for(ProductBean bean: list) {
			map.put(bean.getP_Id(), bean);
		}
		
		return map;
	}

	//依id取商品
	@Override
	public ProductBean getProductById(int p_Id) {
		Session session = factory.getCurrentSession();
		ProductBean pb = session.get(ProductBean.class, p_Id);
		return pb;
	}

	//類別下拉選單
	@Override
	public String getCategoryTag() {
		String ans = "";
		List<String> list = getAllCategories();
		ans += "<SELECT name='category'>";
		for (String cate : list) {
			if (cate.equals(selected)) {
				ans += "<option value='" + cate + "' selected>" + cate + "</option>";
			} else {
				ans += "<option value='" + cate + "'>" + cate + "</option>";
			}
		}
		ans += "</SELECT>";
		return ans;
	}

	// 新增一筆記錄---
	@Override
	public int saveProduct(ProductBean bean) {
		int n = 0;
        Session session = factory.getCurrentSession();
        String filename = bean.getP_FileName(); 
        String filename1 = bean.getP_FileName1(); 
        String filename2 = bean.getP_FileName2(); 
        String filename3 = bean.getP_FileName3(); 
        if (filename.length() > MAX_FILENAME_LENGTH) {
        	String ext = filename.substring(filename.lastIndexOf("."));
        	String shortFilefname = filename.substring(0, MAX_FILENAME_LENGTH-ext.length()) + ext;
        	System.out.println(shortFilefname);
        	bean.setP_FileName(shortFilefname);
        };
        if (filename1.length() > MAX_FILENAME_LENGTH) {
        	String ext = filename1.substring(filename1.lastIndexOf("."));
        	String shortFilefname = filename1.substring(0, MAX_FILENAME_LENGTH-ext.length()) + ext;
        	System.out.println(shortFilefname);
        	bean.setP_FileName1(shortFilefname);
        };
        if (filename2.length() > MAX_FILENAME_LENGTH) {
        	String ext = filename2.substring(filename2.lastIndexOf("."));
        	String shortFilefname = filename2.substring(0, MAX_FILENAME_LENGTH-ext.length()) + ext;
        	System.out.println(shortFilefname);
        	bean.setP_FileName2(shortFilefname);
        };
        if (filename3.length() > MAX_FILENAME_LENGTH) {
        	String ext = filename3.substring(filename3.lastIndexOf("."));
        	String shortFilefname = filename3.substring(0, MAX_FILENAME_LENGTH-ext.length()) + ext;
        	System.out.println(shortFilefname);
        	bean.setP_FileName3(shortFilefname);
        };
        session.save(bean);
        n++;
        return n;
	}

	//修改一筆資料
	@Override
	public int updateProduct(ProductBean bean, long sizeInBytes) {
		int n = 0;
        ProductBean b0 = null;
        Session session = factory.getCurrentSession();
        b0 = session.get(ProductBean.class, bean.getP_Id());
        bean.setP_Name(b0.getP_Name());
        bean.setP_Category(b0.getP_Category());
        bean.setP_Cover(b0.getP_Cover());
        bean.setP_FileName(b0.getP_FileName());
        //對修改畫面 jsp 送來的資料有那些
        ///////DAO剩這裡，修改項目serive 還沒修改
        session.evict(b0);
        session.saveOrUpdate(bean);
        n++;
        return n;
	}
	
	// 依bookId來刪除單筆記錄
		@Override
		public int deleteProduct(int p_Id) {
			int n = 0;
	        Session session = factory.getCurrentSession();
	        ProductBean bb = new ProductBean();
	        bb.setP_Id(p_Id);
	        session.delete(bb);
	        n++;
	        return n;
		}

		//瀏覽頁相關
		@SuppressWarnings("unchecked")
		@Override
		public Map<Integer, ProductBean> getPageProducts(int pageNo) {
			Map<Integer, ProductBean> map = new LinkedHashMap<>();
			String hql = "FROM ProductBean";
	        Session session = factory.getCurrentSession();
	        int startRecordNo = (pageNo - 1) * recordsPerPage;
	        List<ProductBean> list = session.createQuery(hql)
	                      .setFirstResult(startRecordNo)
	                      .setMaxResults(recordsPerPage)
	                      .getResultList();
			for(ProductBean bean: list) {
				map.put(bean.getP_Id(), bean);
			}
			return map;
		}
		@Override
		public long getRecordCounts(String category) {
			long count = 0; // 必須使用 long 型態
			String hql;
			if (category != null)
				hql = "SELECT count(*) FROM ProductBean bb WHERE bb.p_Category = :category";
			else hql = "SELECT count(*) FROM ProductBean";
			Session session = factory.getCurrentSession();
			Query query = session.createQuery(hql);
			if (category != null)
				query.setParameter("category", category);
			count = (Long) query.getSingleResult();
			return count;
		}

		@Override
		public int getRecordsPerPage() {
			return recordsPerPage;
		}

		// 計算販售的商品總共有幾頁
		@Override
		public int getTotalPages(String category) {
			// 注意下一列敘述的每一個型態轉換
			totalPages = (int) (Math.ceil(getRecordCounts(category) / (double) recordsPerPage));
			return totalPages;
		}

		@Override
		public void setRecordsPerPage(int recordsPerPage) {
			this.recordsPerPage = recordsPerPage;
		}

		@Override
		public void setSelected(String selected) {
			this.selected = selected;
		}

}