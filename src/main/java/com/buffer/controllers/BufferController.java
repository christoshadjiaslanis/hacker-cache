package com.buffer.controllers;

import com.buffer.crawler.BufferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BufferController {
    private final BufferService bufferService;

    @Autowired
    public BufferController(BufferService bufferService) {
        this.bufferService = bufferService;
    }

    @RequestMapping(value = "/{topic}")
    public String index(@PathVariable String topic){
        return bufferService.getContent(topic, 50);
    }

    @RequestMapping(value = "{topic}/pages")
    public String pages(@PathVariable String topic, @RequestParam("n") int n){
        return bufferService.getContent(topic, n);
    }

}
