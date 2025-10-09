package lk.ijse.projectseaw.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class StockDTO {
    private String stockId;
    private String StockName;
    private int quantity;
}
