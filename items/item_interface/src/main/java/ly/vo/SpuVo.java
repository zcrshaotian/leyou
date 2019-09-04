package ly.vo;

import lombok.Data;
import ly.domain.Sku;
import ly.domain.SpuDetail;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

//因为此持久层对象中有数据库中没有对应字段的属性，所以放在VO包下，否则放在POJO包中

@Data
@Table(name = "tb_spu")
public class SpuVo {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String title;
    private String subTitle;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private Long brandId;
    private Boolean saleable;
    private Boolean valid;

    private Date create_time;
    private Date last_update_time;

    //因为cname和bname数据中没有这些字段，所以要用Transient注解
    @Transient
    private String cname;

    @Transient
    private String bname;

    @Transient
    private List<Sku> skus;

    @Transient
    private SpuDetail spuDetail;


}
