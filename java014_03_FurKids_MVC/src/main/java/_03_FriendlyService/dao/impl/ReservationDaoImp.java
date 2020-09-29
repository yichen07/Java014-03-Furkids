package _03_FriendlyService.dao.impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _03_FriendlyService.dao.ReservationDao;

@Repository
public class ReservationDaoImp implements ReservationDao{
	
	public int totalPages = -1;
	
	 @Autowired
	SessionFactory factory;


	

}
