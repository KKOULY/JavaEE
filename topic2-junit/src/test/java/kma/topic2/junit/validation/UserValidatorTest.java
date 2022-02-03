package kma.topic2.junit.validation;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {
    private static final String LOGIN = "admin";
    private static final String NAME = "Name Surname";
    private static final String PASSWORD = "pass";
    private static final String PASSWORD_LONG = "password12345";
    private static final String PASSWORD_INVALID_SYMBOL = "pass!?";
    private static final String PASSWORD_LONG_INVALID_SYMBOL = "password!?345";

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserValidator userValidator;


    @Test
    public void testNewUserDoesNotExist() {
        when(userRepository.isLoginExists(any())).thenReturn(false);
        assertDoesNotThrow(() -> userValidator.validateNewUser(getUser()));
    }

    @Test
    public void testNewUserExist() {
        when(userRepository.isLoginExists(any())).thenReturn(true);
        assertThrows(LoginExistsException.class,
                () -> userValidator.validateNewUser(getUser()));
    }

    @Test
    public void testPasswordLengthNotCorrect(){
        when(userRepository.isLoginExists(any())).thenReturn(false);
        NewUser user = NewUser.builder()
                        .login(LOGIN).password(PASSWORD_LONG).fullName(NAME).build();
        assertThrows(ConstraintViolationException.class,() -> userValidator.validateNewUser(user));
    }
    @Test
    public void testPasswordSymbolsNotCorrect(){
        when(userRepository.isLoginExists(any())).thenReturn(false);
        NewUser user = NewUser.builder()
                .login(LOGIN).password(PASSWORD_INVALID_SYMBOL).fullName(NAME).build();
        assertThrows(ConstraintViolationException.class,() -> userValidator.validateNewUser(user));
    }
    @Test
    public void testPasswordLengthAndSymbolsNotCorrect(){
        when(userRepository.isLoginExists(any())).thenReturn(false);
        NewUser user = NewUser.builder()
                .login(LOGIN).password(PASSWORD_LONG_INVALID_SYMBOL).fullName(NAME).build();
        assertThrows(ConstraintViolationException.class,() -> userValidator.validateNewUser(user));
    }

    private NewUser getUser(){
       return NewUser.builder()
                .fullName(NAME)
                .login(LOGIN)
                .password(PASSWORD)
                .build();
    }

}