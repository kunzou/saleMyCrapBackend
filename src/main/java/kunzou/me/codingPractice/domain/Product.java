package kunzou.me.codingPractice.domain;

import kunzou.me.codingPractice.dto.Image;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product {
  @Id
  @Indexed(unique = true)
  private String id;
  private String name;
  private BigDecimal price;
  private String description;
  private String productStatus;
  private Image coverImage;
  private List<Image> images;

  public Image getCoverImage() {
    return coverImage == null?new Image(): coverImage;
  }

  public List<Image> getImages() {
    return images == null?new ArrayList<>(): images;
  }
}
