package com.mergetechng.jobs.commons.util;



import com.mergetechng.jobs.commons.dto.ApiResponseDto;

import java.util.Date;

public class ApiResponseUtil {
    public static ApiResponseDto process(String message, String statusCode, String action, Date requestDate, Object data) {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setStatusCode(statusCode);
        apiResponseDto.setResponseDate(requestDate);
        apiResponseDto.setMessage(message);
        apiResponseDto.setAction(action);
        apiResponseDto.setData(data);
        return apiResponseDto;
    }
}