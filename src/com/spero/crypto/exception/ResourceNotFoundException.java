/*
 * *
 *  * *****************************************************************
 *  * Copyright  2020.
 *  * All Rights Reserved to
 *  * NAGAD.
 *  * Redistribution or Using any part of source code or binary
 *  * can not be done without permission of NAGAD.
 *  * *****************************************************************
 *  *
 *  * @author - Md. Imrul Hasan
 *  * @email - imrul.hasan@nagad.com.bd
 *  * @date: 8/25/20, 3:55 PM
 *  * ****************************************************************
 *
 */

package com.spero.crypto.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5861310537366287163L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }
}
