package de.zenhomes.assignment.service;

import de.zenhomes.assignment.model.ConsumptionReportDto;
import de.zenhomes.assignment.model.TimeFrame;

public interface ReportService {
    ConsumptionReportDto generateConsumptionReport(TimeFrame timeFrame);
}
