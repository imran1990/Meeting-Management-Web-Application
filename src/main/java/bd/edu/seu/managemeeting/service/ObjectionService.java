package bd.edu.seu.managemeeting.service;

import bd.edu.seu.managemeeting.exception.ResourceAlreadyExistsException;
import bd.edu.seu.managemeeting.exception.ResourceNotFoundException;
import bd.edu.seu.managemeeting.model.Objection;
import bd.edu.seu.managemeeting.repository.ObjectionRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ObjectionService {


    private ObjectionRepository objectionRepository;
    //private MeetingsRepository meetingRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ObjectionService(ObjectionRepository objectionRepository /*, MeetingsRepository meetingRepository*/) {
        this.objectionRepository = objectionRepository;
        //this.meetingRepository = meetingRepository;
    }

    public Objection insertObjection(Objection objection) throws ResourceAlreadyExistsException {
       /* Optional<Objection> optionalObjection = objectionRepository.findById(objection.getObjectionId());
        if (optionalObjection.isPresent()) {
            logger.warn("Objection ID {} already exists", objection.getObjectionId());
            throw new ResourceAlreadyExistsException(objection.getObjectionId() + "");
        } else {
            return objectionRepository.save(objection);
        }*/
        return objectionRepository.save(objection);
    }

    /*public Objection updateObjection(ObjectId objectionId, Objection objection) throws ResourceNotFoundException {
        Optional<Objection> optionalObjection = objectionRepository.findById(objectionId);
        if (optionalObjection.isPresent()) {
            objection.setObjectionId(objectionId);
            return objectionRepository.save(objection);
        } else {
            logger.warn("Objection ID {} doesn't exist", objection.getObjectionId());
            throw new ResourceNotFoundException(objectionId + "");
        }
    }*/

    public Objection findById(ObjectId id) throws ResourceNotFoundException {
        Optional<Objection> optionalObjection = objectionRepository.findById(id);
        if (optionalObjection.isPresent()) {
            return optionalObjection.get();
        } else {
            logger.warn("Objection ID {} doesn't exist", id);
            throw new ResourceNotFoundException(id + "");
        }
    }

    public List<Objection> findAll() {
        List<Objection>objectionList = new ArrayList<>();
        objectionRepository.findAll().forEach(objectionList::add);
        return objectionList;
    }

    public boolean deleteById(ObjectId id) throws ResourceNotFoundException {
        Optional<Objection> optionalObjection = objectionRepository.findById(id);
        optionalObjection.ifPresent(objection -> objectionRepository.deleteById(id));
        logger.info("Objection id {} is deleted", id);
        optionalObjection.orElseThrow(() -> new ResourceNotFoundException(id + ""));
        return true;
    }


}
