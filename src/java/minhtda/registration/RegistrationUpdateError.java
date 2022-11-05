/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.registration;

import java.io.Serializable;

/**
 *
 * @author minhd
 */
public class RegistrationUpdateError implements Serializable{
    private String passwordLengthError;

    public RegistrationUpdateError() {
    }
    

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }
    
}
