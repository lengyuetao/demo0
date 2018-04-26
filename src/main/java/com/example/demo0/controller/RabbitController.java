package com.example.demo0.controller;

import java.util.Date;

import javax.annotation.Resource;

import com.example.demo0.rabbit.RabbitProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rabbit")
public class RabbitController {
	
	@Resource
	RabbitProducer rabbitProducer;
	
	@RequestMapping(value="/send",method=RequestMethod.GET)
	public String send(){
		String context="tianniu-msg"+new Date();
		rabbitProducer.send("tianniu-msg", context);
		return "success";
	}
}
