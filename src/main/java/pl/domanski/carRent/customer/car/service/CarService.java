package pl.domanski.carRent.customer.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.customer.car.model.BodyType;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.dto.CarBasicInfo;
import pl.domanski.carRent.customer.common.mapper.CarMapper;
import pl.domanski.carRent.customer.common.repository.CarRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car getCar(String slug) {
        return carRepository.findBySlug(slug).orElseThrow();
    }

    public List<CarBasicInfo> searchCarsByFilters(Map<String, String> params) {
        Specification<Car> spec = createSpecification(params);
        return carRepository.findAll(spec).stream()
                .map(CarMapper::mapToCarBasicInfo).toList();
    }

    private Specification<Car> createSpecification(Map<String, String> params) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            String brand = params.get("brand");
            if (brand != null && !brand.isEmpty()) {
                predicates.add(builder.equal(root.get("brand"), brand));
            }
            String model = params.get("model");
            if (model != null && !model.isEmpty()) {
                predicates.add(builder.equal(root.get("model"), model));
            }
            String year = params.get("year");
            if (year != null && !year.isEmpty()) {
                predicates.add(builder.equal(root.get("year"), year));
            }
            String bodyTypeString = params.get("bodyType");
            if (bodyTypeString != null && !bodyTypeString.isEmpty()) {
                BodyType bodyType = BodyType.get(bodyTypeString).orElseThrow();
                predicates.add(builder.equal(root.get("bodyType"), bodyType));
            }
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
