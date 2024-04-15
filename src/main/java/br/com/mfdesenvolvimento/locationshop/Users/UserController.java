package br.com.mfdesenvolvimento.locationshop.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private IUserRepository userRepository;

  @GetMapping("/")
  public ResponseEntity<Object> getAllUser() {
    var user = this.userRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PostMapping("/")
  public ResponseEntity<Object> createUser(@RequestBody UserModel userModel) {
    var user = this.userRepository.findByUsername(userModel.getUsername());
    if (user != null) {
      return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("username already in use");
    }

    var passwordHashered = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

		userModel.setPassword(passwordHashered);

		var userCreated = this.userRepository.save(userModel);
		return ResponseEntity.status(HttpStatus.OK).body(userCreated);
  }
}
