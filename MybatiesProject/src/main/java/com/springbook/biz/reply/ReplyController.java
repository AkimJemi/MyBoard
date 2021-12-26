package com.springbook.biz.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReplyController {
	@Autowired
	ReplyService replyDAO;

}
