package com.fedorusha.appsstore.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegistrationException extends Exception{
    public RegistrationException(String mes){ super(mes);}
}
