package de.zenhomes.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Data class holding info about individual village energy consumption")
public class VillageConsumptionDto {

    @JsonProperty("village_name")
    private String villageName;

    @JsonProperty("consumption")
    private Double consumption;
}
