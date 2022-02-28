package com.example.card.credit.repository;

import com.example.card.credit.entity.CreditCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDetailsRepository extends CrudRepository<CreditCardEntity, Long> {

    List<CreditCardEntity> findAll();
}