package _03_FriendlyService.service.impl;

import java.util.Map;

import _03_FriendlyService.dao.ConvenienceDaoImp_JDBC;
import _03_FriendlyService.model.ConvenienceBean;

public class ConvenienceServiceImpl {

	ConvenienceDaoImp_JDBC dao;
	
	public ConvenienceServiceImpl() {
		this.dao = new ConvenienceDaoImp_JDBC();
	}
	
	public Map<Integer, ConvenienceBean> getConvenience(String userId) {
		return dao.checkIdConvenience(userId);
	}

}
