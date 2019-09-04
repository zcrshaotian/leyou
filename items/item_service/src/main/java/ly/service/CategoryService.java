package ly.service;

import ly.Exception.LyException;
import ly.dao.CategoryDao;
import ly.domain.Category;
import ly.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    //根据parentId查询
    public List<Category> queryCategoryByPid(long pid) {
        //通用mapper中select方法，可以接收一个对象，根据对象不为空的值进行查询，并返回一个list值
        Category c = new Category();
        c.setParentId(pid);
        List<Category> categories = categoryDao.select(c);
        //判断结果是否为空
        if(CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionEnum.Category_Not_Found);
        }
        return categories;
    }

    //根据品牌ID查询所有该品牌的分类
    public List<Category> findByBid(Long id) {
        List<Category> categories = categoryDao.findByBid(id);
        if (CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionEnum.CategoryName_Not_Found);
        }
        return categories;
    }

    //通过多个id查询分类
    public List<Category> findByCids(List<Long> cids){
        List<Category> categories = categoryDao.selectByIdList(cids);
        if (CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionEnum.CategoryName_Not_Found);
        }
        return categories;
    }
}
