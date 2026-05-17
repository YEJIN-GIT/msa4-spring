package com.msa4spring.requests;

import jakarta.validation.constraints.NotBlank;

public record EmployeesModifyRequest(
        Long empId,

        @NotBlank(message = "생일 필수")
        String birth
) {
}
