package lk.ijse.projectseaw.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ReservationDTO {
    private String reservation_id;
    private String guest_name;
    private LocalDate check_in;
    private LocalDate check_out;
    private String roomId;
    private int noOf_guest;
    private String reservation_status;

}
