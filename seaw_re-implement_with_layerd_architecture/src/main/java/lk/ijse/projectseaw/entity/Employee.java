package lk.ijse.projectseaw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String id;
    private  String name;
    private  String contact;
    private  String address;
    private String role;
}
