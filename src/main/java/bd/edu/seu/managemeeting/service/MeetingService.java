package bd.edu.seu.managemeeting.service;

import bd.edu.seu.managemeeting.exception.ResourceNotFoundException;
import bd.edu.seu.managemeeting.repository.MeetingsRepository;
import bd.edu.seu.managemeeting.exception.ResourceAlreadyExistsException;
import bd.edu.seu.managemeeting.model.Meetings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class MeetingService {


    private MeetingsRepository meetingRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public MeetingService(MeetingsRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meetings insertMeeting(Meetings meeting) throws ResourceAlreadyExistsException {
        Optional<Meetings> optionalMeeting = meetingRepository.findById(meeting.getMeetingId());
        if(optionalMeeting.isPresent()){
            logger.warn("Meeting ID {} already exists", meeting.getMeetingId());
            throw new ResourceAlreadyExistsException(meeting.getMeetingId() + "");
        }
        else{
            return meetingRepository.save(meeting);
        }
    }

    public Meetings updateMeeting (String meetingId, Meetings meeting) throws ResourceNotFoundException {
        Optional<Meetings> optionalMeeting = meetingRepository.findById(meetingId);
        if(optionalMeeting.isPresent()){
            meeting.setMeetingId(meetingId);
            return meetingRepository.save(meeting);
        }
        else {logger.warn("Meeting ID {} does not exist", meeting.getMeetingId());
            throw new ResourceNotFoundException(meetingId + "");
        }

    }

    public Meetings findById(String meetingId) throws ResourceNotFoundException {
        Optional<Meetings> optionalMeeting = meetingRepository.findById(meetingId);
        if(optionalMeeting.isPresent()){
            return optionalMeeting.get();
        }else {
            logger.warn("Meeting ID {} does not exist", meetingId);
            throw new ResourceNotFoundException(meetingId + "");
        }
    }

    public List<Meetings>findAll(){
        List<Meetings>meetingList = new ArrayList<>();
        meetingRepository.findAll().forEach(meetingList::add);
        return meetingList;
    }

    public  boolean deleteById(String meetingId) throws ResourceNotFoundException{
        Optional<Meetings> optionalMeeting = meetingRepository.findById(meetingId);
        optionalMeeting.ifPresent(meeting -> meetingRepository.deleteById(meetingId));
        logger.info("Meeting ID {} deleted", meetingId);
        optionalMeeting.orElseThrow(() -> new ResourceNotFoundException(meetingId + ""));
        return true;
    }



}
