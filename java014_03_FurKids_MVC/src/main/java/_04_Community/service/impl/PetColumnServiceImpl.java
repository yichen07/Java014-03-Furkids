package _04_Community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _04_Community.dao.PetColumnDao;
import _04_Community.model.PetColumnBean;
import _04_Community.service.PetColumnService;

@Service
public class PetColumnServiceImpl implements PetColumnService {
	
	@Autowired
	PetColumnDao pcd;

	@Transactional
	@Override
	public int saveBlogIndex(PetColumnBean pcb) {
		return pcd.saveBlogIndex(pcb);
	}

	@Transactional
	@Override
	public List<PetColumnBean> selectBlogIndexAll() {
		return pcd.selectBlogIndexAll();
	}

	@Transactional
	@Override
	public PetColumnBean getPetColumn(String pcid) {
		return pcd.getPetColumn(pcid);
	}

	@Transactional
	@Override
	public List<PetColumnBean> selectBlogArticalAll(String pcfid) {
		return pcd.selectBlogArticalAll(pcfid);
	}

	@Transactional
	@Override
	public int saveBlogArtical(PetColumnBean pcb) {
		return pcd.saveBlogArtical(pcb);
	}

	@Transactional
	@Override
	public int getpcfidCOUNT(String pcfid) {
		return pcd.getpcfidCOUNT(pcfid);

	}

	@Transactional
	@Override
	public int updatePCViews(String pcid, String pcViews) {
		return pcd.updatePCViews(pcid, pcViews);

	}

	@Transactional
	@Override
	public List<PetColumnBean> selectPopularBlogIndex() {
		return pcd.selectPopularBlogIndex();
	}
	
	@Transactional
	@Override
	public List<PetColumnBean> selectRelatedBlogArtical(List<String> sList,String pcid) {
		return pcd.selectRelatedBlogArtical(sList, pcid);
	}

	@Transactional
	@Override
	public int updatePetColumn(PetColumnBean pcb) {
		return pcd.updatePetColumn(pcb);
	}

	@Transactional
	@Override
	public int deletePetColumn(String pcid) {
		return pcd.deletePetColumn(pcid);
	}
}
