package ly.dao;

import ly.domain.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandDao extends Mapper<Brand> {

    //存储品牌id和对应的分类id到tb_category_brand中
    @Insert("insert into tb_category_brand(category_id, brand_id) values(#{cid}, #{brand_id})")
    public int insertIntoCategory_Brand(@Param("cid")Long cid, @Param("brand_id")Long bid);

    //删除tb_category_brand中对应的bid记录
    @Delete("delete from tb_category_brand where brandId = #{brandId}")
    int deleteCategory_Brand(Long bid);


    //根据cid查询品牌
    @Select("select * from tb_brand where id in (select brandId from tb_category_brand where category_id = #{cid})")
    List<Brand> findBrandsByCid(Long cid);
}
