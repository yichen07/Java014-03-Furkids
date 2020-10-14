package _03_listProducts.dao;

import java.util.List;
import java.util.Map;

import _03_listProducts.model.ProductBean;

public interface ProductDao {
	//查詢全部記錄  
	Map<Integer, ProductBean> getAllProducts();
	Map<Integer, ProductBean> getAllProducts(String category, int pageNo);
	
	//取類別清單  ok
	List<String>  getAllCategories();
	
	//依類別取商品資料
	Map<Integer, ProductBean> getProductsByCategory(String category, int pageNo);
	
	//依ID取商品資料 ok
	ProductBean getProductById(int p_Id);
	
	//類別選單ok
	String getCategoryTag();
	
	// 新增一筆記錄ok
	int saveProduct(ProductBean bean);
		
	//修改一筆記錄ok
	int updateProduct(ProductBean bean, long sizeInBytes) ;
	
	// 依p_Id來刪除單筆記錄ok
	int deleteProduct(int p_Id);
	
	//瀏覽頁相關ok
	Map<Integer, ProductBean> getPageProducts(int pageNo);
	long getRecordCounts(String catagory);
	int getRecordsPerPage();
	int getTotalPages(String catagory);
	void setRecordsPerPage(int recordsPerPage);
	void setSelected(String selected);
}
