package com.xinlang.zly_xyx.cat_manage_backend.service;


import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.mail.Mail;

import java.util.Map;

public interface MailService {

    void saveMail(Mail mail);

    void updateMail(Mail mail);

    void sendMail(Mail mail);

    Mail findById(Long id);

    Page<Mail> findMails(Map<String, Object> params);
}
