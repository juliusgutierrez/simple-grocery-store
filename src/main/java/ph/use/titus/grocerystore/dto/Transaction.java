package ph.use.titus.grocerystore.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transaction implements Serializable {

  private BigDecimal totalPrice = BigDecimal.ZERO;
  List<Product> checkoutProduct;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("\n");
    sb.append(String.format("%-15s %5s %10s\n", "Item", "Qty", "Price"));
    sb.append(String.format("%-15s %5s %10s\n", "----", "---", "-----"));
    checkoutProduct.forEach(product -> {
      sb.append(String.format("%-15s %5s %10s\n", product.getName(), product.getQuantity(), product.getPrice()));
    });
    sb.append("\n\n");
    sb.append(String.format("%-15s %5s %10s\n", "----", "---", "-----"));

    sb.append(String.format("%-15s %5s %10s\n", "Total", "   ", totalPrice));
    return sb.toString();
  }
}
