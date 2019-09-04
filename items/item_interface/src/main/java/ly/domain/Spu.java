package ly.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_spu")
@Data
public class Spu {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private String sub_title;
    private Integer cid1;
    private Integer cid2;
    private Integer cid3;
    @Column(name = "brandId")
    private Integer bid;
    private Boolean saleable;
    private Boolean valid;
    private Date create_time;
    private Date last_update_time;
}
