package _04_ShoppingCart.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_Member.Registration.dao.MemberDao;
import _04_ShoppingCart.dao.SaleOrderDao;
import _04_ShoppingCart.dao.SaleOrderItemsDao;
import _04_ShoppingCart.model.SaleOrderBean;
import _04_ShoppingCart.model.SaleOrderItemsBean;
import _04_ShoppingCart.service.SaleOrderService;
@Service
public class SaleOrderServiceImpl implements SaleOrderService {
//	private SessionFactory factory;
	@Autowired
	private SaleOrderItemsDao soidao;
	@Autowired
	private SaleOrderDao sodao;
	@Autowired
	private MemberDao mdao;

	public SaleOrderServiceImpl() {
//		factory = HibernateUtils.getSessionFactory();
//		oidao = new OrderItemDaoImpl_Hibernate();
//		odao = new OrderDaoImpl_Hibernate();
//		mdao = new MemberDaoImpl_Hibernate();
	}
	@Transactional
	@Override
	// 這是一個交易
	public void persistOrder(SaleOrderBean ob) {
			checkStock(ob);
			sodao.insertSaleOrder(ob);
	}

	public void checkStock(SaleOrderBean ob) {
		Set<SaleOrderItemsBean> items = ob.getItems();
		for (SaleOrderItemsBean oib : items) {
			soidao.updateProductStock(oib);
		}
	}

	public SaleOrderDao getSaleOdao() {
		return sodao;
	}

	public void setOdao(SaleOrderDao sodao) {
		this.sodao = sodao;
	}
	
	@Transactional
  @Override
  // 本方法將由控制 Lazy Loading 的過濾器之doFilter()方法間接呼叫，所以不可以在此方法內執行與交易
  // 有關的方法
  public SaleOrderBean getOrder(int orderNo) {
      SaleOrderBean  bean = null;
          bean = sodao.getSaleOrder(orderNo);
      return bean;
  }
  @Transactional
	@Override
	public List<SaleOrderBean> getAllOrders() {
		List<SaleOrderBean> list = null;
			list = sodao.getAllSaleOrders();
		return list;
	}
	@Transactional
	@Override
	public List<SaleOrderBean> getMemberOrders(int m_No) {
		List<SaleOrderBean> list = null;
			list = sodao.getMemberSaleOrders(m_No);
		return list;
	}
}
