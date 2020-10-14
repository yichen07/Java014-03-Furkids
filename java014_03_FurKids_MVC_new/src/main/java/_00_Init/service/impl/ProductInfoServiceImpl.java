package _00_Init.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _00_Init.dao.ProductInfoDao;
import _00_Init.service.ProductInfoService;
import _03_listProducts.model.ProductBean;


@Transactional
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
	@Autowired
	ProductInfoDao dao;

	@Autowired
	SessionFactory factory;

	// 新增後存入
	@Override
	public int saveProduct(ProductBean mb) {
		int count = 0;
		dao.saveProduct(mb);
		count++;
		return count;
	}

	@Override
	public List<ProductBean> getProduct() {
		return dao.getProduct();
	}

	@Override
	public Map<Integer, ProductBean> getSupPageProducts(int pageNo) {
		Map<Integer, ProductBean> map = null;
		map = dao.getSupPageProducts(pageNo);
		return map;
	}

	@Override
	public long getRecordCounts() {
		return dao.getRecordCounts();
	}

	@Override
	public int getRecordsPerPage() {
		return dao.getRecordsPerPage();
	}

	@Override
	public int getTotalPages() {
		int n = 0;
		n = dao.getTotalPages();
		return n;
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		dao.setRecordsPerPage(recordsPerPage);

	}

	@Override
	public void setSelected(String category) {
		dao.setSelected(category);

	}
	
	


	@Override
	public ProductBean getSupPageProductsById(int id) {

		return dao.getSupPageProductsById(id);
	}

	@Override
	public void updateSupPageProducts(ProductBean bean) {
		dao.updateSupPageProducts(bean);
	}

	@Override
	public void delete(Integer id) {
			dao.delete(id);
	}

//	@Override
//	public ProductBean getProductBean(Integer id) {
//		return dao.getProductBean(id);
//	}

}
