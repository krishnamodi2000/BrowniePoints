/**
 * Represents an exception response, which includes a status and a message.
 */
package com.GRP3.BPA.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    /**
     * The status of the exception response.
     */
    @Getter @Setter
    public boolean status;

    /**
     * The message of the exception response.
     */
    @Getter @Setter
    public String message;
}
