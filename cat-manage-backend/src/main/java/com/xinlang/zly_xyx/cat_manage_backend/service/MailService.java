package com.xinlang.zly_xyx.cat_manage_backend.service;


import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.email.Email;

import java.util.Map;

public interface MailService {

    void saveMail(Email mail);

    void updateMail(Email mail);

    void sendMail(Email mail);

    Email findById(Long id);

    Page<Email> findMails(Map<String, Object> params);
}
