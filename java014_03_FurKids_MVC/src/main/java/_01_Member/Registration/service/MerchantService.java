package _01_Member.Registration.service;

import java.util.List;

import _01_Member.Registration.model.MerchantBean;
import _01_Member.Registration.model.MerchantChildBean;

public interface MerchantService {
	
// 商家
	// 註冊
	int saveMerchant(MerchantBean mb);
	// 更新(修改)
	int updateMerchant(MerchantBean mb);
	// 刪除
	int deleteMerchant(String account);
	// 檢查(帳號重複性)
	boolean accountExists(String account);
	// 搜尋特定商家資料
	MerchantBean queryMerchant(String account);
	// 搜尋全部商家資料
	List<MerchantBean> queryAllMerchants();
	// 登入驗證
	MerchantBean checkAccountPassword(String account, String password);
//	Object checkAccountPassword(String account, String password);
	
// 商家分店
	// 新增分店
	int saveMerchantChild(MerchantChildBean mb);
	// 檢查(分店重複性)
	boolean merchantChildExists(String account, String address);
	// 搜尋特定商家所有分店資料
	List<MerchantChildBean> queryAllMerchantChilds(String account);
}
