package ly.web;

import ly.service.GoodsService;
import ly.service.SpuService;
import ly.vo.SpuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("goods")
public class GoodController {

    @Autowired
    private GoodsService goodsService;


    //添加商品
    @PostMapping
    public ResponseEntity<Void> saveGood(@RequestBody SpuVo spuVo){
        goodsService.saveGood(spuVo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
