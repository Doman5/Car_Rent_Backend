package pl.domanski.carRent.admin.adminCar.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_technical_specification")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AdminCarTechnicalSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer power;
    private String engine;
    private String drive;
    private String acceleration;
    private String gearbox;
    private String fuel;
    private String seats;
}
