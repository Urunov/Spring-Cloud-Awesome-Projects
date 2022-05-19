/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.util;

import com.qqb.travelclub.util.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TravelClubExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchClubException.class)
    ResponseEntity<ApiErrorMessage> handleNoSuchClubException(NoSuchClubException exception){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setCode("9");
        apiErrorMessage.setName(NoSuchClubException.class.getSimpleName());
        apiErrorMessage.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorMessage);
    }

    @ExceptionHandler(NoSuchMemberException.class)
    ResponseEntity<ApiErrorMessage> handleNoSuchMemberException(NoSuchMemberException exception){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setCode("9");
        apiErrorMessage.setName(NoSuchMemberException.class.getSimpleName());
        apiErrorMessage.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorMessage);    }

    @ExceptionHandler(NoSuchMembershipException.class)
    ResponseEntity<ApiErrorMessage> handleNoSuchMembershipException(NoSuchMembershipException exception){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setCode("9");
        apiErrorMessage.setName(NoSuchMembershipException.class.getSimpleName());
        apiErrorMessage.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorMessage);    }

    @ExceptionHandler(ClubDuplicationException.class)
    ResponseEntity<ApiErrorMessage> handleClubDuplicationException(ClubDuplicationException exception){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setCode("9");
        apiErrorMessage.setName(ClubDuplicationException.class.getSimpleName());
        apiErrorMessage.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorMessage);    }

    @ExceptionHandler(InvalidEmailException.class)
    ResponseEntity<ApiErrorMessage> handleNoSuchMembershipException(InvalidEmailException exception){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setCode("9");
        apiErrorMessage.setName(InvalidEmailException.class.getSimpleName());
        apiErrorMessage.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorMessage);    }
    @ExceptionHandler(MemberDuplicationException.class)
    ResponseEntity<ApiErrorMessage> handleMemberDuplicationException(MemberDuplicationException exception){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setCode("9");
        apiErrorMessage.setName(MemberDuplicationException.class.getSimpleName());
        apiErrorMessage.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorMessage);    }
    @ExceptionHandler(MembershipDuplicationException.class)
    ResponseEntity<ApiErrorMessage> handleMembershipDuplicationException(MembershipDuplicationException exception){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setCode("9");
        apiErrorMessage.setName(MembershipDuplicationException.class.getSimpleName());
        apiErrorMessage.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorMessage);    }
    @ExceptionHandler(ClubIntroLongerThanException.class)
    ResponseEntity<ApiErrorMessage> handleClubIntroLongerThanException(ClubIntroLongerThanException exception){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setCode("9");
        apiErrorMessage.setName(ClubIntroLongerThanException.class.getSimpleName());
        apiErrorMessage.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorMessage);    }
    @ExceptionHandler(ClubNameLongerThanException.class)
    ResponseEntity<ApiErrorMessage> handleClubNameLongerThanException(ClubNameLongerThanException exception){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.setCode("998");
        apiErrorMessage.setName(ClubNameLongerThanException.class.getSimpleName());
        apiErrorMessage.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(apiErrorMessage);    }
}
