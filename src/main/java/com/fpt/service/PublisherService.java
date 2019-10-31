package com.fpt.service;

import com.fpt.entity.Publisher;
import com.fpt.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PublisherService {
    @Autowired
    PublisherRepository publisherRepository;

    public List<Publisher> publishers() {
        publisherRepository.findAll(PageRequest.of(1,3));
        return publisherRepository.findActivePublisher(1);
    }

    public Page<Publisher> publisherPage(int page, int limit) {
        return publisherRepository.findAll(PageRequest.of(page - 1,limit));
    }

    public Publisher getById(long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public Publisher create(Publisher publisher) {
        publisher.setId(Calendar.getInstance().getTimeInMillis());
        publisher.setStatus(1);
        publisher.setCreateAt(Calendar.getInstance().getTimeInMillis());
        publisher.setUpdateAt(Calendar.getInstance().getTimeInMillis());
        return publisherRepository.save(publisher);
    }

    public Publisher update(Publisher publisher) {
        publisher.setUpdateAt(Calendar.getInstance().getTimeInMillis());
        return publisherRepository.save(publisher);
    }

    public boolean delete(Publisher publisher) {
        publisher.setDeleteAt(Calendar.getInstance().getTimeInMillis());
        publisher.setStatus(-1);
        publisherRepository.save(publisher);
        return true;
    }
}
