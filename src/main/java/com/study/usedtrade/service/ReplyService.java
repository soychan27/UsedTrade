package com.study.usedtrade.service;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.Reply;
import com.study.usedtrade.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    public void save(Reply reply) {
        replyRepository.save(reply);
    }

    public void delete(Integer id) {
        replyRepository.deleteById(id);
    }

    public Reply findById(Integer id) {
        return replyRepository.findById(id).orElse(null);
    }

    public List<Reply> findAllByItem(Item item) {
        return replyRepository.findAllByItem(item);
    }
}
