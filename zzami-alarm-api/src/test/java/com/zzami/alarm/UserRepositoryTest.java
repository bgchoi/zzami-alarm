package com.zzami.alarm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.zzami.alarm.api.AlarmApiApplication;
import com.zzami.alarm.api.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AlarmApiApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    
    @Test
    public void test() {
        userRepository.count();
        userRepository.findAll();
        userRepository.findByEncrytedPassword("aa");
        userRepository.findByUsername("hello");
        userRepository.getOne("C0000");
    }
    
}
