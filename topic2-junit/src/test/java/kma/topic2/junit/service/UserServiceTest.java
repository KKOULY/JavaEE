package kma.topic2.junit.service;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.model.User;
import kma.topic2.junit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {
    private static final String LOGIN1 = "user1";
    private static final String LOGIN2 = "user2";
    private static final String LOGIN3 = "user3";
    private static final String NAME = "Name Surname";
    private static final String PASSWORD = "pass123";
    private static final String PASSWORD_INVALID = "1_";
    private static final List<String> ERRORS = Arrays.asList("Password has invalid size",
                                                                "Password doesn't match regex");


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateNewUser() {
        NewUser user = NewUser.builder()
                .fullName(NAME)
                .login(LOGIN1)
                .password(PASSWORD)
                .build();

        User expectedUser = User.builder()
                .fullName(NAME)
                .login(LOGIN1)
                .password(PASSWORD)
                .build();

        userService.createNewUser(user);

        assertEquals(userService.getUserByLogin(LOGIN1), expectedUser);
        assertEquals(userRepository.getUserByLogin(LOGIN1), expectedUser);
    }

    @Test
    public void testUserExistLogin() {
        NewUser user = NewUser.builder()
                .fullName(NAME)
                .login(LOGIN2)
                .password(PASSWORD)
                .build();

        userService.createNewUser(user);
        assertThrows(LoginExistsException.class, () -> userService.createNewUser(user),
                String.format(user.getLogin()));
    }

    @Test
    public void testUserInvalidPasswordSizeAndSymbol() {
        NewUser user = NewUser.builder()
                .fullName(NAME)
                .login(LOGIN3)
                .password(PASSWORD_INVALID)
                .build();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> userService.createNewUser(user));
        System.out.println(exception.getErrors());
        assertEquals(exception.getErrors(), ERRORS);
    }

}