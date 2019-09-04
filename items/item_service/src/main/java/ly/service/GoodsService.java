package ly.service;

import ly.Exception.LyException;
import ly.dao.SkuDao;
import ly.dao.SpuDao;
import ly.dao.SpuDetailDao;
import ly.dao.StockDao;
import ly.domain.Sku;
import ly.domain.SpuDetail;
import ly.domain.Stock;
import ly.enums.ExceptionEnum;
import ly.vo.SpuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private SpuDao spuDao;

    @Autowired
    private SpuDetailDao spuDetailDao;

    @Autowired
    private SkuDao skuDao;

    @Autowired
    private StockDao stockDao;


    @Transactional
    public void saveGood(SpuVo spuVo) {
        //存储spu
        spuVo.setCreate_time(new Date());
        spuVo.setLast_update_time(spuVo.getCreate_time());
        spuVo.setSaleable(true);
        spuVo.setValid(false);
        int i = spuDao.insert(spuVo);
        if (i != 1){
            throw new LyException(ExceptionEnum.Good_Save_Error);
        }

        //存储spuDetail
        SpuDetail spuDetail = spuVo.getSpuDetail();
        spuDetail.setSpu_id(spuVo.getId());
        int i1 = spuDetailDao.insert(spuDetail);
        if (i1 != 1){
            throw new LyException(ExceptionEnum.Good_Save_Error);

        }

        //存储sku
        List<Sku> skus = spuVo.getSkus();
        for (Sku sku : skus) {
            sku.setCreate_time(new Date());
            sku.setSpu_id(spuVo.getId());
            sku.setLast_update_time(sku.getCreate_time());
            int i2 = skuDao.insert(sku);
            if (i2 != 1){
                throw new LyException(ExceptionEnum.Good_Save_Error);

            }

            //存储stock
            Stock stock = new Stock();
            stock.setSku_id(sku.getId());
            stock.setStock(sku.getStock());
            int i3 = stockDao.insert(stock);
            if (i3 != 1){
                throw new LyException(ExceptionEnum.Good_Save_Error);
            }
        }

    }
}
