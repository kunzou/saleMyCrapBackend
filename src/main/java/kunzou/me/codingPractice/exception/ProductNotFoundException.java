package kunzou.me.codingPractice.exception;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String messageKey,Long id) {
    super(String.format(messageKey, id));
  }
}
