package dev.dao;

import dev.entite.Plat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PlatRowMapper implements RowMapper<Plat> {

	@Override
	public Plat mapRow(ResultSet rs, int rowNum) throws SQLException {
		Plat plat = new Plat();
		plat.setNom(rs.getString("nom"));
		plat.setPrixEnCentimesEuros(rs.getInt("prixEnCentimesEuros"));
		return plat;
	}
	
	

}
