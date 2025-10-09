package lk.ijse.projectseaw.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class GuestDTO {
    private String GuestId;
    private String Fullname;
    private String Address;
    private String City;
    private String Postalcode;
    private String Country;
    private String email;
    private int Age;


}
