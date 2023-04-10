package com.GRP3.BPA.exceptions;

import lombok.*;


/**
 * A customizable exception that contains a status and message.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomizableException extends Throwable {

    /**
     * A boolean indicating the status of the exception.
     */
    @Getter @Setter
    private boolean status;

    /**
     * A string representing the message associated with the exception.
     */
    @Getter @Setter
    private String message;
}
