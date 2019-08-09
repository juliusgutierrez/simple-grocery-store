package ph.use.titus.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ph.use.titus.grocerystore.constant.SalePromotion;
import ph.use.titus.grocerystore.dto.Product;
import ph.use.titus.grocerystore.dto.Transaction;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ComputationServiceTest {

  private static final Logger LOG = LoggerFactory.getLogger(ComputationServiceTest.class);


  @Autowired
  private ComputationService computationService;

  @Test
  public void do_checkout_test() {

    Product noodle = Product
        .builder()
        .name("Noodle")
        .price(new BigDecimal(10.00)).build();

    Transaction transaction = computationService.doCheckOut(noodle);
    assertEquals(new BigDecimal(10), transaction.getTotalPrice());

  }

  @Test
  public void do_checkout_bulk_test() {
    Product rice = Product
        .builder()
        .name("Rice")
        .price(new BigDecimal("45"))
        .weight(2)
        .build();

    Product noodle = Product
        .builder()
        .name("Noodle")
        .price(new BigDecimal(10.00)).build();

    Product soap = Product.builder()
        .name("Bath Soap")
        .price(new BigDecimal(34))
        .build();

    Transaction transaction = computationService.doCheckOut(rice, noodle, soap);
    assertEquals(new BigDecimal(134), transaction.getTotalPrice());

  }

  @Test
  public void do_checkout_with_promotions_buy_1_get_1_test() {

    Product noodles = Product
        .builder()
        .name("Noodles")
        .price(new BigDecimal("10"))
        .promotion(SalePromotion.BUY_ONE_GET_ONE)
        .build();

    Transaction transaction = computationService.doCheckOut(noodles);
    assertEquals(2, transaction.getCheckoutProduct().get(0).getQuantity());
    assertEquals(new BigDecimal(10), transaction.getTotalPrice());

  }

  @Test
  public void do_checkout_with_promotions_buy_1_get_1__qty_2_test() {

    Product noodles = Product
        .builder()
        .name("Noodles")
        .quantity(2)
        .price(new BigDecimal("10"))
        .promotion(SalePromotion.BUY_ONE_GET_ONE)
        .build();

    Transaction transaction = computationService.doCheckOut(noodles);
    assertEquals(4, transaction.getCheckoutProduct().get(0).getQuantity());
    assertEquals(new BigDecimal(20), transaction.getTotalPrice());

  }

  @Test
  public void do_checkout_with_promotions_buy_2_get_1_test() {

    Product noodles = Product
        .builder()
        .name("Noodles")
        .price(new BigDecimal("10"))
        .quantity(5)
        .promotion(SalePromotion.BUY_TWO_GET_ONE)
        .build();

    Transaction transaction = computationService.doCheckOut(noodles);
    assertEquals(7, transaction.getCheckoutProduct().get(0).getQuantity());
    assertEquals(new BigDecimal(50), transaction.getTotalPrice());

  }

  @Test
  public void do_checkout_print_receipt_test() {

    Product noodles = Product
        .builder()
        .name("Noodles")
        .price(new BigDecimal("10"))
        .quantity(5)
        .promotion(SalePromotion.BUY_TWO_GET_ONE)
        .build();

    Product rice = Product
        .builder()
        .name("Rice")
        .price(new BigDecimal("45"))
        .weight(1)
        .build();

    Product noodle = Product
        .builder()
        .name("Noodle")
        .price(new BigDecimal(10.00)).build();

    Product soap = Product.builder()
        .name("Bath Soap")
        .price(new BigDecimal(34))
        .build();

    Transaction transaction = computationService.doCheckOut(noodles, rice, noodle, soap);
    assertNotNull(transaction);
    LOG.info("{}", transaction);
  }

}
