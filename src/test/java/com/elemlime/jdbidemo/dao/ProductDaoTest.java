package com.elemlime.jdbidemo.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
import com.elemlime.jdbidemo.model.dto.ProductDto;
import com.elemlime.jdbidemo.test.dao.TestData;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TestcontainersConfiguration.class, TestDaoConfig.class})
@SpringBootTest
public class ProductDaoTest extends AbstractDaoTest {
  @Autowired
  private ProductDao productDao;
  @Autowired
  private TestData testData;

  @BeforeEach
  void setUp() {testData.deleteAll();}

  @Test
  void testGetAll_Empty() {
    var products = productDao.getAll();
    assertThat(products).isEmpty();
  }

  @Test
  void testGetAll_WithData() {
    testData.loadProducts();
    var products = productDao.getAll();
    assertThat(products).hasSize(4);
    verifyProductBigBox(products.get(0));
    verifyProductSmallBox(products.get(1));
    verifyProductApple(products.get(2));
    verifyProductPear(products.get(3));
  }

  @Test
  void testGetByCategory_Empty() {
    var products = productDao.getByCategory(TestData.FRUITS_CATEGORY_ID);
    assertThat(products).isEmpty();
  }

  @Test
  void testGetByCategory_WithData() {
    testData.loadProducts();
    var products = productDao.getByCategory(TestData.FRUITS_CATEGORY_ID);
    assertThat(products).hasSize(2);
    verifyProductApple(products.get(0));
    verifyProductPear(products.get(1));
  }

  @Test
  void testGetById_NotFound() {
    testData.loadProducts();
    var productOpt = productDao.getById(UUID.randomUUID());
    assertThat(productOpt).isEmpty();
  }

  @Test
  void testGetById_Found() {
    testData.loadProducts();
    var productOpt = productDao.getById(TestData.BIG_BOX_PRODUCT_ID);
    assertThat(productOpt).isPresent();
    verifyProductBigBox(productOpt.get());
  }

  @Test
  void testCreateProduct() {
    testData.loadProducts();
    var productDto = new ProductDto();
    productDto.setCategoryId(TestData.FRUITS_CATEGORY_ID);
    productDto.setName("Orange");
    productDto.setDescription("A big juicy orange");
    productDto.setPrice(99);
    productDto.setInventory(130);
    var productId = productDao.createProduct(productDto);
    assertThat(productId).isNotNull();
    var productOpt = productDao.getById(productId);
    assertThat(productOpt).isPresent();
    var orange = productOpt.get();
    assertThat(orange.getName()).isEqualTo("Orange");
    assertThat(orange.getDescription()).isEqualTo("A big juicy orange");
    assertThat(orange.getPrice()).isEqualTo(99);
    assertThat(orange.getInventory()).isEqualTo(130);
    assertThat(orange.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(orange.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
  }

  @Test
  void testUpdateProduct() {
    testData.loadProducts();
    delay();
    var productDto = new ProductDto();
    productDto.setCategoryId(TestData.FRUITS_CATEGORY_ID);
    productDto.setName("Johnathon Apple");
    productDto.setDescription("A tasty tart apple");
    productDto.setPrice(129);
    productDto.setInventory(98);
    productDao.updateProduct(TestData.APPLE_PRODUCT_ID, productDto);
    var productOpt = productDao.getById(TestData.APPLE_PRODUCT_ID);
    assertThat(productOpt).isPresent();
    var apple = productOpt.get();
    assertThat(apple.getName()).isEqualTo("Johnathon Apple");
    assertThat(apple.getDescription()).isEqualTo("A tasty tart apple");
    assertThat(apple.getPrice()).isEqualTo(129);
    assertThat(apple.getInventory()).isEqualTo(98);
    assertThat(apple.getUpdated().toEpochMilli()).isGreaterThan(apple.getCreated().toEpochMilli());
  }

  @Test
  void testUpdatePrice() {
    testData.loadProducts();
    delay();
    productDao.updatePrice(TestData.APPLE_PRODUCT_ID, 99);
    var productOpt = productDao.getById(TestData.APPLE_PRODUCT_ID);
    assertThat(productOpt).isPresent();
    var apple = productOpt.get();
    assertThat(apple.getName()).isEqualTo("Apple");
    assertThat(apple.getDescription()).isEqualTo("A tasty red apple");
    assertThat(apple.getPrice()).isEqualTo(99);
    assertThat(apple.getInventory()).isEqualTo(50);
    assertThat(apple.getUpdated().toEpochMilli()).isGreaterThan(apple.getCreated().toEpochMilli());
  }

  @Test
  void testUpdateInventory() {
    testData.loadProducts();
    delay();
    productDao.updateInventory(TestData.APPLE_PRODUCT_ID, 49);
    var productOpt = productDao.getById(TestData.APPLE_PRODUCT_ID);
    assertThat(productOpt).isPresent();
    var apple = productOpt.get();
    assertThat(apple.getName()).isEqualTo("Apple");
    assertThat(apple.getDescription()).isEqualTo("A tasty red apple");
    assertThat(apple.getPrice()).isEqualTo(125);
    assertThat(apple.getInventory()).isEqualTo(49);
    assertThat(apple.getUpdated().toEpochMilli()).isGreaterThan(apple.getCreated().toEpochMilli());
  }
}
