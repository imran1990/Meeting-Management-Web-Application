package bd.edu.seu.managemeeting.controller;

import bd.edu.seu.managemeeting.exception.ResourceNotFoundException;
import bd.edu.seu.managemeeting.exception.ResourceAlreadyExistsException;
import bd.edu.seu.managemeeting.model.Venue;
import bd.edu.seu.managemeeting.service.VenueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = {"httpStatus", "messageType", "messageTitle", "messageDescription", "servedAt"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {
    Logger logger = LoggerFactory.getLogger(VenueController.class);
    private VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("")
    public ResponseEntity<List<Venue>> getVenue(){
        logger.trace("getVenue method is called");
        List<Venue> VenueList = venueService.findAll();
        return ResponseEntity.ok(VenueList);
    }

    @GetMapping("/{venueId}")
    public ResponseEntity<Venue> getVenue(@PathVariable String venueId){
        logger.trace("getVenueById method is called");
        try{
            Venue venue = venueService.findById(venueId);
            return ResponseEntity.ok(venue);}
        catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception f){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public  ResponseEntity<Venue> insertVenue(@RequestBody Venue venue){
        logger.trace("insertVenue method is called");
        try{
            Venue insertVenue = venueService.insertVenue(venue);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertVenue);}
        catch (ResourceAlreadyExistsException e) {return ResponseEntity.badRequest().body(null);}
    }

    @PutMapping("/{venueId}")
    public  ResponseEntity<Venue> updateVenue(@PathVariable String venueId, @RequestBody Venue venue){
        logger.trace("updateVenue method is called");
        try{
            Venue updateVenue = venueService.updateVenue(venueId, venue);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateVenue);}
        catch (ResourceNotFoundException e) {return ResponseEntity.badRequest().body(null);}

    }

    @DeleteMapping("/{venueId}")
    public ResponseEntity<String> deleteVenue(@PathVariable String venueId){
        logger.trace("deleteVenue method is called");
        try{
            boolean deletedVenue = venueService.deleteById(venueId);
            return ResponseEntity.ok(venueId);}
        catch (ResourceNotFoundException e){return ResponseEntity.notFound().build();}
    }

}
