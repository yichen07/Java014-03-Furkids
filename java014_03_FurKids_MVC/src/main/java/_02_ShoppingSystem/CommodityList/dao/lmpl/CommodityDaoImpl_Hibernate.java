package _02_ShoppingSystem.CommodityList.dao.lmpl;

import java.io.Serializable;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import _00_Init.util.DBService;
import _00_Init.util.GlobalService;
import _01_Member.Registration.dao.MerchantDao;
import _01_Member.Registration.model.MerchantBean;
import _02_ShoppingSystem.CommodityList.dao.CommodityDao;
import _02_ShoppingSystem.CommodityList.model.CommodityBean;

@Repository
public class CommodityDaoImpl_Hibernate implements Serializable, CommodityDao {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_FILENAME_LENGTH = 255;
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 預設值：每頁三筆
	private int totalPages = -1;

	
	String selected = "";

	@Autowired
	SessionFactory factory;
	
	@Autowired
	MerchantDao dao;	
	public CommodityDaoImpl_Hibernate() {
		
		
	}

	// 計算販售的商品總共有幾頁
	@Override
	public int getTotalPages() {
        totalPages = (int)(Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}
	
	// 查詢某一頁商品資料	 
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, CommodityBean> getPageCommodity(int pageNo) {
// 		Map<Integer, CommodityBean> map = new HashMap<>();
 		Map<Integer, CommodityBean> map = new LinkedHashMap<>();
        String hql = "FROM CommodityBean";
        Session session = factory.getCurrentSession();
        int startRecordNo = (pageNo - 1) * recordsPerPage;
        List<CommodityBean> list = session.createQuery(hql)
                            .setFirstResult(startRecordNo)
                            .setMaxResults(recordsPerPage)
                            .getResultList();

        for(CommodityBean bean: list){
            map.put(bean.getComId(), bean);
        }

		
		
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getRecordCounts() {
		long count = 0;	   
        String hql = "SELECT count(*) FROM CommodityBean";
		Session session = factory.getCurrentSession();
		count = (Long)session.createQuery(hql).getSingleResult();
			return count;
	}

	@SuppressWarnings("unchecked")
	@Override	
	public List<String> getComSort() {
		String hql = "SELECT DISTINCT ComSort FROM CommodityBean";
		Session session = factory.getCurrentSession();
        List<String> list = null;
        list = session.createQuery(hql).getResultList();

		return list;
	}


	
	@Override
	public String getComSortTag() {
		String ans = "";
		List<String> list = getComSort();
		ans += "<SELECT name='Comsort'>";
		for (String sort : list) {
			if (sort.equals(selected)) {
				ans += "<option value='" + sort + "' selected>" + sort + "</option>";
			} else {
				ans += "<option value='" + sort + "'>" + sort + "</option>";
			}
		}
		ans += "</SELECT>";
		return ans;	}



	@Override
	public int updateCommodity(CommodityBean bean, long sizeInBytes) {
		
		int n = 0;
//		if (bean.getMerchantBean() == null) {
//            dao.setBusAccount(bean.getBusAccount());
//            MerchantBean cb = dao.getMerchantById();
//            bean.setMerchantBean(cb);
//        }
		if (sizeInBytes == -1) { // 不修改圖片
            n = updateCommodity(bean);
            return n;
        }
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(bean);
        n++;

		return n;
	}
	// 修改一筆書籍資料，不改圖片
	public int updateCommodity(CommodityBean bean) {
		int n = 0;
        CommodityBean b0 = null;
        Session session = factory.getCurrentSession();
        b0 = session.get(CommodityBean.class, bean.getComId());
        bean.setComStock(b0.getComStock());
//        bean.setComPrice(b0.getComPrice());
        bean.setComImage(b0.getComImage());
        bean.setFileName(b0.getFileName());
        session.evict(b0);
        session.saveOrUpdate(bean);
        n++;
        return n;
	}

	@Override
	public int deleteCommodity(int no) {
		int n = 0;
        Session session = factory.getCurrentSession();
        CommodityBean cb = new CommodityBean();
        cb.setComId(no);
        session.delete(cb);
        n++;
        return n;
	}


	@Override
	public int saveCommodity(CommodityBean bean) {
		int n = 0;
        Session session = factory.getCurrentSession();
        String filename = bean.getFileName(); 
        if (filename.length() > MAX_FILENAME_LENGTH) {
        	String ext = filename.substring(filename.lastIndexOf("."));
        	String shortFilefname = filename.substring(0, MAX_FILENAME_LENGTH-ext.length()) + ext;
        	System.out.println(shortFilefname);
        	bean.setFileName(shortFilefname);
        };
//        if (bean.getMerchantBean() == null || bean.getMerchantBean().getBusName() == null) {
//            dao.setBusAccount(bean.getMerchantBean().getBusAccount());
//            MerchantBean cb = dao.getMerchantById();
//            bean.setMerchantBean(cb);
//        }
        session.save(bean);
        n++;
        return n;
	}


	@Override
	public void setSelected(String selected) {
		this.selected = selected;		
	}


	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	@Override
	public CommodityBean getCommodity(int ComId) {
		CommodityBean bean = null;
        Session session = factory.getCurrentSession();
        bean = session.get(CommodityBean.class, ComId);
        return bean;
	}






	
}
