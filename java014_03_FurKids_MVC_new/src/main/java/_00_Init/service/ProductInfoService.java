package _00_Init.service;

import java.util.List;
import java.util.Map;

import _03_listProducts.model.ProductBean;

public interface ProductInfoService {

	List<ProductBean> getProduct();

	int saveProduct(ProductBean mb);

	//	查詢修改相關(id)
	ProductBean getSupPageProductsById(int id);
	//	存取修改後商品
	void updateSupPageProducts(ProductBean bean); 
//	ProductBean getProductBean(Integer id);
	
	//	瀏覽頁相關
	Map<Integer, ProductBean> getSupPageProducts(int pageNo);
	//	獲取記錄計數
	long getRecordCounts();
	//	獲取每頁記錄
	int getRecordsPerPage();
	//	獲取總頁數
	int getTotalPages();
	//	設置每頁記錄
	void setRecordsPerPage(int recordsPerPage);
	//	設置選定
	void setSelected(String category);
	//	刪除紀錄
	void delete(Integer id);
}