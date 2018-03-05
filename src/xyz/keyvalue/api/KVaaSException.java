/**
 * Title:        Key-Value as a Service (KVaaS) Java API
 * Description:  Exception object thrown by methods in class KVaaS.
 * Copyright:    Copyright (c) 2018.  All Rights Reserved.
 *
 * @author William F. Gilreath (wfgilreath@yahoo.com)
 * @version 1.0
 *
 * This file is part of Java Key-Value as a Service (KVaaS) library software
 * project. the Java KVaaS library is free software; you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 */
package xyz.keyvalue.api;

/**
 * <code>KVaaSException</code> is the subclass of those runtime exceptions that
 * can be thrown during the normal operation of the Java Virtual Machine.
 * <p>
 * A method is not required to declare in its <code>throws</code> clause any
 * subclasses of <code>RuntimeException</code> that might be thrown during the
 * execution of the method but not caught.
 *
 */
@SuppressWarnings("serial")
public final class KVaaSException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param message is the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    public KVaaSException(final String message) {
        super(message);
    }//end constructor

    /**
     * Constructs a new runtime exception with the specified cause and a detail
     * message of the cause.(which typically contains the class and detail
     * message of
     * <tt>cause</tt>). This constructor is useful for runtime exceptions that
     * are little more than wrappers for re-thrown exceptions.
     *
     * @param except the exception to rethrow
     *
     */
    public KVaaSException(final Exception except) {
        super(except);
    }//end constructor

}//end class KVaaSException
