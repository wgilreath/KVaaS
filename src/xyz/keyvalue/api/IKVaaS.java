/**
 * Title:        Key-Value as a Service (KVaaS) Java API
 * Description:  Interface for KVaaS API.
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

public interface IKVaaS {

    /**
     * A constant holding the maximum value an {@code int} a raw (@code String)
     * can have a length of 1,000,000-characters (@code char).
     */
    public final static int STRING_MAX_SIZE = 1000000;

    /**
     * A constant holding the maximum value an {@code int} an Base64 encoded
     * (@code String) can have a length of 750,000-characters (@code char).
     */
    public final static int BYTE_MAX_SIZE = 750000;

    /**
     * Returns the String (@code String) key value used with the key-value
     * store.
     *
     * @return the String (@code String) key.
     *
     */
    public String getKey();

    /**
     * Create a new String (@code String) key value used with the key-value
     * store.
     *
     * @throws KVaaSException for any HTTP errors--HTTP/1.1 400 Bad Request.
     *
     */
    public void newKey();

    /**
     * Returns the String (@code String) value by the key from the key-value
     * store.
     *
     * @return the String (@code String) element associated with the key.
     * @throws KVaaSException for any HTTP errors--HTTP/1.1 400 Bad Request.
     */
    public String getValue();

    /**
     * Associates the specified String value with the specified key in the
     * key-value store. If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param value is the value to be associated with the specified key
     * @return <tt>true</tt> for success, <tt>false</tt> for failure
     *
     * @throws KVaaSException for null, empty String, if String value is greater
     * than STRING_MAX_SIZE, or for any HTTP errors--HTTP/1.1 400 Bad Request.
     *
     */
    public boolean putValue(final String value);

    /**
     * Returns an array of bytes (@code byte) value by the key from the key-value
     * store.
     *
     * @return the array of bytes (@code byte) element associated with the key.
     * @throws KVaaSException for any HTTP errors--HTTP/1.1 400 Bad Request.
     */
    public byte[] getBytes();

    /**
     * Associates the specified array of bytes with the specified key in the
     * key-value store. If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param value is the value to be associated with the specified key
     * @return <tt>true</tt> for success, <tt>false</tt> for failure
     *
     * @throws KVaaSException for null, empty String, if String value is greater
     * than BYTE_MAX_SIZE, or for any HTTP errors--HTTP/1.1 400 Bad Request.
     *
     */
    public boolean putBytes(final byte[] value);

}// end interface IKVaaS
