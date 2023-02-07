package pl.domanski.carrent.worker.rent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carrent.worker.rent.model.dto.WorkerRentBasicInfo;
import pl.domanski.carrent.worker.rent.model.dto.WorkerRentDto;
import pl.domanski.carrent.worker.rent.service.WorkerRentService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/worker/rents")
public class WorkerRentController {

    private final WorkerRentService workerRentService;

    @GetMapping
    public List<WorkerRentBasicInfo> getNotCompletedRents() {
        return workerRentService.getNotCompletedRents();
    }

    @GetMapping("/{id}")
    public WorkerRentDto getRent(@PathVariable Long id) {
        return workerRentService.getRent(id);
    }

    @PatchMapping("/{id}")
    public void patchRent(@PathVariable Long id, @RequestBody Map<String, String> values) {
        workerRentService.patchRentStatus(id, values);
    }

}
