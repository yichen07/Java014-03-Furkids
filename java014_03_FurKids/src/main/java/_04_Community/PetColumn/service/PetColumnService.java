package _04_Community.PetColumn.service;

import java.util.List;

import _04_Community.PetColumn.model.PetColumnBean;

public interface PetColumnService {

	int saveBlogIndex(PetColumnBean pcb);

	List<PetColumnBean> selectBlogIndexAll();

	PetColumnBean getPetColumn(String pcid);
	
	List<PetColumnBean> selectBlogArticalAll(String pcfid);

	int saveBlogArtical(PetColumnBean pcb);
	
	int getpcfidCOUNT(String pcfid);

	int updatePCViews(String pcid, String pcViews);
}
