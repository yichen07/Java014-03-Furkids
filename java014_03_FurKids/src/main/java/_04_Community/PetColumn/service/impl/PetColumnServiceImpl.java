package _04_Community.PetColumn.service.impl;

import java.util.List;

import _04_Community.PetColumn.dao.PetColumnDao;
import _04_Community.PetColumn.dao.impl.PetColumnDaoImpl;
import _04_Community.PetColumn.model.PetColumnBean;
import _04_Community.PetColumn.service.PetColumnService;

public class PetColumnServiceImpl implements PetColumnService {
	PetColumnDao pcd = new PetColumnDaoImpl();;

	@Override
	public int saveBlogIndex(PetColumnBean pcb) {
		return pcd.saveBlogIndex(pcb);
	}

	@Override
	public List<PetColumnBean> selectBlogIndexAll() {
		return pcd.selectBlogIndexAll();
	}

	@Override
	public PetColumnBean getPetColumn(String pcid) {
		return pcd.getPetColumn(pcid);
	}

	public List<PetColumnBean> selectBlogArticalAll(String pcfid) {
		return pcd.selectBlogArticalAll(pcfid);
	}

	@Override
	public int saveBlogArtical(PetColumnBean pcb) {
		return pcd.saveBlogArtical(pcb);
	}

	@Override
	public int getpcfidCOUNT(String pcfid) {
		return pcd.getpcfidCOUNT(pcfid);

	}

	@Override
	public int updatePCViews(String pcid, String pcViews) {
		return pcd.updatePCViews(pcid, pcViews);

	}
}
