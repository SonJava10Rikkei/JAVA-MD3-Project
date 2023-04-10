package rikkei.academy.controller;

import rikkei.academy.model.productModel.Category;
import rikkei.academy.service.categoryService.CategoryServiceIMPL;
import rikkei.academy.service.categoryService.ICategoryService;

import java.util.List;

public class CategoryController {
    ICategoryService categoryService = new CategoryServiceIMPL();

    public List<Category> getListCategory() {
        return categoryService.findAll();
    }

    public void createCategory(Category category) {
        categoryService.save(category);
    }

    public void deleteCategory(int id) {
        categoryService.deleteById(id);
    }

    public Category detailCategory(int id) {
        return categoryService.findById(id);
    }

    public void editCategory(int id, Category category) {
        Category category1 = categoryService.findById(id);
        category1.setNameCategory(category.getNameCategory());
    }
}
