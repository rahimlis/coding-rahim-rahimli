package de.zenhomes.assignment.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Data class holding report about all villages energy consumption")
public class ConsumptionReportDto {
    private List<VillageConsumptionDto> villages;
}
