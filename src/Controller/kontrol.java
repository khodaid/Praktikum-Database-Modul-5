/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author khoda
 */
import Model.*;
import Database.Koneksi;
import java.util.*;
import java.sql.*;

public class kontrol {
    ArrayList<Marketing> marketing;
    ArrayList<Pengguna_Profil> pp;
    ArrayList<Kepemilikan_Proyek> kp;
    Koneksi koneksi;

    public kontrol() throws SQLException{
        this.koneksi = new Koneksi();
        this.marketing = new ArrayList<>();
        this.pp = new ArrayList<>();
        this.kp = new ArrayList<>();
    }
    
    public void InsertAMP(String table,String nama_sequence, String nama, String WT){
        String sql = "INSERT INTO "+table+" VALUES("+nama_sequence+","+nama+","+WT+")";
        this.koneksi.ManipulasiData(sql);
    }
    
    public void UpdateAMP(String table,int id, String update){
        String sql = null;
        if(null != table)switch (table) {
            case "arsitek":{
                sql = "UPDATE "+table+" set telp_kantor = "+update+" where id_arsitek = "+id;
                    break;
                }
            case "marketing":{
                sql = "UPDATE "+table+" set no_telp = "+update+" where id_marketing = "+id;
                    break;
                }
            case "profil_aluminium":{
                sql = "UPDATE "+table+" set warna = "+update+" where id_profil = "+id;
                    break;
                }
            default:
                break;
        }
        this.koneksi.ManipulasiData(sql);
    }
    
    public void DeleteAMP(String table, int id){
        String sql = null;
        switch (table) {
            case "arsitek":
                sql = "DELETE FROM "+table+" WHERE id_arsitek = "+id;
                break;
            case "marketing":
                sql = "DELETE FROM "+table+" WHERE id_marketing = "+id;
                break;
            case "profil_aluminium":
                sql = "DELETE FROM "+table+" WHERE id_profil = "+id;
                break;
            default:
                break;
        }
        this.koneksi.ManipulasiData(sql);
    }

    public ArrayList<Pengguna_Profil> getPp() throws SQLException {
        this.pp.clear();
        ResultSet rs = this.koneksi.GetData("SELECT marketing.nama_marketing, profil_aluminium.nama_profil,count(marketing.nama_marketing) AS JUMLAH from proyek right join profil_aluminium on profil_aluminium.id_profil = proyek.id_profil right join marketing on marketing.id_marketing = proyek.id_marketing where profil_aluminium.nama_profil = 'standart' and profil_aluminium.warna = 'iron grey' group by marketing.nama_marketing, profil_aluminium.nama_profil");
        while(rs.next()){
            Pengguna_Profil pProfil =  new Pengguna_Profil();
            pProfil.setMarketing(rs.getString("NAMA_MARKETING"));
            pProfil.setProfil(rs.getString("NAMA_PROFIL"));
            pProfil.setJumlah(rs.getInt("JUMLAH"));
            
            this.pp.add(pProfil);
        }
        return this.pp;
    }
    
    public ArrayList<Kepemilikan_Proyek> getkp() throws SQLException {
        this.kp.clear();
        ResultSet rs = this.koneksi.GetData("SELECT * FROM KEPEMILIKAN_PROYEK");
        while (rs.next()) {            
            Kepemilikan_Proyek kProyek = new Kepemilikan_Proyek();
            kProyek.setMarketing(rs.getString("NAMA_MARKETING"));
            kProyek.setOwner(rs.getString("NAMA_OWNER"));
            this.kp.add(kProyek);
        }
        return this.kp;
    }
    
    public ArrayList<Marketing> getMarketing () throws SQLException {
        this.marketing.clear();
        ResultSet rs = this.koneksi.GetData("SELECT * FROM MARKETING");
        while (rs.next()) {            
            Marketing m = new Marketing();
            m.setId(rs.getInt("ID_MARKETING"));
            m.setNama(rs.getString("NAMA_MARKETING"));
            m.setTelp(rs.getInt("NO_TELP"));
            this.marketing.add(m);
        }
        return this.marketing;
    }
}
