package com.shoes.controller.manager;


import com.shoes.response.ApiResponse;
import com.shoes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/{id}")
    public ApiResponse<?> getCategory(@PathVariable String id) {return categoryService.getCategory(id);}

    @PostMapping
    public ApiResponse<?> createCategory(@RequestParam String name) {return categoryService.createCategory(name);}

    @PutMapping
    public ApiResponse<?> updateCategory(@RequestParam String id, @RequestParam String name) {return categoryService.updateCategory(id, name);}

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteCategory(@PathVariable String id) {return categoryService.deleteCategory(id);}


}
