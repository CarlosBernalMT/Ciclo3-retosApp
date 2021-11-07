package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Car;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.getAll();
    }

    public Optional<Car> getCar(int id){
        return carRepository.getCar(id);
    }

    public Car save(Car car){
        if (car.getIdCar() == null){
            return carRepository.save(car);
        }else{
            Optional<Car> caux = carRepository.getCar(car.getIdCar());
            if (caux.isEmpty()){
                return carRepository.save(car);
            }else{
                return car;
            }
        }
    }

    public Car update(Car car){
        if (car.getIdCar() != null){
            Optional<Car> e = carRepository.getCar(car.getIdCar());
            if (!e.isEmpty()){
                if (car.getName() != null){
                    e.get().setName(car.getName());
                }
                if (car.getBrand() != null){
                    e.get().setBrand(car.getBrand());
                }
                if (car.getYear() != null){
                    e.get().setYear(car.getYear());
                }
                if (car.getDescription() != null){
                    e.get().setDescription(car.getDescription());
                }
                if (car.getGama() != null){
                    e.get().setGama(car.getGama());
                }
                carRepository.save(e.get());
                return e.get();
            } else {
                return car;
            }
        } else {
            return car;
        }
    }

    public boolean deleteCar(int id){
        Boolean aBoolean =getCar(id).map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
