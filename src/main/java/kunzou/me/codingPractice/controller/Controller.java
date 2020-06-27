package kunzou.me.codingPractice.controller;

import kunzou.me.codingPractice.dto.Description;
import kunzou.me.codingPractice.dto.EmailDetail;
import kunzou.me.codingPractice.dto.Image;
import kunzou.me.codingPractice.domain.Product;
import kunzou.me.codingPractice.service.EmailService;
import kunzou.me.codingPractice.service.ImgurService;
import kunzou.me.codingPractice.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
  private ImgurService imgurService;
  private EmailService emailService;

  public Controller(ProductService productService, ImgurService imgurService, EmailService emailService) {
    this.productService = productService;
    this.imgurService = imgurService;
    this.emailService = emailService;
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

  @PostMapping("/sendEmail")
  public ResponseEntity sendEmail(@RequestBody EmailDetail emailDetail) {
    try {
      emailService.sendEmail(emailDetail);
      return new ResponseEntity(new Description("邮件发送成功。我们会尽快回复您",
        "Message sent successfully. We will contact you soon")
        , new HttpHeaders(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(new Description("邮件发送失败，请直接联系204-881-5966",
        "Message sent unsuccessfully. Please contact 204-881-5966 or email to shery.c.liu@gmail.com"),
        new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
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
