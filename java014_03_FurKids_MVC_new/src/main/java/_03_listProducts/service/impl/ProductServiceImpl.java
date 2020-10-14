package _03_listProducts.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _03_listProducts.dao.ProductDao;
import _03_listProducts.model.ProductBean;
import _03_listProducts.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao dao;
	
	//查詢全部記錄
	@Transactional
	@Override
	public Map<Integer, ProductBean> getAllProducts() {
		return dao.getAllProducts();
	}
	@Transactional
	@Override
	public Map<Integer, ProductBean> getAllProducts(String category, int pageNo) {
		return dao.getAllProducts(category, pageNo);
	}
	//取類別清單
	@Transactional
	@Override
	public List<String> getAllCategories() {
		return dao.getAllCategories();
	}
	//依類別取商品資料
	@Transactional
	@Override
	public Map<Integer, ProductBean> getProductsByCategory(String category, int pageNo) {
		return dao.getProductsByCategory(category, pageNo);
	}
	//依id取商品資料
	@Transactional
	@Override
	public ProductBean getProductById(int p_Id) {
		return dao.getProductById(p_Id);
	}
	//類別下拉選單
	@Transactional
	@Override
	public String getCategoryTag() {
		String tag = dao.getCategoryTag();
        return tag;
	}
	//新增一筆記錄
	@Transactional
	@Override
	public int saveProduct(ProductBean bean) {
		int n = 0;
            n = dao.saveProduct(bean);
        return n;
	}
	//修改一筆記錄
	@Transactional
	@Override
	public int updateProduct(ProductBean bean, long sizeInBytes) {
		int n = 0;
		    n = dao.updateProduct(bean, sizeInBytes);
		return n;

	}
	//依p_Id來刪除單筆記錄
	@Transactional
	@Override
	public int deleteProduct(int p_Id) {
		int n = 0;
          n = dao.deleteProduct(p_Id);
      return n;
	}
	
	//瀏覽頁相關
	@Transactional
	@Override
	public Map<Integer, ProductBean> getPageProducts(int pageNo) {
		Map<Integer, ProductBean> map = null;
			map = dao.getPageProducts(pageNo);
		return map;
	}
	@Override
	public long getRecordCounts(String catagory) {
		return dao.getRecordCounts(catagory);
	}
	@Override
	public int getRecordsPerPage() {
		return dao.getRecordsPerPage();
	}
	@Transactional
	@Override
	public int getTotalPages(String catagory) {
		int n = 0;
			n = dao.getTotalPages(catagory);
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
}
