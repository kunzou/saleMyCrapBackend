package kunzou.me.codingPractice.service;

import kunzou.me.codingPractice.domain.Product;
import kunzou.me.codingPractice.domain.ProductStatus;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

  private MongoTemplate mongoTemplate;

  public ProductServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public List<Product> getProducts() {
    return mongoTemplate.findAll(Product.class);
  }

  @Override
  public List<Product> getForSaleProducts() {
    return mongoTemplate.find(Query.query(Criteria.where("productStatus").ne(ProductStatus.Sold)), Product.class);
  }

  @Override
  public Product saveProduct(Product product) {
    return mongoTemplate.save(product);
  }

  @Override
  public Product getProduct(String id) {
    return mongoTemplate.findById(id, Product.class);
  }

  @Override
  public void delete(String id) {
    mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Product.class);
  }

  @Override
  public Product update(Product product) {
    return mongoTemplate.save(product);
  }

}
