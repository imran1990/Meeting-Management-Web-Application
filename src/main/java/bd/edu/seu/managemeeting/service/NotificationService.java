package bd.edu.seu.managemeeting.service;

import bd.edu.seu.managemeeting.exception.ResourceNotFoundException;
import bd.edu.seu.managemeeting.repository.NotificationRepository;
import bd.edu.seu.managemeeting.exception.ResourceAlreadyExistsException;
import bd.edu.seu.managemeeting.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public  class NotificationService {

    private NotificationRepository notificationRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification insertNotification(Notification notification) throws ResourceAlreadyExistsException {
        Optional<Notification> optionalNotification = notificationRepository.findById(notification.getNotificationId());
        if(optionalNotification.isPresent()){
            logger.warn("Notification ID {} already exists", notification.getNotificationId());
            throw new ResourceAlreadyExistsException(notification.getNotificationId() + "");
        }
        else{
            return notificationRepository.save(notification);
        }
    }

    public Notification updateNotification (String notificationId, Notification notification) throws ResourceNotFoundException {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if(optionalNotification.isPresent()){
            notification.setNotificationId(notificationId);
            return notificationRepository.save(notification);
        }
        else throw new ResourceNotFoundException(notificationId + "");
    }

    public Notification findById(String notificationId) throws ResourceNotFoundException {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if(optionalNotification.isPresent()){
            return optionalNotification.get();
        }else throw new ResourceNotFoundException(notificationId + "");
    }

    public List<Notification>findAll(){
        List<Notification>notificationList = new ArrayList<>();
        notificationRepository.findAll().forEach(notificationList::add);
        return notificationList;
    }

    public  boolean deleteById(String notificationId) throws ResourceNotFoundException{
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        optionalNotification.ifPresent(notification -> notificationRepository.deleteById(notificationId));
        optionalNotification.orElseThrow(() -> new ResourceNotFoundException(notificationId + ""));
        return true;
    }



}
