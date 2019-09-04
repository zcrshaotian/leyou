package ly.dao;

import ly.domain.Sku;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface SkuDao extends Mapper<Sku>, InsertListMapper<Sku> {
}
