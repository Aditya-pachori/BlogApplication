package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDTO;

public interface CategoryService 
{
	 CategoryDTO createCategory(CategoryDTO categoryDto);
	 CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId);
	 void deleteCategory(Integer catergoryId);
	 CategoryDTO getCategory(Integer categoryId);
	 List<CategoryDTO> getAllCategory();
	 
}
