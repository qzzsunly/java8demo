package com.qzztf;

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}