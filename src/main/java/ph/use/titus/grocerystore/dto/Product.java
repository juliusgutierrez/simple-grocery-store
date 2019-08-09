package ph.use.titus.grocerystore.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ph.use.titus.grocerystore.constant.SalePromotion;

@Getter
@Setter
@Builder
public class Product implements Serializable {

  private String name;
  private BigDecimal price;
  private int weight;
  @Builder.Default
  private int quantity = 1;
  private SalePromotion promotion;
}
