package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
@EnableScheduling
public class MailerController {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    AppUserService appUserService;

    public void sendEmail(AppUser appUser) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(0);
        String day = formatter.format(dayPlusAWeek);
//     cách gửi mail cho nhiều người
//        List<String> emails = appUserService.getAllEmails();
//        Set<String> listEmail = new HashSet<>();
//        listEmail.addAll(emails);
//
//        if (!(listEmail.size() == 0)) {
//            String[] array = listEmail.toArray(new String[0]);
//            SimpleMailMessage message = new SimpleMailMessage();
//
//            message.setTo(array);
//            message.setSubject("Thông báo đăng ký thành công");
//            message.setText("Chào bạn \n"
//                    + "OGANI xin thông báo, vào ngày " + day
//                    + " bạn đã chính thức trở thành một thành viên tham gia sàn thương mại điện tử OGANI"
//                    + " cùng chung tay xây dựng OGANI ngày càng phát triển nhé! \n"
//                    + "Mọi thắc mắc xin liên hệ: \n"
//                    + "Hotline: 0896 110 467 \n"
//                    + "Email: vvviet97@gmail.com");
//
//            //send message
//            this.emailSender.send(message);
//        } else {
//            System.out.println(day + "Have not email to send!");
//        }

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(appUser.getUsername());
        message.setSubject("Thông báo đăng ký thành công");
        message.setText("Chào bạn \n"
                + "OGANI xin thông báo, vào ngày " + day
                + " bạn đã chính thức trở thành một thành viên tham gia sàn thương mại điện tử OGANI"
                + " cùng chung tay xây dựng OGANI ngày càng phát triển nhé! \n"
                + "Mọi thắc mắc xin liên hệ: \n"
                + "Hotline: 0896 110 467 \n"
                + "Email: vvviet97@gmail.com");

        //send message
        this.emailSender.send(message);

    }

}
