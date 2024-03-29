package pl.domanski.carrent.admin.car.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Table(name = "car")
@Entity
@Getter
@Setter
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
    @Enumerated(EnumType.STRING)
    private AdminBodyType bodyType;
    private String slug;
    @OneToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "car_technical_specification_id")
    private AdminCarTechnicalSpecification adminCarTechnicalSpecification;
    @OneToMany(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "carId")
    private List<AdminCarEquipment> equipments;
    @OneToMany(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "carId")
    private List<AdminCarDescription> descriptions;
    @OneToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "car_price_id")
    private AdminCarPrice carPrice;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "carId")
    private List<AdminCarPhoto> photos;
    private Long categoryId;
}
