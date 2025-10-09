package lk.ijse.projectseaw.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class StockTm {
    private String stockId;
    private String StockName;
    private int quantity;
}
