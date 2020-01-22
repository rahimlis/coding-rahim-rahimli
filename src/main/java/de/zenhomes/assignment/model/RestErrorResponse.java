package de.zenhomes.assignment.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * Error Rest response
 */
@ApiModel("Error response from Rest service")
@Data
@NoArgsConstructor
public class RestErrorResponse implements Serializable  {

    private static final long serialVersionUID = 7602767156735008891L;

    @ApiModelProperty("Error unique ID")
    private String uuid;

    @ApiModelProperty("Error code - NOT_FOUND, INTERNAL_SERVER_ERROR etc")
    private String code;

    @ApiModelProperty("HTTP error code - 404, 503 etc")
    private int httpCode;

    @ApiModelProperty("Error message")
    private String message;


    public RestErrorResponse(String uuid, HttpStatus status, String message) {
        this.uuid = uuid;
        this.code = status.name();
        this.httpCode = status.value();
        this.message = message;
    }

    public RestErrorResponse(String uuid, HttpStatus status,String code, String message) {
        this.uuid = uuid;
        this.code = code;
        this.httpCode = status.value();
        this.message = message;
    }

    public RestErrorResponse(String uuid, String code, HttpStatus status) {
        this.uuid = uuid;
        this.code = code;
        this.httpCode = status.value();
    }

}
