package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Carro;

public class CarroDAO extends DAO {
    
    public CarroDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
    
    public boolean insert(Carro carro) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO carro (codigo, marca, modelo, cor, condicao) "
                       + "VALUES (" + carro.getCodigo() + ", '" + carro.getMarca() + "', '"
                       + carro.getModelo() + "', '" + carro.getCor() + "', '" + carro.getCondicao() + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
	
    public Carro get(int codigo) {
        Carro carro = null;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM carro WHERE codigo=" + codigo;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);   
            if(rs.next()){            
                 carro = new Carro(rs.getInt("codigo"), rs.getString("marca"), rs.getString("modelo"),
                		 rs.getString("cor"), rs.getString("condicao").charAt(0));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carro;
    }
	
	public List<Carro> get() {
		return get("");
	}
	
	public List<Carro> getOrderByCodigo() {
		return get("codigo");		
	}
	
	public List<Carro> getOrderByMarca() {
		return get("marca");		
	}
	
	public List<Carro> getOrderByModelo() {
	    return get("modelo");       
	}
	
	public List<Carro> getOrderByCor() {
		return get("cor");		
	}
	
	private List<Carro> get(String orderBy) {    
        
        List<Carro> carros = new ArrayList<Carro>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM carro" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);               
            while(rs.next()) {                  
                Carro u = new Carro(rs.getInt("codigo"), rs.getString("marca"), rs.getString("modelo"),
                		  rs.getString("cor"), rs.getString("condicao").charAt(0));
                carros.add(u);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carros;
    }
	
	public List<Carro> getCondicaoNovo() {
        List<Carro> carros = new ArrayList<Carro>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM carro WHERE condicao LIKE 'N'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);               
            while(rs.next()) {                  
                Carro u = new Carro(rs.getInt("codigo"), rs.getString("marca"), rs.getString("modelo"),
                		  rs.getString("cor"), rs.getString("condicao").charAt(0));
                carros.add(u);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carros;
    }
	
	public boolean update(Carro carro) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE carro SET marca = '" + carro.getMarca() + "', modelo = '"  
                       + carro.getModelo() + "', cor = '" + carro.getCor() + "', condicao = '"
            		   + carro.getCondicao() + "'" + " WHERE codigo = " + carro.getCodigo();
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM carro WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean autenticar(int codigo, String marca, String modelo, String cor, char condicao) {
	    boolean resp = false;
	    
	    try {
	        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        String sql = "SELECT * FROM carro WHERE codigo = " + codigo + " AND marca LIKE '"
	        		   + marca + "' AND modelo LIKE '" + modelo + "' AND cor LIKE '"
	        		   + cor + "' AND condicao = '" + condicao + "'";
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
	        resp = rs.next();
	        st.close();
	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }
	    return resp;
	}
}
