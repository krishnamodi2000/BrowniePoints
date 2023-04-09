package com.GRP3.BPA.exceptions;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomizableException extends Throwable {

    @Getter @Setter
    private boolean status;

    @Getter @Setter
    private String message;
}
