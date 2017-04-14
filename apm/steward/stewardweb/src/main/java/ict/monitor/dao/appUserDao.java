package ict.monitor.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import ict.monitor.bean.appUser;
//app测试用的 后期可删掉
@Repository
@Transactional
public interface appUserDao extends BaseDao<appUser>{

	public List<appUser> findAll();
	public appUser findUserByUserNameAndPassword(appUser appUser);
	public appUser findUserByUserName(String username);
	public appUser findUserByUserEmail(String email);
	public Integer findData();
}
