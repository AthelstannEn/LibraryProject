package ua.com.serviceImpl;

import java.util.List;


import java.util.Properties;

import javax.annotation.PostConstruct;



import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.CRUD.dao.UserDao;
import ua.com.entity.Role;
import ua.com.entity.User;
import ua.com.service.User_Service;


@Service("userDetailService")
public class UserServiceImpl implements User_Service, UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		userDao.save(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findOne(int id) {
		return userDao.findOne(id);
	}

	public void delete(int id) {

		userDao.delete(id);
	}

	

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User findByMobilePhone(String mobilePhone) {
		return userDao.findByMobilePhone(mobilePhone);
	}

	public User findByPostCode(int postCode) {
		return userDao.findByPostCode(postCode);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userDao.findByEmail(email);
	}
	
	@PostConstruct
	public void addAdmin(){
		User user = userDao.findByEmail("admin");
		if(user==null){
			user = new User();
			user.setPassword(encoder.encode("admin"));
			user.setEmail("admin");
			user.setRole(Role.ROLE_ADMIN);
			user.setPostCode("000");
			user.setMobilePhone("000");
			user.setFullName("000");
			user.setDeliveryAddressCountry("000");
			user.setDeliveryAddressCity("000");
			user.setDeliveryHomeAddress("000");
			userDao.save(user);
		}
	}

	@Override
	public void sendMail(String content, String email, String mailBody) {

		Properties properties = System.getProperties();
		
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.setProperty("mail.smtp.socketFactory.class",
		"javax.net.ssl.SSLSocketFactory");
		Session session = Session.getDefaultInstance(properties,
		new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("appleshopteam@gmail.com", "money00");
		}
		});
		try {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("appleshopteam@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
		email));
		message.setSubject(content, "UTF-8");
		message.setText(mailBody);
		Transport.send(message);
		} catch (MessagingException å) {
		å.printStackTrace();
		}
		
	}

	
	
}
