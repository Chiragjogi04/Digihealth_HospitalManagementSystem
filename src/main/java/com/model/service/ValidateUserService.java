package com.model.service;


import org.springframework.stereotype.Service;


@Service
public interface ValidateUserService {
	boolean registerUser(String id, String Password);
	
	boolean isValid(String id, String Password);

}