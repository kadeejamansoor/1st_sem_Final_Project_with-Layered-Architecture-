package lk.ijse.projectseaw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private String id;
    private  String name;
    private  String contact;
    private  String address;
    private String role;
}
