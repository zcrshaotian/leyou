package ly.web;

import ly.domain.Category;
import ly.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    //因为遵循rest风格，所以返回值用 ResponseEntity
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam("pid")long pid){
        return ResponseEntity.ok(categoryService.queryCategoryByPid(pid));
    }

    @GetMapping("bid/{id}")
    public ResponseEntity<List<Category>> findCategoryByBid(@PathVariable(value = "id")Long id){
        List<Category> categories = categoryService.findByBid(id);
        return ResponseEntity.ok(categories);
    }
}
