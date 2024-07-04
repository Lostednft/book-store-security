package com.example.book_store_security.domains.book_available;

public class bookOffline implements StatusBook{
    @Override
    public boolean bookAvailable() {
        return false;
    }
}
