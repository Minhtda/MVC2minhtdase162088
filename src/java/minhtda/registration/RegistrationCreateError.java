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
public class RegistrationCreateError implements Serializable{
    private String usernameLengthError;
    private String passwordLengthError;
    private String fullnameLengthError;
    private String confirmNotMatched;
    private String usernameisExisted;
    public RegistrationCreateError(){
        
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getUsernameisExisted() {
        return usernameisExisted;
    }

    public void setUsernameisExisted(String usernameisExisted) {
        this.usernameisExisted = usernameisExisted;
    }

        
}
