package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDTO;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {   
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDto) 
	{
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId) {
		 Category cat = this.categoryRepo.findById(categoryId)
				 .orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		 cat.setCategoryTitle(categoryDto.getCategoryTitle());
		 cat.setCategoryDescription(categoryDto.getCategoryDescription());
		 
		 Category updatedCategory =this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer catergoryId) {
		Category cat= this.categoryRepo.findById(catergoryId)
		.orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", catergoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		Category cat= this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		return this.modelMapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories= this.categoryRepo.findAll();
		List<CategoryDTO> categoriesDto=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return categoriesDto;
	}

}
