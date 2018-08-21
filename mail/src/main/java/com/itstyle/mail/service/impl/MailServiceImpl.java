package com.itstyle.mail.service.impl;

import com.itstyle.mail.common.model.Email;
import com.itstyle.mail.common.model.Result;
import com.itstyle.mail.entity.OaEmail;
import com.itstyle.mail.repository.MailRepository;
import com.itstyle.mail.service.IMailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/8/21 14:12
 * @Description:
 */
@Service
public class MailServiceImpl implements IMailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    public Configuration configuration;//freemarker

    @Value("${spring.mail.username}")
    public String USER_NAME;//发送者

    @Value("${server.path}")
    public String PATH;//发送者

    @Override
    public void send(Email mail) throws Exception {
        logger.info("发送邮件：{}",mail.getContent());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USER_NAME);
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailSender.send(message);
    }

    @Override
    public void sendHtml(Email mail) throws Exception {

    }

    @Override
    public void sendFreemarker(Email mail) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(USER_NAME);
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        Map<String, Object> model = new HashMap<>();
        model.put("mail",mail);
        model.put("path",PATH);
        Template template = configuration.getTemplate(mail.getTemplate()+".flv");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
        helper.setText(text,true);
        mailSender.send(message);
        mail.setContent(text);
        OaEmail oaEmail = new OaEmail(mail);
        mailRepository.save(oaEmail);
    }

    @Override
    public void sendThymeleaf(Email mail) throws Exception {

    }

    @Override
    public void sendQueue(Email mail) throws Exception {

    }

    @Override
    public void sendRedisQueue(Email mail) throws Exception {

    }

    @Override
    public Result listMail(Email mail) {
        List<OaEmail> list = mailRepository.findAll();
        return Result.ok(list);
    }
}
