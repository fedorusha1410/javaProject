package com.fedorusha.appsstore.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends Exception{
    public UserException(String mes){ super(mes);}

}
