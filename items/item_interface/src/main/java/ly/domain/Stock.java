package ly.domain;

import lombok.Data;

import javax.persistence.Table;

@Table(name = "tb_stock")
@Data
public class Stock {
    private Long sku_id;
    private Integer seckillStock;
    private Integer seckillTotal;
    private Integer stock;
}
