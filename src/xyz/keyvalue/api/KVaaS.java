/**
 * Title:        Key-Value as a Service (KVaaS) Java API
 * Description:  Class implementation of KVaaS API
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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.util.Base64;

public final class KVaaS implements IKVaaS, Serializable {

    /** 
     * use serialVersionUID as long (@code long) for interoperability 
     */
    private static final long serialVersionUID = 7702903119360909602L;

    /**
     * A constant holding the HTTP API prefix an {@code String}.
     */
    private final static String HTTP_API_PREFIX = "https://api.keyvalue.xyz/";

    /**
     * A constant holding the HTTP User-Agent identifier.
     */
    private final static String USER_AGENT = "api.keyvalue.xyz Java API/1.0";

    /**
     * A constant holding the HTTP new key prefix an {@code String}.
     */
    private final static String NEW_KEY = "new/key";

    /**
     * The value of the String (@code String) HTTP key-value used to associate
     * with data.
     *
     */
    private String key = "";

    /**
     * Constructs a KVaaS object with a new String (@code String) HTTP key that
     * represents the key associated with a value.
     */
    public KVaaS() {
        this.key = KVaaS.key();
    }// end constructor

    /**
     * Constructs a newly allocated {@code KVaas} object using String (@code
     * String) HTTP key that represents the key associated with a value.
     *
     * @param key the immutable String (@code String) HTTP key to be associated
     * with the {@code String} (@code byte) object.
     */
    public KVaaS(final String key) {
        if (key.contains(KVaaS.HTTP_API_PREFIX)) {
            this.key = key;
        } else {
            throw new KVaaSException(
                    "Invalid key; key does not begin with prefix: "
                    + KVaaS.HTTP_API_PREFIX + "!");
        }
    }// end constructor

    /**
     * Returns the String (@code String) key value used with the key-value
     * store.
     *
     * @return the String (@code String) key.
     *
     */
    public final String getKey() {
        return this.key;
    }// end getKey

    /**
     * Create a new String (@code String) key value used with the key-value
     * store.
     *
     * @throws KVaaSException for any HTTP errors--HTTP/1.1 400 Bad Request.
     *
     */
    public final void newKey() {
        this.key = KVaaS.key();
    }// end newKey

    /**
     * A static helper method generates a new String (@code String) HTTP key
     * that represents the key associated with a value.
     */
    private final static String key() {

        try {
            return httpPost(HTTP_API_PREFIX + NEW_KEY);
        } catch (Exception ex) {
            throw new KVaaSException(ex);
        }
    }// end constructor

    /**
     * The encoder (@code Base64.Encoder) is used to encode 8-bit datum into a
     * 6-bit datum value to encode byte (@code byte) data into a String (@code
     * String) value.
     */
    private final static Base64.Encoder enc = Base64.getEncoder();

    /**
     * The decoder (@code Base64.Decoder) is used to decode 6-bit datum into a
     * 8-bit datum value to decode byte (@code byte) data into a String (@code
     * String) value.
     */
    private final static Base64.Decoder dec = Base64.getDecoder();

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
    public final boolean putBytes(final byte[] data) {

        if (data == null) {
            throw new KVaaSException("Byte value data is null!");
        }//end if

        if (data.length == 0) {
            throw new KVaaSException("Byte value data is empty!");
        }//end if

        if (data.length > IKVaaS.BYTE_MAX_SIZE) {
            throw new KVaaSException(
                    "Byte value data length exceeds maximum size of "
                    + IKVaaS.BYTE_MAX_SIZE + "!");
        }//end if

        return this.putValue(enc.encodeToString(data));
    }// end putBytes

    /**
     * Returns an array of bytes (@code byte) value by the key from the
     * key-value store.
     *
     * @return the array of bytes (@code byte) element associated with the key.
     * @throws KVaaSException for any HTTP errors--HTTP/1.1 400 Bad Request.
     */
    public final byte[] getBytes() {
        try {
            return dec.decode(this.getValue());
        } catch (Exception ex) {
            System.out.printf("getBytes() %n%s%n",
                    ex.toString() + " " + ex.getMessage());

            return new byte[0];
        }// end try
    }// end getBytes

    /**
     * A static helper method for HTTP GET the KVaaS (@code KVaaS) in the
     * key-value store.
     *
     * @param url the String (@code String) URL
     */
    private final static String httpGet(final String url) {

        try {

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }//end while
            in.close();

            return response.toString();

        } catch (Exception ex) {
            throw new KVaaSException(ex);
        }// end try

    }// end httpGet

    /**
     * Returns the String (@code String) value by the key from the key-value
     * store.
     *
     * @return the String (@code String) element associated with the key.
     * @throws KVaaSException for any HTTP errors--HTTP/1.1 400 Bad Request.
     */
    public final String getValue() {
        return KVaaS.httpGet(this.key);
    }// end getValue

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
    public final boolean putValue(final String value) {

        if (value == null) {
            throw new KVaaSException("String value data is null!");
        }//end if

        if (value.equals("")) {
            throw new KVaaSException("String value data is empty!");
        }//end if

        if (value.length() > IKVaaS.STRING_MAX_SIZE) {
            throw new KVaaSException(
                    "String value data length exceeds maximum size of "
                    + IKVaaS.STRING_MAX_SIZE + "!");
        }//end if

        KVaaS.httpPost(this.key, value);
        return true;

    }// end putValue

    /**
     * A static helper method for HTTP POST the KVaaS (@code KVaaS) in the
     * key-value store.
     *
     * @param url the String (@code String) URL
     */
    private final static String httpPost(final String url) {
        try {
            return KVaaS.httpPost(url, null);
        } catch (Exception ex) {
            throw new KVaaSException(ex);
        }// end try

    }// end httpPost

    /**
     * A static helper method for HTTP POST the KVaaS (@code KVaaS) in the
     * key-value store.
     *
     * @param url the String (@code String) URL
     * @param value the String (@code String) value
     */
    private final static String httpPost(final String url, final String value) {

        try {

            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);

            con.setDoOutput(true);
            DataOutputStream dos = new DataOutputStream(con.getOutputStream());

            if (value != null) {
                dos.writeBytes(value);
            }// end if

            dos.flush();
            dos.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }// end while

            in.close();

            return response.toString();

        } catch (Exception ex) {
            throw new KVaaSException(ex);
        }// end try

    }// end httpPost

}// end class KVaaS
