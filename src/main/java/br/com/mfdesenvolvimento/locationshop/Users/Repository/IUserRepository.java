package br.com.mfdesenvolvimento.locationshop.Users.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mfdesenvolvimento.locationshop.Users.Model.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, UUID>{
  UserModel findByUsername(String username);
}
