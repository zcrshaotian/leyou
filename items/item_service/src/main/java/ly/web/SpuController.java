package ly.web;

import com.github.pagehelper.PageInfo;
import ly.service.SpuService;
import ly.vo.SpuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    @GetMapping("page")
    public ResponseEntity<PageInfo<SpuVo>> findSpu(
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "saleable", required = false)Boolean saleable,
            @RequestParam(value = "key", required = false)String key
    ){

        List<SpuVo> spuVos = spuService.findSpu(page, rows, saleable, key);
        PageInfo<SpuVo> pageInfo = new PageInfo<>(spuVos);
        return ResponseEntity.ok(pageInfo);
    }
}
