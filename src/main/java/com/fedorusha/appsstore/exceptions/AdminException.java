package com.fedorusha.appsstore.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AdminException extends Exception{
    public AdminException(String mes){ super(mes);}

}