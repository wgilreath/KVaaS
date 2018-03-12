About
=============================

The Java Key Value as a Service (KVaaS) is a library for using the online HTTP key-value store at https://keyvalue.xyz/ which has documentation at 
https://github.com/kvaas/docs for the web service API.

Features
========

* Simple API following the KVaaS HTTP API.
* Self-contained using on the Java Development Kit 1.8 libraries.
* Uses unchecked exceptions for flexibility in error handling.
* Uses Java interface for specification and Java class for implementation.
* Use either String datum or array of bytes data for put/get values.
* HTTP Get/Post functionality is built into library.

License
===============================

This library is licensed under the GNU General Public License.

Usage
===============================

	KVaaS value = new KVaaS();
	
	value.putValue("Hello World!!!");
	
	String helloWorld = value.getValue();
	
	String helloWorldKey = value.getKey();
    

External Dependencies
=====================================
This library is self-contained. It does not depend on any additional libraries, only the Java Development Kit 1.8 libraries.
