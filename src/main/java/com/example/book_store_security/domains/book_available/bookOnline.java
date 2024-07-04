package com.example.book_store_security.domains.book_available;

public class bookOnline implements StatusBook{
    @Override
    public boolean bookAvailable() {
        return true;
    }
}
