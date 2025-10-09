package lk.ijse.projectseaw.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Stock {
    private String stockId;
    private String StockName;
    private int quantity;
}
