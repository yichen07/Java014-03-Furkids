package _00_Init.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import _03_listProducts.model.ProductBean;

public interface ProductInfoDao {

	// 新增紀錄
	int saveProduct(ProductBean mb);

	//	修改相關(查詢id)
	ProductBean getSupPageProductsById(int id);
	
	//	儲存修改後
	void updateSupPageProducts(ProductBean bean); 
//	ProductBean getProductBean(Integer id);
	
	// 查詢商品
	List<ProductBean> getProduct();
	//	刪除產品
	void delete(Integer id);
	
	public void setConnection(Connection con);

	// 瀏覽頁相關ok
	Map<Integer, ProductBean> getSupPageProducts(int pageNo);
	long getRecordCounts();
	int getRecordsPerPage();
	int getTotalPages();
	void setRecordsPerPage(int recordsPerPage);
	void setSelected(String selected);
}