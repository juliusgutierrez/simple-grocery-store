package ph.use.titus.grocerystore.constant;

import java.math.BigDecimal;
import ph.use.titus.grocerystore.dto.Product;

public enum SalePromotion {

  BUY_ONE_GET_ONE {
    @Override
    public Product applyPromotion(Product product) {
      product.setPrice(product.getPrice().multiply(new BigDecimal(product.getQuantity())));
      product.setQuantity(product.getQuantity() * 2);
      return product;
    }
  },

  BUY_TWO_GET_ONE {
    @Override
    public Product applyPromotion(Product product) {
      int count = 0;
      if (product.getQuantity() > 1) {
        for (int q = 1; q <= product.getQuantity(); q++) {

          //find if its even number
          if (q % 2 == 0) {
            count++;
          }
        }
      }
      product.setPrice(product.getPrice().multiply(new BigDecimal(product.getQuantity())));
      product.setQuantity(product.getQuantity() + count);
      return product;
    }


  };

  public abstract Product applyPromotion(Product product);


}
