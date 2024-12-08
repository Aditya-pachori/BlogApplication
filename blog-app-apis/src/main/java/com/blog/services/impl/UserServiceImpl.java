package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.payloads.UserDTO;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;
import com.blog.exceptions.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO user) {
		User user1 = this.dTOtoUser(user);
		User savedUser= this.userRepo.save(user1);
		return this.UsertoDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User savedUser=this.userRepo.save(user);
		return this.UsertoDto(savedUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) 
	{
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "id",userId));
		return this.UsertoDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDTO> userDtos= users.stream().map(user-> this.UsertoDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId)
	{
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "id",userId));
		this.userRepo.delete(user);
		
	}
	
	private User dTOtoUser(UserDTO userDTO)
	{
		User user =this.modelMapper.map(userDTO, User.class);
		return user;	
	}
	
	public UserDTO UsertoDto(User user)
	{
		UserDTO userDTO =this.modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

}
