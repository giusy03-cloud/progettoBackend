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
    private UserDAOImpl userDAO; // Per ottenere l'utente
    public RecensioneDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addRecensione(Recensione recensione) {
        // Recuperiamo l'utente in base al nome utente
        User user = userDAO.findByUsername(recensione.getNomeUtente());

        if (user != null) {
            recensione.setUserId(user.getId()); // Impostiamo l'userId trovato

            // Ora possiamo inserire la recensione
            String sql = "INSERT INTO recensioni (user_id, nome_utente, commento) VALUES (?, ?, ?)";
            return jdbcTemplate.update(sql, recensione.getUserId(), recensione.getNomeUtente(), recensione.getCommento());
        } else {
            throw new RuntimeException("Utente non trovato");
        }
    }

    // Metodo per recuperare tutte le recensioni con il nome utente
    @Override
    public List<Recensione> getAllRecensioni() {
        String sql = "SELECT r.*, u.username AS nomeUtente " +
                "FROM recensioni r " +
                "JOIN users u ON r.user_id = u.id"; // Usa username al posto di nome
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Recensione recensione = new Recensione();
            recensione.setId(rs.getInt("id"));
            recensione.setCommento(rs.getString("commento"));
            recensione.setNomeUtente(rs.getString("nomeUtente"));  // Imposta il nome utente dalla query
            return recensione;
        });
    }

    @Override
    public List<Recensione> getRecensioniByUserId(int userId) {
        return List.of();
    }

    // Metodo per eliminare una recensione
    @Override
    public void deleteRecensione(int id) {
        String sql = "DELETE FROM recensioni WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
