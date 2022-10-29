package com.vprp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vprp.user.entity.CaptchaModel;


@Repository
public interface CaptchaRepository extends JpaRepository<CaptchaModel, Long> {

}