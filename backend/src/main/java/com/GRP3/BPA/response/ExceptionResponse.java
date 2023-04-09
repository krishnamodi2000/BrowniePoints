package com.GRP3.BPA.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    @Getter @Setter
    public boolean status;

    @Getter @Setter
    public String message;
}
