package de.zenhomes.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Data transfer object holding from counter callback")
public class CounterRecordDto {

    @JsonProperty("counter_id")
    @ApiModelProperty("ID of counter where the amount should be written")
    private Long counterId;

    @JsonProperty("amount")
    @ApiModelProperty("Amount of electricity to record")
    private Double amount;
}