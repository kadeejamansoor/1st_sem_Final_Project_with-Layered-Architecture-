package lk.ijse.projectseaw.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Salary {
    private String salaryId;
    private String empFullName;
    private double salaryAmount;
    private String salaryDate;
    private String employeeId;
}
