package bd.edu.seu.managemeeting.service;

import bd.edu.seu.managemeeting.exception.ResourceNotFoundException;
import bd.edu.seu.managemeeting.repository.MeetingsRepository;
import bd.edu.seu.managemeeting.repository.VenueRepository;
import bd.edu.seu.managemeeting.exception.ResourceAlreadyExistsException;
import bd.edu.seu.managemeeting.model.Venue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public  class VenueService{


    private VenueRepository venueRepository;
    //private MeetingsRepository meetingRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public VenueService(VenueRepository venueRepository /*, MeetingsRepository meetingRepository*/) {
        this.venueRepository = venueRepository;
        //this.meetingRepository = meetingRepository;
    }

    public Venue insertVenue(Venue venue) throws ResourceAlreadyExistsException {
        Optional<Venue> optionalVenue = venueRepository.findById(venue.getVenueId());
        if(optionalVenue.isPresent()){
            logger.warn("Venue ID {} already exists", venue.getVenueId());
            throw new ResourceAlreadyExistsException(venue.getVenueId() + "");
        }
        else{
            return venueRepository.save(venue);
            }
    }

    public Venue updateVenue (String venueId, Venue venue) throws ResourceNotFoundException {
        Optional<Venue> optionalVenue = venueRepository.findById(venueId);
        if(optionalVenue.isPresent()){
            venue.setVenueId(venueId);
            return venueRepository.save(venue);
        }
        else {logger.warn("Venue ID {} doesn't exist", venue.getVenueId());
            throw new ResourceNotFoundException(venueId + "");}
    }

    public Venue findById(String venueId) throws ResourceNotFoundException {
        Optional<Venue> optionalVenue = venueRepository.findById(venueId);
        if(optionalVenue.isPresent()){
            return optionalVenue.get();
        }else {
            logger.warn("Venue ID {} doesn't exist", venueId);
            throw new ResourceNotFoundException(venueId + "");
        }
        }

    public List<Venue>findAll(){
        List<Venue>venueList = new ArrayList<>();
        venueRepository.findAll().forEach(venueList::add);
        return venueList;
    }

    public  boolean deleteById(String venueId) throws ResourceNotFoundException{
        Optional<Venue> optionalVenue = venueRepository.findById(venueId);
        optionalVenue.ifPresent(venue -> venueRepository.deleteById(venueId));
        logger.info("Venue id {} is deleted", venueId);
        optionalVenue.orElseThrow(() -> new ResourceNotFoundException(venueId + ""));
        return true;
    }




}
