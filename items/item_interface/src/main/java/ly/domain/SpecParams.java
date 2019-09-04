package ly.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "tb_spec_param")
public class SpecParams {
    private Long id;
    private Long cid;
    @Column(name = "group_id")
    private Long gid;
    private String name;
    @Column(name = "`numeric`")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;

}
