package com.mhr.user.serviece;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mhr.user.model.User;
import com.mhr.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public User findById(Long userId) {
		Optional<User> result = userRepository.findById(userId);
		
		User users = null ;
		
		if(result.isPresent()) {
			users = result.get();
		}else{
			throw new RuntimeException("Did not found diary id : " + userId );
		}
		 
		return users;
	}
	

	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(user);
	}
	
	@Override
	public List<User> findAllUsers(){
		return this.userRepository.findAll();
	}
	
	@Override
	public void deleteById(Long userId) {
		this.userRepository.deleteById(userId);
	}
	
	@Override
	public void deleteAllUser() {
		this.userRepository.deleteAll();
	}


	@Override
	public UserDetails loadUserByUsername(String emaile) throws UsernameNotFoundException {
		return this.userRepository.findByEmaile(emaile) ;
	}

}
