package ly.service;

import com.github.pagehelper.PageHelper;
import ly.Exception.LyException;
import ly.dao.BrandDao;
import ly.domain.Brand;
import ly.enums.ExceptionEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandDao brandDao;

    //根据分页信息查询商品
    public List<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key){
        //分页
        PageHelper.startPage(page, rows);
        //过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)){
            example.createCriteria().orLike("name", "%" + key + "%")
                    .orEqualTo("letter", key.toUpperCase());
        }
        //排序
        if (StringUtils.isNotBlank(sortBy)){
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<Brand> brands = brandDao.selectByExample(example);
        if (CollectionUtils.isEmpty(brands)){
            throw new LyException(ExceptionEnum.Brand_Not_Null);
        }
        return brands;
    }

    //存储商品
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        //将商品信息存储到商品表中
        int insert = brandDao.insert(brand);
        if (insert != 1){
            throw new LyException(ExceptionEnum.Brand_Not_Null);
        }
        for (Long cid : cids) {
            int i = brandDao.insertIntoCategory_Brand(cid, brand.getId());
            if (i != 1){
                throw new LyException(ExceptionEnum.Save_Brand_Error);
            }
        }

    }

    //删除商品
    @Transactional
    public void deleteBrand(Long bid) {
        //根据ID删除相关的上商品
        int i = brandDao.deleteByPrimaryKey(bid);
        if (i != 1){
            throw new LyException(ExceptionEnum.Delete_Brand_Error);
        }
        //删除与分类表相关联的中间表
        int b = brandDao.deleteCategory_Brand(bid);
        if (b != 1){
            throw new LyException(ExceptionEnum.Delete_Brand_Error);
        }

    }

    //根据bid查询品牌
    public Brand findByBid(Long id){
        return brandDao.selectByPrimaryKey(id);

    }

    public List<Brand> findBrandsByCid(Long cid) {
        List<Brand> brands = brandDao.findBrandsByCid(cid);
        if (CollectionUtils.isEmpty(brands)){
            throw new LyException(ExceptionEnum.Brand_Not_Null);
        }
        return brands;
    }
}
