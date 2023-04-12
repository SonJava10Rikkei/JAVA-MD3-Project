package rikkei.academy.service.categoryService;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.model.productModel.Category;
import rikkei.academy.model.productModel.Product;

import java.util.List;

public class CategoryServiceIMPL implements ICategoryService {
    static List<Category> categoryList = new Config<Category>().readFormFile(PathConfig.PATH_CATEGORY);

    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public void save(Category category) {
        if (findById(category.getId()) == null) {
            categoryList.add(category);
        } else {
            int index = categoryList.indexOf(findById(category.getId()));
            categoryList.set(index, category);
        }
        new Config<Category>().writeToFile(PathConfig.PATH_CATEGORY,categoryList);
    }

    @Override
    public Category findById(int id) {
        return categoryList.stream().filter(category -> category.getId() == id).findAny().orElse(null);
    }

    @Override
    public void deleteById(int id) {
        categoryList.remove(findById(id));
        new Config<Category>().writeToFile(PathConfig.PATH_CATEGORY, categoryList);
    }
}
