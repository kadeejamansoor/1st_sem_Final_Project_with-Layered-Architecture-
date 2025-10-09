package lk.ijse.projectseaw.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SalaryDTO {
    private String salaryId;
    private String empFullName;
    private double salaryAmount;
    private String salaryDate;
    private String employeeId;
}
