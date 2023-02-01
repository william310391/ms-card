package com.crud.card.repository;
import com.crud.card.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepository implements ICardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Card> findAll() {
        String SQL= "SELECT * FROM CARDS WHERE STATUS=1";
        return jdbcTemplate.query(SQL,BeanPropertyRowMapper.newInstance(Card.class));
    }

    @Override
    public int save(Card card) {
        String SQL= "INSERT INTO CARDS VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(SQL,new Object[]{card.getName(),card.getNumber(),card.getType(),card.getCvv(),card.getStatus()});
    }

    @Override
    public int update(Card card) {
        String SQL= "UPDATE CARDS SET NAME=?, NUMBER=?, TYPE=?, CVV=?, WHERE id_card=?";
        return jdbcTemplate.update(SQL,new Object[]{card.getName(),card.getNumber(),card.getType(),card.getCvv(),card.getId_card()});
    }

    @Override
    public int deleteById(int id) {
        String SQL= "UPDATE CARDS SET STATUS=0, WHERE id_card=?";
        return jdbcTemplate.update(SQL,new Object[]{id});
    }
}
