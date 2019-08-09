package ph.use.titus.grocerystore.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import ph.use.titus.grocerystore.constant.SalePromotion;
import ph.use.titus.grocerystore.dto.Product;
import ph.use.titus.grocerystore.dto.Transaction;

@Service
public class ComputationService {


  /**
   * do the checkout
   * @param products
   * @return Transaction of the checkout
   */
  public Transaction doCheckOut(Product... products) {
    Transaction transaction = new Transaction();
    transaction.setTotalPrice(computeTotalPrice(products));
    transaction.setCheckoutProduct(Arrays.asList(products));
    return transaction;
  }

  /**
   * compute total price of the products
   * @param products
   * @return price
   */
  public BigDecimal computeTotalPrice(Product... products) {
    return Stream.of(products)
        .map(ComputationService::processProductDetails)
        .map(Product::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  /**
   * process the price of the product, when a product is under sale / promotions
   * the price and qty will be updated based on the type of Sale Promotions
   *
   * when the product has a weight, it will also update the price of the product
   * PLEASE NOTE: for this exam, weight only accept whole number and considered as Kilogram.
   *
   * @param product
   * @return price
   */
  public static Product processProductDetails(Product product) {
    if (product.getPromotion() != null) {
      SalePromotion salePromotion = product.getPromotion();
      return salePromotion.applyPromotion(product);
    }

    if (product.getWeight() > 0) {
      product.setPrice(product.getPrice().multiply(new BigDecimal(product.getWeight())));
    }

    return product;
  }

}
