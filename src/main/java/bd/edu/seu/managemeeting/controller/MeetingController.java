package bd.edu.seu.managemeeting.controller;

import bd.edu.seu.managemeeting.exception.ResourceNotFoundException;
import bd.edu.seu.managemeeting.service.MeetingService;
import bd.edu.seu.managemeeting.exception.ResourceAlreadyExistsException;
import bd.edu.seu.managemeeting.model.Meetings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = {"httpStatus", "messageType", "messageTitle", "messageDescription", "servedAt"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/meetings")
public class MeetingController {

    Logger logger = LoggerFactory.getLogger(MeetingController.class);
    private MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("")
    public ResponseEntity<List<Meetings>> getMeeting(){
        logger.trace("getmeeting method called.");
        List<Meetings> meetingList = meetingService.findAll();
        return ResponseEntity.ok(meetingList);
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<Meetings> getMeeting(@PathVariable String meetingId){
        logger.trace("getmeetingById method called");
        try{
            Meetings meeting = meetingService.findById(meetingId);
            return ResponseEntity.ok(meeting);}
        catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception f){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public  ResponseEntity<Meetings> insertMeeting(@RequestBody Meetings meeting){
        logger.trace("InsertMeeting method called");
        try{
            Meetings insertedMeeting = meetingService.insertMeeting(meeting);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedMeeting);}
        catch (ResourceAlreadyExistsException e) {return ResponseEntity.badRequest().body(null);}

    }

    @PutMapping("/{meetingId}")
    public  ResponseEntity<Meetings> updateMeeting(@PathVariable String meetingId, @RequestBody Meetings meeting){
        logger.trace("UpdateMeeting method called");
        try{
            Meetings updateMeeting = meetingService.updateMeeting(meetingId, meeting);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateMeeting);}
        catch (ResourceNotFoundException e) {return ResponseEntity.badRequest().body(null);}

    }

    @DeleteMapping("/{meetingId}")
    public ResponseEntity<String> deleteMeeting(@PathVariable String meetingId){
        logger.trace("Delete Meeting method called");
        try{
            boolean deletedMeeting = meetingService.deleteById(meetingId);
            return ResponseEntity.ok(meetingId);}
        catch (ResourceNotFoundException e){return ResponseEntity.notFound().build();}
    }

}
