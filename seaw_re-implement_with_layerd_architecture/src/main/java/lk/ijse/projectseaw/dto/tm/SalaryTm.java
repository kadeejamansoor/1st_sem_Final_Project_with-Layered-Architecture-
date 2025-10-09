package lk.ijse.projectseaw.dto.tm;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SalaryTm {
    private String salaryId;
    private String empFullName;
    private double salaryAmount;
    private String salaryDate;
    private String employeeId;
}
