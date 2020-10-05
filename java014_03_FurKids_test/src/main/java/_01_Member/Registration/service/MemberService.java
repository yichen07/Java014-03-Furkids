package _01_Member.Registration.service;

import java.util.List;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.PetBean;

public interface MemberService {

// 會員
	// 註冊
	int saveMember(MemberBean mb);
	// 更新(修改)
	int updateMember(MemberBean mb);
	// 刪除
	int deleteMember(String account);
	// 檢查(帳號重複性)
	boolean accountExists(String account);
	// 搜尋特定會員資料
	MemberBean queryMember(String account);
	// 搜尋全部會員資料
	List<MemberBean> queryAllMembers();
	// 登入驗證
	MemberBean checkAccountPassword(String account, String password);
//	Object checkAccountPassword(String account, String password);
	
// 會員寵物
	// 新增寵物
	int savePet(PetBean pet);
	// 搜尋特定會員所有寵物資料
	List<PetBean> queryAllPets(String account);
	
}
