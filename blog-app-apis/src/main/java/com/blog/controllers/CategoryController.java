package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDTO;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController 
{
	@Autowired
	private CategoryService categoryService;
	
	//Create
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto)
	{
		 CategoryDTO createdCategory= this.categoryService.createCategory(categoryDto);
		 return new ResponseEntity<CategoryDTO>(createdCategory,HttpStatus.CREATED);
	}
	
	//Update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDto , @PathVariable Integer catId)
	{
		 CategoryDTO updatedCategory= this.categoryService.updateCategory(categoryDto, catId);
		 return new ResponseEntity<CategoryDTO>(updatedCategory,HttpStatus.OK);
	}
	
	//Delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId)
	{
		  this.categoryService.deleteCategory(catId);	
		 return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully",false),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer catId)
	{
		  CategoryDTO categoryDto=this.categoryService.getCategory(catId);	
		 return new ResponseEntity<CategoryDTO>(categoryDto , HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getCategories()
	{
		  List<CategoryDTO> categories= this.categoryService.getAllCategory();
		  return ResponseEntity.ok(categories);
	}
}
