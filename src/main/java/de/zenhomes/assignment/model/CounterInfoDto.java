package de.zenhomes.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterInfoDto {

    @JsonProperty("id")
    @ApiModelProperty("ID of requested counter")
    private Long counterId;

    @JsonProperty("village_name")
    @ApiModelProperty("Name of village to which this counter belongs")
    private String villageName;
}
