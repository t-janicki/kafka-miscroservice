package com.auth.service;

import com.auth.repository.AccountRepository;
import com.auth.domain.Account;
import com.auth.domain.Role;
import com.auth.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(s);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    public Account registerUser(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.grantAuthority(Role.ROLE_USER);
        return accountRepository.save(account);
    }

    public Account registerUser(UserDTO userDto) {
        Account user = new Account();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.grantAuthority(Role.ROLE_USER);
        return accountRepository.save(user);
    }

}