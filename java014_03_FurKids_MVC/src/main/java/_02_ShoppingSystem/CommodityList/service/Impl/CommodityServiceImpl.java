package _02_ShoppingSystem.CommodityList.service.Impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import _02_ShoppingSystem.CommodityList.dao.CommodityDao;
import _02_ShoppingSystem.CommodityList.dao.lmpl.CommodityDaoImpl_Jdbc;
import _02_ShoppingSystem.CommodityList.model.CommodityBean;
import _02_ShoppingSystem.CommodityList.service.CommodityService;

public class CommodityServiceImpl implements CommodityService{

	CommodityDao dao;
	
	public CommodityServiceImpl() {
		this.dao = new CommodityDaoImpl_Jdbc();
	}
	
	@Override
	public int getTotalPages() {
		return dao.getTotalPages();
	}
	
	@Override
	public Map<Integer, CommodityBean> getPageCommodity(int pageNo) {
		return dao.getPageCommodity(pageNo);
	}
	
	@Override
	public int getRecordsPerPage() {
		return dao.getRecordsPerPage();
	}
	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		dao.setRecordsPerPage(recordsPerPage);
	}
	
	@Override
	public long getRecordCounts() {
		return dao.getRecordCounts();
	}
	
	@Override
	public CommodityBean getCommodity(int ComId) {
		return dao.getCommodity(ComId);
	}
	
	@Override
	public int updateCommodity(CommodityBean bean, long sizeInBytes) {
		return dao.updateCommodity(bean, sizeInBytes);
	}
	
	@Override
	public List<String> getComSort() {
		return dao.getComSort();
	}
	@Override
	public String getComSortTag() {
		return dao.getComSortTag();
	}

	
	@Override
	public void setSelected(String ComSort) {
		dao.setSelected(ComSort);
	}
	

}
