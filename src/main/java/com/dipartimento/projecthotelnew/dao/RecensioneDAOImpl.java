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
    //PER VERIFICARE CHE L'UTENTE ESISTE
    private UserDAOImpl userDAO;
    // Costruttore per l'iniezione di JdbcTemplate
    public RecensioneDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // Aggiunge una recensione al database
    @Override
    public int addRecensione(Recensione recensione) {
        // Verifica se l'utente esiste nel database
        User user = userDAO.findByUsername(recensione.getNomeUtente());
        // Se l'utente esiste, inserisce la recensione nel database
        if (user != null) {
            recensione.setUserId(user.getId()); // Impostiamo l'userId trovato

            // SQL per inserire la recensione nella tabella 'recensioni'
            String sql = "INSERT INTO recensioni (user_id, nome_utente, commento) VALUES (?, ?, ?)";
            // Esegui l'operazione di insert e restituisci il numero di righe modificate
            return jdbcTemplate.update(sql, recensione.getUserId(), recensione.getNomeUtente(), recensione.getCommento());
        } else {
            throw new RuntimeException("Utente non trovato");
        }
    }

    // Ottiene tutte le recensioni, unendo i dati con gli utenti
    @Override
    public List<Recensione> getAllRecensioni() {
        // SQL per ottenere tutte le recensioni con il nome dell'utente associato
        String sql = "SELECT r.*, u.username AS nomeUtente " +
                "FROM recensioni r " +
                "JOIN users u ON r.user_id = u.id";
        // Esegui la query e mappa i risultati in una lista di oggetti 'Recensione'
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Recensione recensione = new Recensione();
            recensione.setId(rs.getInt("id"));
            recensione.setCommento(rs.getString("commento"));
            recensione.setNomeUtente(rs.getString("nomeUtente"));
            return recensione;
        });
    }

    // Ottiene recensioni in base all'ID dell'utente (questo metodo è attualmente vuoto)
    @Override
    public List<Recensione> getRecensioniByUserId(int userId) {
        return List.of();
    }

    // Elimina una recensione dal database usando l'id della recensione
    @Override
    public void deleteRecensione(int id) {
        // SQL per eliminare la recensione con l'ID specificato
        String sql = "DELETE FROM recensioni WHERE id = ?";
        // Esegui la query per eliminare la recensione
        jdbcTemplate.update(sql, id);
    }
}
