package _00_Init.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00_Init.dao.ProductInfoDao;
import _00_Init.util.GlobalService;
import _03_listProducts.model.ProductBean;


@Repository
public class ProductDao implements ProductInfoDao {

	@Autowired
	SessionFactory factory;
	//預設值每頁顯示20筆
	private int recordsPerPage = GlobalService.IMAGE_FILENAME_LENGTH;
	private int MalltotalPages = -1;
	
	String selected = "";
	
	
	public ProductDao() {

	}

	@Override
	// 新增紀錄
	public int saveProduct(ProductBean bean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(bean);
		n++;
		return n;
	}

	@Override
	public void setConnection(Connection con) {
		throw new RuntimeException("本類別為提供此功能");
	}

	// 查詢商品
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getProduct() {
		Session session = factory.getCurrentSession();
		String hql  = "FROM ProductBean";
		List<ProductBean> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, ProductBean> getSupPageProducts(int MallpageNo) {
		Map<Integer, ProductBean> map = new LinkedHashMap<>();
		String hql = "FROM ProductBean";
        Session session = factory.getCurrentSession();
        int startRecordNo = (MallpageNo - 1) * recordsPerPage;
        List<ProductBean> list = session.createQuery(hql)
                      .setFirstResult(startRecordNo)
                      .setMaxResults(recordsPerPage)
                      .getResultList();
		for(ProductBean bean: list) {
			map.put(bean.getP_Id(), bean);
			System.out.println("Map的bean"+ bean);
		}
		return map;
	}

	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*) FROM ProductBean";
		Session session = factory.getCurrentSession();
		count = (Long)session.createQuery(hql).getSingleResult();
		return count;
	}

	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	@Override
	public int getTotalPages() {
		MalltotalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return MalltotalPages;
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;		
	}

	@Override
	public void setSelected(String selected) {
		this.selected  = selected;
		
	}

//	抓取商品資訊準備修改
	@Override
	public ProductBean getSupPageProductsById(int id) {
		ProductBean bean = null;
		Session session = factory.getCurrentSession();
		String hql  = "FROM ProductBean cb WHERE cb.p_Id = :id";
		try {
			bean = (ProductBean)session.createQuery(hql)
									.setParameter("id", id)
									.getSingleResult();
		} catch(NoResultException e) {
			;  // 表示查無紀錄
		}
		return bean;
	}

	//	修改後存入(目前沒有用到)
	@Override
	public void updateSupPageProducts(ProductBean bean) {
		Session session = factory.getCurrentSession();
		session.update(bean);
	}
	//	刪除單筆資料
	@Override
	public void delete(Integer id) {
		Session session = factory.getCurrentSession();
		ProductBean productBean = getSupPageProductsById(id);
		if (productBean != null) {
			session.delete(productBean);
		}
	}

////	修改後存入(有增加照片)
//	@Override
//	public ProductBean getProductBean(Integer id) {
//		Session session = factory.getCurrentSession();
//		return session.get(ProductBean.class, id);
//	}



}
