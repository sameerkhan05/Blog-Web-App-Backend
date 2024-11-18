package com.sam.blog.services.impl;

import com.sam.blog.entities.Category;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.payloads.CategoryDTO;
import com.sam.blog.repositories.CategoryRepo;
import com.sam.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoryService {

	private final CategoryRepo categoryRepo;
	private final ModelMapper modelMapper;

	public CategoriesServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
		this.categoryRepo = categoryRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = this.modelMapper.map(categoryDTO, Category.class);
		Category addCategory = categoryRepo.save(category);
		return modelMapper.map(addCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setGetCategoryDescription(categoryDTO.getGetCategoryDescription());
		Category updateCategory = this.categoryRepo.save(category);
		return modelMapper.map(updateCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDTO getCategory(Long categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> categoryList = this.categoryRepo.findAll();
		List<CategoryDTO> categoryDTOS = categoryList.stream()
				.map((category) -> this.modelMapper.map(category, CategoryDTO.class))
				.collect(Collectors.toList());
		return categoryDTOS;
	}
}
