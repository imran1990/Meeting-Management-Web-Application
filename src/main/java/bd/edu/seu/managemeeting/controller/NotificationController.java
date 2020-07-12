package bd.edu.seu.managemeeting.controller;

import bd.edu.seu.managemeeting.exception.ResourceNotFoundException;
import bd.edu.seu.managemeeting.exception.ResourceAlreadyExistsException;
import bd.edu.seu.managemeeting.model.Notification;
import bd.edu.seu.managemeeting.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", exposedHeaders = {"httpStatus", "messageType", "messageTitle", "messageDescription", "servedAt"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("")
    public ResponseEntity<List<Notification>> getNotification(){
        logger.trace("getNotification method called.");
        List<Notification> notificationList = notificationService.findAll();
        return ResponseEntity.ok(notificationList);
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> getNotification(@PathVariable String notificationId){
        logger.trace("getNotification By notifIcationId method called.");
        try{
            Notification notification = notificationService.findById(notificationId);
            return ResponseEntity.ok(notification);}
        catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception f){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public  ResponseEntity<Notification> insertNotification(@RequestBody Notification notification){
        logger.trace("insertNotification method called.");
        try{
            Notification insertNotification = notificationService.insertNotification(notification);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertNotification);}
        catch (ResourceAlreadyExistsException e) {return ResponseEntity.badRequest().body(null);}
    }

    @PutMapping("/{notificationId}")
    public  ResponseEntity<Notification> updateNotification(@PathVariable String notificationId, @RequestBody Notification notification){
        logger.trace("updateNotification method called.");
        try{
            Notification updateNotification = notificationService.updateNotification(notificationId, notification);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateNotification);}
        catch (ResourceNotFoundException e) {return ResponseEntity.badRequest().body(null);}

    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable String notificationId){
        logger.trace("Delete Notification method called.");
        try{
            boolean deletedNotification = notificationService.deleteById(notificationId);
            return ResponseEntity.ok(notificationId);}
        catch (ResourceNotFoundException e){return ResponseEntity.notFound().build();}
    }

}
