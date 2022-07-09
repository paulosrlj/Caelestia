package com.ifpb.caelestiabackend.exception.achievement;

public class InvalidUrlImage extends Exception {
    public InvalidUrlImage(String msg){
        super(msg);
    }

    public InvalidUrlImage(String msg, Throwable cause){
        super(msg, cause);
    }


}
