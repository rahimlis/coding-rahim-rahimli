package de.zenhomes.assignment.rest;

import de.zenhomes.assignment.model.CounterInfoDto;
import de.zenhomes.assignment.model.CounterRecordDto;
import de.zenhomes.assignment.service.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CounterController {

    private static final Logger logger = LoggerFactory.getLogger(CounterController.class);
    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping("/counter_callback")
    public ResponseEntity saveCounterRecord(@RequestBody CounterRecordDto recordDto) {
        logger.debug("Controller.saveCounterRecord.start");
        counterService.saveCounterRecord(recordDto);
        logger.debug("Controller.saveCounterRecord.end");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/counter")
    public ResponseEntity<CounterInfoDto> getCounterInfo(@RequestParam("id") Long id) {
        logger.debug("Controller.getCounterInfo.start");
        var counterInfo = counterService.getCounterInfo(id);
        logger.debug("Controller.getCounterInfo.end");
        return ResponseEntity.ok(counterInfo);
    }
}
