package kunzou.me.codingPractice.controller;

import kunzou.me.codingPractice.domain.Image;
import kunzou.me.codingPractice.domain.Product;
import kunzou.me.codingPractice.service.CachingService;
import kunzou.me.codingPractice.service.ImgurService;
import kunzou.me.codingPractice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Controller {

  private ProductService productService;
  private CachingService cachingService;
  private ImgurService imgurService;

  public Controller(ProductService productService) {
    this.productService = productService;
  }

  @Autowired
  public void setCachingService(CachingService cachingService) {
    this.cachingService = cachingService;
  }

  @Autowired
  public void setImgurService(ImgurService imgurService) {
    this.imgurService = imgurService;
  }

  @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> getProducts() {
    return ResponseEntity.ok().body(productService.getProducts());
  }

  @GetMapping(value = "/forSaleProducts", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> getForSaleProducts() {
    return ResponseEntity.ok().body(productService.getForSaleProducts());
  }

  @PutMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
    return ResponseEntity.ok().body(productService.saveProduct(product));
  }

  @PostMapping("/product")
  public ResponseEntity<Product> addNewBlog(@RequestBody Product product) {
    return ResponseEntity.ok().body(productService.saveProduct(product));
  }

  @GetMapping("/product/{id}")
  public Product getProduct(@PathVariable("id") String id) {
    return productService.getProduct(id);
  }

  @DeleteMapping("/product/{id}")
  public ResponseEntity deleteBlog(@PathVariable("id") String id) {
    productService.delete(id);
    return ResponseEntity.ok().build();
  }

  @CrossOrigin
  @PostMapping("/uploadFile")
  public Image uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
    return imgurService.uploadImage(file);
  }

/*  @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Customer>> getCustomers() {
    return ResponseEntity.ok().body(rentalService.getAllCustomers());
  }

  @GetMapping(value = "/customersAsync", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Customer>> getCustomersAsync() {
    return ResponseEntity.ok().body(rentalService.getAllCustomers());
  }

  @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerInformation> getCustomerById(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(rentalService.getCustomerInformation(id));
  }

  @GetMapping(value = "/availableFilms", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AvailableFilm>> getAvailableFilms() {
    return ResponseEntity.ok().body(rentalService.getAvailableFilms());
  }

  @GetMapping(value = "/film/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FilmInformation> getFilmById(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(rentalService.getFilmInformation(id));
  }

  @GetMapping("/cache/reset/all")
  public ResponseEntity resetAllCache() {
    cachingService.clearAllCache();
    return ResponseEntity.ok().build();
  }

  @GetMapping("/cache/reset/customer/{id}")
  public ResponseEntity resetCustomerCache(@PathVariable("id") Long id) {
    cachingService.removeCustomer(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/cache/reset/film/{id}")
  public ResponseEntity resetFilmCache(@PathVariable("id") Long id) {
    cachingService.removeFilm(id);
    return ResponseEntity.ok().build();
  }*/
}
