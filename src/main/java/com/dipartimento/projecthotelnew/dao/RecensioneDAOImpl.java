package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.Recensione;
import com.dipartimento.projecthotelnew.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecensioneDAOImpl implements RecensioneDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDAOImpl userDAO;
    public RecensioneDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addRecensione(Recensione recensione) {
        User user = userDAO.findByUsername(recensione.getNomeUtente());

        if (user != null) {
            recensione.setUserId(user.getId()); // Impostiamo l'userId trovato

            String sql = "INSERT INTO recensioni (user_id, nome_utente, commento) VALUES (?, ?, ?)";
            return jdbcTemplate.update(sql, recensione.getUserId(), recensione.getNomeUtente(), recensione.getCommento());
        } else {
            throw new RuntimeException("Utente non trovato");
        }
    }

    @Override
    public List<Recensione> getAllRecensioni() {
        String sql = "SELECT r.*, u.username AS nomeUtente " +
                "FROM recensioni r " +
                "JOIN users u ON r.user_id = u.id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Recensione recensione = new Recensione();
            recensione.setId(rs.getInt("id"));
            recensione.setCommento(rs.getString("commento"));
            recensione.setNomeUtente(rs.getString("nomeUtente"));
            return recensione;
        });
    }

    @Override
    public List<Recensione> getRecensioniByUserId(int userId) {
        return List.of();
    }

    @Override
    public void deleteRecensione(int id) {
        String sql = "DELETE FROM recensioni WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
