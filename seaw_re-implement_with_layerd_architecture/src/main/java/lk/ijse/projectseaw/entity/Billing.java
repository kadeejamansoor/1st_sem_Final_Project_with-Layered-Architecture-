package lk.ijse.projectseaw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//encap
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Billing {
    private String paymentId;
    private String bookingId;
    private String guestId;
    private double payAmount;
    private String payDate;
}
