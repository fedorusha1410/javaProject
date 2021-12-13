package com.fedorusha.appsstore.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthenticationException extends Exception{

    public AuthenticationException(String mes){ super(mes);}
}
