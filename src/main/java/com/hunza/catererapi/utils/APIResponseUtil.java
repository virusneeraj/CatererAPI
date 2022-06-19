package com.hunza.catererapi.utils;

import com.hunza.catererapi.dto.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class APIResponseUtil {

    public APIResponse successResponse(Object data){
        APIResponse apiResponse = new APIResponse();

        apiResponse.setStatus(HunzaConstant.SUCCESS_STATUS);
        apiResponse.setMessage(HunzaConstant.SUCCESS_MESSAGE);
        apiResponse.setData(data);
        return apiResponse;
    }

    public APIResponse errorResponse(Object data){
        APIResponse apiResponse = new APIResponse();

        apiResponse.setStatus(HunzaConstant.SUCCESS_STATUS);
        apiResponse.setMessage(HunzaConstant.SUCCESS_MESSAGE);
        apiResponse.setData(data);
        return apiResponse;
    }

    public APIResponse alreadyExist(Object data){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HunzaConstant.ALREADY_EXIST_STATUS);
        apiResponse.setMessage(HunzaConstant.ALREADY_EXIST_MESSAGE);
        apiResponse.setData(data);
        return apiResponse;
    }

    public APIResponse notFound(Object data){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HunzaConstant.NOT_FOUND_STATUS);
        apiResponse.setMessage(HunzaConstant.NOT_FOUND_MESSAGE);
        apiResponse.setData(data);
        return apiResponse;
    }

    public APIResponse badRequest(Object data){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HunzaConstant.BAD_REQUEST_STATUS);
        apiResponse.setMessage(HunzaConstant.BAD_REQUEST_MESSAGE);
        apiResponse.setData(data);
        return apiResponse;
    }


    public APIResponse failResponse(Object data){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HunzaConstant.FAIL_STATUS);
        apiResponse.setMessage(HunzaConstant.FAIL_MESSAGE);
        apiResponse.setData(data);
        return apiResponse;
    }

    public APIResponse authFailResponse(Object data){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HunzaConstant.AUTH_FAIL_STATUS);
        apiResponse.setMessage(HunzaConstant.AUTH_FAIL_MESSAGE);
        apiResponse.setData(data);
        return apiResponse;
    }


    //add entry if dding new response function
    public ResponseEntity<APIResponse> apiResponseToEntityResponse(APIResponse apiResponse){
        ResponseEntity<APIResponse> apiResponseResponseEntity = null;
        if(apiResponse == null)
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);//500

        switch (apiResponse.getStatus()){
            case HunzaConstant.SUCCESS_STATUS:
                apiResponseResponseEntity =  new ResponseEntity<>(apiResponse, HttpStatus.OK);//200
                break;
            case HunzaConstant.ERROR_STATUS:
                apiResponseResponseEntity =  new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);//500
                break;
            case HunzaConstant.ALREADY_EXIST_STATUS:
                apiResponseResponseEntity =  new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);//409
                break;
            case HunzaConstant.NOT_FOUND_STATUS:
                apiResponseResponseEntity =  new ResponseEntity<>(apiResponse, HttpStatus.NON_AUTHORITATIVE_INFORMATION);//203
                break;
            case HunzaConstant.BAD_REQUEST_STATUS:
                apiResponseResponseEntity =  new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);//400
                break;
            case HunzaConstant.AUTH_FAIL_STATUS:
                apiResponseResponseEntity =  new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);//401
                break;
            default:
                apiResponseResponseEntity =  new ResponseEntity<>(apiResponse, HttpStatus.FAILED_DEPENDENCY);//424

        }
        return apiResponseResponseEntity;
    }


}

