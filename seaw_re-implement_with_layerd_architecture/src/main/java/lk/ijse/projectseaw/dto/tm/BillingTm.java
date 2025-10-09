package lk.ijse.projectseaw.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillingTm {

    private String paymentId;
    private String bookingId;
    private String guestId;
    private double payAmount;
    private String payDate;



}
