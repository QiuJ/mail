package com.itstyle.mail.repository;

import com.itstyle.mail.entity.OaEmail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator
 * @Date: 2018/8/21 14:43
 * @Description:
 */
public interface MailRepository extends JpaRepository<OaEmail, Integer> {
}
