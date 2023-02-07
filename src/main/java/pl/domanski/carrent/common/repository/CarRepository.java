package pl.domanski.carrent.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.domanski.carrent.car.model.BodyType;
import pl.domanski.carrent.common.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findBySlug(String slug);
    List<Car> findAllByCategoryId(Long categoryId);

    @Query("select distinct c.brand from Car c")
    List<String> findAllBrandNames();
    Long countByBrand(String brandName);

    @Query("select distinct c.year from Car c")
    List<Integer> findAllCarsYears();
    Long countByYear(Integer year);

    @Query("select distinct c.bodyType from Car c")
    List<BodyType> findAllCarsBodyTypes();
    Long countByBodyType(BodyType bodyType);


}
