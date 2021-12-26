package com.springbook.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Service("userService")
public class UserServeImpl implements UserService{
	@Autowired
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		System.out.println("UserServeImpl===> Mybatis로 setUserDAO() 기능 처리");
		this.userDAO = userDAO;
	}

	@Override
	public UserVO getUser(UserVO vo) {
		System.out.println("UserServeImpl===> Mybatis로 getUser() 기능 처리");
		return userDAO.getUser(vo);
	}


}
