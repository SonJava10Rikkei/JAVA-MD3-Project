package rikkei.academy.service.categoryVideo;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.model.productModel.Category;

import java.util.List;

public class CategoryServiceIMPL implements ICategoryService {
    static List<Category> categories = new Config<Category>().readFormFile(PathConfig.PATH_CATEGORY);

    @Override
    public List<Category> findAll() {
        new Config<Category>().writeToFile(PathConfig.PATH_CATEGORY, categories);
        return categories;
    }

    @Override
    public void save(Category category) {
        categories.add(category);
        new Config<Category>().writeToFile(PathConfig.PATH_CATEGORY, categories);

    }

    @Override
    public Category findById(int id) {
        return categories.stream().filter(category -> category.getId() == id).findAny().orElse(null);
    }

    @Override
    public void deleteById(int id) {
        categories.remove(findById(id));
        new Config<Category>().writeToFile(PathConfig.PATH_CATEGORY, categories);
    }
}
