package br.com.mfdesenvolvimento.locationshop.Users;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserModel, UUID>{
  UserModel findByUsername(String username);
}
