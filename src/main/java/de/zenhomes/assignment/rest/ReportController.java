package de.zenhomes.assignment.rest;

import de.zenhomes.assignment.model.ConsumptionReportDto;
import de.zenhomes.assignment.model.TimeFrame;
import de.zenhomes.assignment.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(CounterController.class);
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping("consumption_report")
    public ResponseEntity<ConsumptionReportDto> getConsumptionReport(@RequestParam("duration") TimeFrame duration) {
        logger.debug("ReportController.getConsumptionReport.start");
        var report = reportService.generateConsumptionReport(duration);
        logger.debug("ReportController.getConsumptionReport.end");
        return ResponseEntity.ok(report);
    }

}
