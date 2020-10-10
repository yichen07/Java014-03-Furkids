package _02_ShoppingSystem.ShoppingCart.service.Impl;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _02_ShoppingSystem.ShoppingCart.dao.OrderDao;
import _02_ShoppingSystem.ShoppingCart.dao.OrderListDao;

import _02_ShoppingSystem.ShoppingCart.model.OrderBean;
import _02_ShoppingSystem.ShoppingCart.model.OrderListBean;
import _02_ShoppingSystem.ShoppingCart.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderListDao oidao;
    @Autowired
	private OrderDao odao;
//	private MemberDao mdao;

	public OrderServiceImpl() {

	}

    @Transactional
	@Override
	// 這是一個交易
	public void persistOrder(OrderBean ob) {
		// 檢查所有訂單明細所訂購之商品的庫存數量是否足夠
			checkStock(ob);
			
			// 儲存訂單

			odao.insertOrder(ob);


	}

	public void checkStock(OrderBean ob) {
		Set<OrderListBean> items = ob.getItems();

		for (OrderListBean oib : items) {
			oidao.updateProductStock(oib);
		}
	}



	public OrderDao getOdao() {
		return odao;
	}

	public void setOdao(OrderDao odao) {
		this.odao = odao;
	}

	@Override
	public OrderBean getOrder(int orderNo) {
		return odao.getOrder(orderNo);
	}

	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
        return list;
	}

	@Override
	public List<OrderBean> getMemberOrders(String memberId) {
		List<OrderBean> list = null;
        return list;
	}

}
