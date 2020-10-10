package _02_ShoppingSystem.CommodityList.dao;

import java.util.List;
import java.util.Map;

import _02_ShoppingSystem.CommodityList.model.CommodityBean;

public interface CommodityDao {

	CommodityBean getCommodity(int ComId);
	
	List<String> getComSort();
	
	String getComSortTag();
	
	Map<Integer, CommodityBean> getPageCommodity(int pageNo);
	
	long getRecordCounts();
  
	int getRecordsPerPage();

	int getTotalPages();
	
//	int saveCommodity(CommodityBean bean);
	
	void setRecordsPerPage(int recordsPerPage);
		
	void setSelected(String selected);
	
	int updateCommodity(CommodityBean bean, long sizeInBytes) ;

	int saveCommodity(CommodityBean bean);

	int deleteCommodity(int no);




}
