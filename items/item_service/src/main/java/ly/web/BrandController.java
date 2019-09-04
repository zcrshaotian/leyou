package ly.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import ly.domain.Brand;
import ly.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //查询所有品牌
    @GetMapping("page")
    public ResponseEntity<PageInfo<Brand>> queryBrandByPage(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ) {
        List<Brand> brands = brandService.queryBrandByPage(page, rows, sortBy, desc, key);
        PageInfo pageInfo = new PageInfo(brands);
        return ResponseEntity.ok(pageInfo);
    }


    //新增商品
    @RequestMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam(value = "cids") List<Long> cid) {
        brandService.saveBrand(brand, cid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //根据ID删除商品
    @GetMapping("bid/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("id") Long bid) {
        brandService.deleteBrand(bid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //根据cid查询品牌
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> findBrandsByCid(@PathVariable(name = "cid")Long cid){
        return ResponseEntity.ok(brandService.findBrandsByCid(cid));
}


}
