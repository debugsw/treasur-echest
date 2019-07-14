package com.liushuai.treasurechest.email;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import java.io.File;
import java.util.Date;

/**
 * @Description 邮件工具类
 * @Author ls
 * @Date 2019/7/13 22:11
 */
@Slf4j
public class MailUtils {
    private String host = "smtp.126.com";
    private int port = 25;
    /**
     * 发送邮箱
     */
    private String userName = "shuai012192@126.com";
    /**
     * 发送邮箱密码
     */
    private String password = " ";
    /**
     * 接收邮箱
     */
    private String receive = "debugsw@126.com";

    /**
     * 发送文本邮件
     *
     * @param sendUser
     * @param sendPassword
     * @param receiveUser
     * @param theme
     * @param msg
     */
    public void sendTextMail(String sendUser, String sendPassword, String receiveUser, String theme, String msg) {
        try {
            SimpleEmail mail = new SimpleEmail();
            // 设置邮箱服务器信息
            mail.setSmtpPort(port);
            mail.setHostName(host);
            // 设置密码验证器
            mail.setAuthentication(sendUser, sendPassword);
            // 设置邮件发送者
            mail.setFrom(sendUser);
            // 设置邮件接收者
            mail.addTo(receiveUser);
            // 设置邮件编码
            mail.setCharset("UTF-8");
            // 设置邮件主题
            mail.setSubject(theme);
            // 设置邮件内容
            mail.setMsg(msg);
            // 设置邮件发送时间
            mail.setSentDate(new Date());
            // 发送邮件
            mail.send();
            log.debug("邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("邮件发送失败,请联系管理员", e);
        }
    }

    /**
     * 发送内嵌图片邮件
     */
    public void sendImageMail() {
        try {
            HtmlEmail mail = new HtmlEmail();
            // 设置邮箱服务器信息
            mail.setSmtpPort(port);
            mail.setHostName(host);
            // 设置密码验证器
            mail.setAuthentication(userName, password);
            // 设置邮件发送者
            mail.setFrom(userName);
            // 设置邮件接收者
            mail.addTo(receive);
            // 设置邮件编码
            mail.setCharset("UTF-8");
            // 设置邮件主题
            mail.setSubject("Test Email");
            mail.embed(new File("1_jianggujin.jpg"), "image");
            // 设置邮件内容
            String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML email.</div></body></html>";
            mail.setHtmlMsg(htmlText);
            // 设置邮件发送时间
            mail.setSentDate(new Date());
            // 发送邮件
            mail.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送附件邮件
     *
     * @throws Exception
     */
    public void sendAttachmentMail() {
        try {
            MultiPartEmail mail = new MultiPartEmail();
            // 设置邮箱服务器信息
            mail.setSmtpPort(port);
            mail.setHostName(host);
            // 设置密码验证器
            mail.setAuthentication(userName, password);
            // 设置邮件发送者
            mail.setFrom(userName);
            // 设置邮件接收者
            mail.addTo(receive);
            // 设置邮件编码
            mail.setCharset("UTF-8");
            // 设置邮件主题
            mail.setSubject("Test Email");

            mail.setMsg("this is a Attachment email.this email has a attachment!");
            // 创建附件
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath("1_jianggujin.jpg");
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setName("1_jianggujin.jpg");
            mail.attach(attachment);

            // 设置邮件发送时间
            mail.setSentDate(new Date());
            // 发送邮件
            mail.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送内嵌图片和附件邮件
     *
     * @throws Exception
     */
    public void sendImageAndAttachmentMail() {
        try {
            HtmlEmail mail = new HtmlEmail();
            // 设置邮箱服务器信息
            mail.setSmtpPort(port);
            mail.setHostName(host);
            // 设置密码验证器
            mail.setAuthentication(userName, password);
            // 设置邮件发送者
            mail.setFrom(userName);
            // 设置邮件接收者
            mail.addTo(receive);
            // 设置邮件编码
            mail.setCharset("UTF-8");
            // 设置邮件主题
            mail.setSubject("Test Email");
            mail.embed(new File("1_jianggujin.jpg"), "image");
            // 设置邮件内容
            String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML email.</div></body></html>";
            mail.setHtmlMsg(htmlText);
            // 创建附件
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath("1_jianggujin.jpg");
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setName("1_jianggujin.jpg");
            mail.attach(attachment);
            // 设置邮件发送时间
            mail.setSentDate(new Date());
            // 发送邮件
            mail.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MailUtils mailUtils = new MailUtils();
        mailUtils.sendTextMail("", "", "debugsw@126.com", "预警邮件", "已经超过平预警线,请及时查看.");

    }


}
