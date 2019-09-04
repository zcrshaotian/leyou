package ly.service;

import com.github.pagehelper.PageHelper;
import ly.Exception.LyException;
import ly.dao.SpuDao;
import ly.domain.Category;
import ly.enums.ExceptionEnum;
import ly.vo.SpuVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpuService {

    @Autowired
    private SpuDao spuDao;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    public List<SpuVo> findSpu(Integer page, Integer rows, Boolean saleable, String key) {
        //分页
        PageHelper.startPage(page, rows);
        //过滤
        Example example = new Example(SpuVo.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("title", "%" + key + "%");
        }
        if (saleable != null){
            criteria.andEqualTo("saleable", saleable);
        }
        //排序
        example.setOrderByClause("last_update_time DESC");
        List<SpuVo> spuVos = spuDao.selectByExample(example);
        if (CollectionUtils.isEmpty(spuVos)){
            throw new LyException(ExceptionEnum.Brand_Not_Null);
        }
        //查询cname和bname
        List<SpuVo> spuVoList = queryCnameBname(spuVos);

        return spuVoList;
    }

    private List<SpuVo> queryCnameBname(List<SpuVo> spuVos) {
        for (SpuVo spuVo : spuVos) {
            List<String> names = categoryService.findByCids(Arrays.asList(spuVo.getCid1(), spuVo.getCid2(), spuVo.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            //插入cname
            spuVo.setCname(StringUtils.join(names, "/"));
            //插入bname
            spuVo.setBname(brandService.findByBid(spuVo.getBrandId()).getName());
        }

        return spuVos;
    }


}
