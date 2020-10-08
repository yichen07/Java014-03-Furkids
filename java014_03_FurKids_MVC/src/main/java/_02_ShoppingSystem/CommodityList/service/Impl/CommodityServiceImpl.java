package _02_ShoppingSystem.CommodityList.service.Impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _02_ShoppingSystem.CommodityList.dao.CommodityDao;
import _02_ShoppingSystem.CommodityList.dao.lmpl.CommodityDaoImpl_Hibernate;
import _02_ShoppingSystem.CommodityList.model.CommodityBean;
import _02_ShoppingSystem.CommodityList.service.CommodityService;

@Service
public class CommodityServiceImpl implements CommodityService{
	
	@Autowired
	CommodityDao dao;
	
	public CommodityServiceImpl() {
	}
	
	@Transactional
	@Override
	public int getTotalPages() { 
		int n = 0;
		n = dao.getTotalPages();
		return n;
	}
	
	@Transactional
	@Override
	public Map<Integer, CommodityBean> getPageCommodity(int pageNo) {	
		Map<Integer, CommodityBean> map = null;
		map = dao.getPageCommodity(pageNo);
		return map;
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
	
	@Transactional
	@Override
	public CommodityBean getCommodity(int comId) {
		CommodityBean bean = null;
		bean = dao.getCommodity(comId);
        return bean;
	}
	
	@Transactional
	@Override
	public int updateCommodity(CommodityBean bean, long sizeInBytes) { 
        int n = 0;
        n = dao.updateCommodity(bean, sizeInBytes);
		return n;
	}
	
	@Transactional
	@Override
	public List<String> getComSort() {
		List<String> list = null;
        list = dao.getComSort();
		return list;
	}
	
	@Transactional
	@Override
	public String getComSortTag() {	   
		String tag = "";
        tag = dao.getComSortTag();
		return tag;
	}

	
	@Override
	public void setSelected(String ComSort) {
		dao.setSelected(ComSort);
	}
	
	

}
