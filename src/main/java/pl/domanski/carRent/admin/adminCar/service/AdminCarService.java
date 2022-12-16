package pl.domanski.carRent.admin.adminCar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;

@Service
@RequiredArgsConstructor
public class AdminCarService {

    private final AdminCarRepository adminCarRepository;

    public Page<AdminCar> getCars(Pageable pageable) {
        return adminCarRepository.findAll(pageable);
    }

    public AdminCar getCar(Long id) {
        return adminCarRepository.findById(id).orElseThrow();
    }


    public AdminCar createCar(AdminCar adminCar) {
        return adminCarRepository.save(adminCar);
    }

    public AdminCar updateCar(AdminCar adminCar) {
        return adminCarRepository.save(adminCar);
    }

    public void deleteCar(Long id) {
        adminCarRepository.deleteById(id);
    }
}
