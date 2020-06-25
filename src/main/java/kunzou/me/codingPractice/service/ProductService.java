package kunzou.me.codingPractice.service;

import kunzou.me.codingPractice.domain.Product;

import java.util.List;

public interface ProductService {
  List<Product> getProducts();
  public List<Product> getForSaleProducts();
  Product saveProduct(Product product);
  Product getProduct(String id);
  void delete(String id);
  Product update(Product product);
}
