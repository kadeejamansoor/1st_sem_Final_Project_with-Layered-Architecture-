package lk.ijse.projectseaw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//encap
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BillingDTO {
    private String paymentId;
    private String bookingId;
    private String guestId;
    private double payAmount;
    private String payDate;
}
