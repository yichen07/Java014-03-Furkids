package _04_Community.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _04_Community.dao.PetColumnDao;
import _04_Community.model.PetColumnBean;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class PetColumnDaoImpl implements PetColumnDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public int saveBlogIndex(PetColumnBean pcb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		pcb.setPCViews("0");
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date current = new Date();
		pcb.setPCDateTime(sdFormat.format(current));
		session.save(pcb);
		n++;
		return n;
	}

	@Override
	public List<PetColumnBean> selectBlogIndexAll() {
		String hql = "from PetColumnBean where PCTitle is not null and PCTitle <>'' order by PCDateTime desc";
		Session session = factory.getCurrentSession();
		List<PetColumnBean> pcbList = null;
		try {
			pcbList = session.createQuery(hql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別selectBlogIndexAll()發生例外: " + e.getMessage());
		}
		return pcbList;
	}

	@Override
	public PetColumnBean getPetColumn(String pcid) {
		String hql = "from PetColumnBean where PCID=:pcid";
		Session session = factory.getCurrentSession();
		PetColumnBean pcb = null;
		try {
			pcb = (PetColumnBean) session.createQuery(hql).setParameter("pcid", pcid).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別getPetColumn()發生例外: " + e.getMessage());
		}
		return pcb;
	}

	@Override
	public List<PetColumnBean> selectBlogArticalAll(String pcfid) {
		String hql = "from PetColumnBean where PCFID=:pcfid and PCFID is not null and PCFID <>'' order by PCDateTime desc";
		Session session = factory.getCurrentSession();
		List<PetColumnBean> pcbList = null;
		try {
			pcbList = session.createQuery(hql).setParameter("pcfid", pcfid).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別selectBlogArticalAll()發生例外: " + e.getMessage());
		}
		return pcbList;
	}

	@Override
	public int saveBlogArtical(PetColumnBean pcb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		pcb.setPCViews("0");
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date current = new Date();
		pcb.setPCDateTime(sdFormat.format(current));
		session.save(pcb);
		n++;
		return n;
	}

	@Override
	public int getpcfidCOUNT(String pcfid) {
		String hql = "select COUNT(*) from PetColumnBean where PCFID=:pcfid";
		Session session = factory.getCurrentSession();
		int count = 0;
		try {
			Query query = session.createQuery(hql).setParameter("pcfid", pcfid);
			List names = query.list();
			Iterator iterator = names.iterator();
			while (iterator.hasNext()) {
				count = new Long((Long) iterator.next()).intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別getpcfidCOUNT()發生例外: " + e.getMessage());
		}
		return count;
	}

	@Override
	public int updatePCViews(String pcid, String PCViews) {
		String hql = "UPDATE PetColumnBean SET PCViews=:pcviews where PCID=:pcid";
		Session session = factory.getCurrentSession();
		int n = 0;
		try {
			Query query = session.createQuery(hql);
			query.setParameter("pcviews", (Integer.parseInt(PCViews) + 1) + "");
			query.setParameter("pcid", pcid);
			n = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別updatePCViews()發生例外: " + e.getMessage());
		}
		return n;
	}

	@Override
	public List<PetColumnBean> selectPopularBlogIndex() {
		String hql = "from PetColumnBean where PCTitle is not null and PCTitle <>'' order by PCViews desc";
		Session session = factory.getCurrentSession();
		List<PetColumnBean> pcbList = null;
		try {
			pcbList = session.createQuery(hql).setMaxResults(3).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別selectBlogIndexAll()發生例外: " + e.getMessage());
		}
		return pcbList;
	}

	@Override
	public List<PetColumnBean> selectRelatedBlogArtical(List<String> sList, String pcid) {
		Session session = factory.getCurrentSession();
		String likePCTitle = "";
		for (String s : sList) {
			likePCTitle += String.format("PCTitle LIKE '%s' OR ", "%" + s + "%");
		}
		String hql = String.format("FROM PetColumnBean WHERE PCID<>:pcid and (%s) order by PCViews desc",
				likePCTitle.substring(0, likePCTitle.length() - 4));
		List<PetColumnBean> pcbList = null;
		try {
			pcbList = session.createQuery(hql).setParameter("pcid", pcid).setMaxResults(3).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PetColumnDaoImp類別selectBlogIndexAll()發生例外: " + e.getMessage());
		}
		return pcbList;
	}

	@Override
	public int updatePetColumn(PetColumnBean pcb) {
		Session session = factory.getCurrentSession();
		int n = 0;
		session.update(pcb);
		n++;
		return n;
	}

	@Override
	public int deletePetColumn(String pcid) {
		String hql = "delete from PetColumnBean where PCID=:pcid OR PCFID=:pcfid";
		Session session = factory.getCurrentSession();
		int n = 0;
		Query query = session.createQuery(hql);
		query.setParameter("pcid", pcid);
		query.setParameter("pcfid", pcid);
		n = query.executeUpdate();
		return n;
	}

}
