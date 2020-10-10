package _02_ShoppingSystem.CommodityList.service;

import java.util.List;
import java.util.Map;

import _02_ShoppingSystem.CommodityList.model.CommodityBean;

public interface CommodityService {
	
	CommodityBean getCommodity(int comId); 
	
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


}
