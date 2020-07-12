package bd.edu.seu.managemeeting.controller;

import bd.edu.seu.managemeeting.exception.ResourceAlreadyExistsException;
import bd.edu.seu.managemeeting.exception.ResourceNotFoundException;
import bd.edu.seu.managemeeting.model.Objection;
import bd.edu.seu.managemeeting.service.ObjectionService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = {"httpStatus", "messageType", "messageTitle", "messageDescription", "servedAt"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/objections")
public class ObjectionController {
    Logger logger = LoggerFactory.getLogger(ObjectionController.class);
    private ObjectionService objectionService;

    public ObjectionController(ObjectionService objectionService) {
        this.objectionService = objectionService;
    }

    @GetMapping("")
    public ResponseEntity<List<Objection>> getObjection(){
        logger.trace("getObjection method is called");
        List<Objection> ObjectionList = objectionService.findAll();
        return ResponseEntity.ok(ObjectionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Objection> getObjection(@PathVariable ObjectId id){
        logger.trace("getObjectionById method is called");
        try{
            Objection objection = objectionService.findById(id);
            return ResponseEntity.ok(objection);}
        catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception f){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public  ResponseEntity<Objection> insertObjection(@RequestBody Objection objection){
        logger.trace("insertObjection method is called");
        try{
            Objection insertedObjection = objectionService.insertObjection(objection);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedObjection);}
        catch (ResourceAlreadyExistsException e) {return ResponseEntity.badRequest().body(null);}
    }

    /*@PutMapping("/{objectionId}")
    public  ResponseEntity<Objection> updateObjection(@PathVariable ObjectId objectionId, @RequestBody Objection objection){
        logger.trace("updateObjection method is called");
        try{
            Objection updateObjection = objectionService.updateObjection(objectionId, objection);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateObjection);}
        catch (ResourceNotFoundException e) {return ResponseEntity.badRequest().body(null);}

    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectId> deleteObjection(@PathVariable ObjectId id){
        logger.trace("deleteObjection method is called");
        try{
            boolean deletedObjection = objectionService.deleteById(id);
            return ResponseEntity.ok(id);}
        catch (ResourceNotFoundException e){return ResponseEntity.notFound().build();}
    }

}
