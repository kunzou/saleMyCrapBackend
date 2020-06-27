package kunzou.me.codingPractice.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmailDetail {
  private String product;
  private String from;
  private String email;
  private String phoneNumber;
  private String message;
  private LocalDateTime pickupDate;
}






