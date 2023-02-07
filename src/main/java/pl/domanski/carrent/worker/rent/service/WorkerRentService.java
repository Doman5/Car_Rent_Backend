package pl.domanski.carrent.worker.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carrent.worker.rent.mapper.WorkerRentDtoMapper;
import pl.domanski.carrent.worker.rent.model.WorkerRent;
import pl.domanski.carrent.worker.rent.model.WorkerRentStatus;
import pl.domanski.carrent.worker.rent.model.dto.WorkerRentBasicInfo;
import pl.domanski.carrent.worker.rent.model.dto.WorkerRentDto;
import pl.domanski.carrent.worker.rent.repository.WorkerRentLogRepository;
import pl.domanski.carrent.worker.rent.repository.WorkerRentRepository;

import java.util.List;
import java.util.Map;

import static pl.domanski.carrent.worker.rent.mapper.WorkerRentDtoMapper.createStatusChangeLog;
import static pl.domanski.carrent.worker.rent.mapper.WorkerRentDtoMapper.mapToWorkerRentDto;

@Service
@RequiredArgsConstructor
public class WorkerRentService {

    private final WorkerRentRepository workerRentRepository;
    private final WorkerRentLogRepository workerRentLogRepository;

    public List<WorkerRentBasicInfo> getNotCompletedRents() {
        return workerRentRepository.findAllByRentStatusNotIn(List.of(WorkerRentStatus.COMPLETED, WorkerRentStatus.CANCELED, WorkerRentStatus.NEW))
                .stream().map(WorkerRentDtoMapper::mapToWorkerRentBasicInfo)
                .toList();
    }

    public WorkerRentDto getRent(Long id) {
        return mapToWorkerRentDto(workerRentRepository.findById(id).orElseThrow());
    }

    public void patchRentStatus(Long id, Map<String, String> values) {
        WorkerRent rent = workerRentRepository.findById(id).orElseThrow();
        patchValues(rent, values);
    }

    private void patchValues(WorkerRent rent, Map<String, String> values) {
        if (values.get("rentStatus") != null) {
            processRentStatusChange(rent, values);
        }
    }

    private void processRentStatusChange(WorkerRent rent, Map<String, String> values) {
        WorkerRentStatus oldStatus = rent.getRentStatus();
        WorkerRentStatus newStatus = WorkerRentStatus.get(values.get("rentStatus")).orElseThrow();

        if (oldStatus == newStatus) {
            return;
        }
        rent.setRentStatus(newStatus);
        workerRentLogRepository.save(createStatusChangeLog(rent, oldStatus, newStatus));
    }
}
