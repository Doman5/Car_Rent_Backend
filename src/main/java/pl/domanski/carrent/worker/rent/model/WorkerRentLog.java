package pl.domanski.carrent.worker.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "rent_log")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerRentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rentId;
    private LocalDateTime created;
    private String note;
}
