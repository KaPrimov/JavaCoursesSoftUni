package org.softuni.nuggets.services;

import org.modelmapper.ModelMapper;
import org.softuni.nuggets.entities.User;
import org.softuni.nuggets.models.binding.RegisterBindingModel;
import org.softuni.nuggets.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final BCryptPasswordEncoder encoder;

    private final ModelMapper mapper;

    private final org.softuni.nuggets.repositories.UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder encoder, ModelMapper mapper, org.softuni.nuggets.repositories.UserRepository userRepository, RoleRepository roleRepository, JmsTemplate jmsTemplate) {
        this.encoder = encoder;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = this.userRepository.findByUsername(username);

        if(result == null) throw new UsernameNotFoundException("Username not found.");

        return result;
    }

    @Override
    public void register(RegisterBindingModel bindingModel) {
        User user = this.mapper.map(bindingModel, User.class);
        user.setPassword(this.encoder.encode(bindingModel.getPassword()));
        user.setAuthorities(new HashSet<>(this.roleRepository.findAll()));
        user.setPreferences("");
//        user.setPreferences(bindingModel.getPreferences());
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);

        this.userRepository.save(user);
        Map<String, Object> messageMap = new HashMap<>();

        messageMap.put("preferences", Arrays.stream(bindingModel.getPreferences().split("\\s*,\\s*")).filter(p -> p.trim().length() != 0).collect(Collectors.toList()));
        messageMap.put("username", bindingModel.getUsername());
        this.jmsTemplate.convertAndSend("register-user", messageMap);
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
