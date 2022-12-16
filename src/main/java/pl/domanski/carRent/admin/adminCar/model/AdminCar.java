package pl.domanski.carRent.admin.adminCar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "car")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private int year;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_technical_specification_id")
    private AdminCarTechnicalSpecification adminCarTechnicalSpecification;
}
