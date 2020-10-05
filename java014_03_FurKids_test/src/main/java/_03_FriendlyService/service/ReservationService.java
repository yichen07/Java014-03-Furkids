package _03_FriendlyService.service;

import java.util.List;

import _03_FriendlyService.model.ConvenienceBean_H;


public interface ReservationService {
	
	List<ConvenienceBean_H> getPageViewConvenience(String item,int pageNo);
	
	int getTotalPages(String item);
}
