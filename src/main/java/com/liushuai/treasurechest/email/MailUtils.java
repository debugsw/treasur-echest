package com.liushuai.treasurechest.email;

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
public class MailUtils {
    private String host = "debugsw@126.com";
    private int port = 25;
    private String userName = "";
    private String password = "";
    private String receive = "shuai012192@126.com";

    /**
     * 发送文本邮件
     */
    public void sendTextMail() {
        try {
            SimpleEmail mail = new SimpleEmail();
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
            // 设置邮件内容
            mail.setMsg("this is a test Text mail");
            // 设置邮件发送时间
            mail.setSentDate(new Date());
            // 发送邮件
            mail.send();
        } catch (Exception e) {
            e.printStackTrace();
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


}
