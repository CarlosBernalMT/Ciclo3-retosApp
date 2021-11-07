package co.usa.ciclo3.ciclo3.web;

import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.reports.CounterClients;
import co.usa.ciclo3.ciclo3.reports.StatusReservation;
import co.usa.ciclo3.ciclo3.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getReservations() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int idReservation) {
        return reservationService.getReservation(idReservation);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int idReservation) {
        return reservationService.deleteReservation(idReservation);
    }

    @GetMapping("/report-status")
    public StatusReservation getReservationStatusReport() {
        return reservationService.getReservationStatusReport();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo) {
        return reservationService.getReservationPeriod(dateOne, dateTwo);
    }

    @GetMapping("/report-clients")
    public List<CounterClients> getClients() {
        return reservationService.getTopClients();
    }
}
