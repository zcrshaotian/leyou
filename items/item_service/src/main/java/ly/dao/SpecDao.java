package ly.dao;

import ly.domain.SpecGroup;
import ly.domain.SpecParams;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpecDao extends Mapper<SpecGroup> {

    @Select("select * from tb_spec_param where group_id = #{gid}")
    public List<SpecParams> findSpecParams(Long gid);
}
