package ly.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "tb_sku")
public class Sku {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long spu_id;
    private String title;
    private String images;
    private Long price;
    private String indexes;
    private String own_spec;
    private Boolean enable;
    private Date create_time;
    private Date last_update_time;

    @Transient
    private Integer stock;
}
