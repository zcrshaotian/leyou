package ly.dao;

import ly.domain.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryDao extends Mapper<Category>, IdListMapper<Category, Long> {

    @Select("select * from tb_category where id in (select category_id from tb_category_brand where brandId = #{id})")
    List<Category> findByBid(@Param(value = "id") Long id);
}
