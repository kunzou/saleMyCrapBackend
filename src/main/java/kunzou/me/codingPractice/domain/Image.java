package kunzou.me.codingPractice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {
  private String link;
  private String smallLink;
  private String mediumLink;
  private String largeLink;
  private String hugeLink;

  public Image() {
  }

  public Image(String link) {
    this.link = link;
  }

  public Image(String link, String smallLink, String mediumLink, String largeLink, String hugeLink) {
    this.link = link;
    this.smallLink = smallLink;
    this.mediumLink = mediumLink;
    this.largeLink = largeLink;
    this.hugeLink = hugeLink;
  }
}
