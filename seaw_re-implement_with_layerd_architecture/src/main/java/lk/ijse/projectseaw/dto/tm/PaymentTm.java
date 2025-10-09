package lk.ijse.projectseaw.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PaymentTm {
    private String   payment_id;
    private String   booking_id;
    private String   guest_id;
    private double   amount;
    private LocalDate Payment_date;
}
