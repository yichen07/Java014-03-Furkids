package _03_FriendlyService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _03_FriendlyService.dao.ReservationDao;
import _03_FriendlyService.model.ConvenienceBean_H;
import _03_FriendlyService.service.ReservationService;
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	ReservationDao resDao;
	
	public ReservationServiceImpl() {

	}

	
	//撈出計算過後的總頁數
	@Transactional
	@Override
	public int getTotalPages(String item) {
		int n = 0;
		n = resDao.getTotalPages(item);
		return n;
	}

	//依上架類型撈出所有商家已上架的服務(一次撈8筆)
	@Transactional
	@Override
	public List<ConvenienceBean_H> getPageViewConvenience(String item, int pageNo) {
		List<ConvenienceBean_H> bean = null;
		bean = resDao.getPageViewConvenience(item, pageNo);
		return bean;
	}
	

	


	

}
