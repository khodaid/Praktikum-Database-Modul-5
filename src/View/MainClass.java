/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author khoda
 */
import Controller.kontrol;
import Model.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class MainClass {
    public static void main(String[] args) throws SQLException, ParseException {
        kontrol control = new kontrol();
        Scanner input = new Scanner(System.in);
        String ulang = null;
        int menu;
        System.out.println(control.getPp().size());
        
        System.out.println("============== Selamat Datang ================");
        
        do{
            System.out.println("============== Menu ===============");
            System.out.println("1 : Create");
            System.out.println("2 : Delete");
            System.out.println("3 : Update");
            System.out.println("4 : Select");
            System.out.println("5 : Select Join");
            System.out.println("6 : Select View");
            
            System.out.print("Masukan menu yang anda inginkan : ");
            menu = input.nextInt();
            switch(menu) {
                case 1 :
                    System.out.println("============= Create ==============");
                    System.out.print("Masukan nama : ");
                    String nama = input.next();
                    System.out.print("Masukan NO. Telp : ");
                    String telp = input.next();
                    control.InsertAMP("marketing", "id_marketing", nama, telp);
                    break;
                case 2 :
                    System.out.println("============= Delete ==============");
                    System.out.print("Masukan ID Marketing : ");
                    int id = input.nextInt();
                    control.DeleteAMP("marketing", id);
                    break;
                case 3 :
                    System.out.println("============= Update ==============");
                    System.out.print("Masukan ID Marketing : ");
                    id = input.nextInt();
                    System.out.print("Masukan No. Telp baru : ");
                    telp = input.next();
                    control.UpdateAMP("marketing", id, telp);
                    break;
                case 4 :
                    System.out.println("============= Select ==============");
                    System.out.println("ID\t Nama\t NO. TELP");
                    System.out.println("===================================");
                    for (Marketing m : control.getMarketing()){
                        System.out.println(m.getId()+"\t"+m.getNama()+"\t"+m.getTelp());
                    }
                    break;
                case 5 :
                    System.out.println("============= Join ==============");
                    System.out.println("Marketing\t Profil\t\t Jumlah");
                    System.out.println("=================================");
                    //untuk melilhat join
                    for (Pengguna_Profil pp : control.getPp()) {
                        System.out.println(pp.getMarketing()+"\t\t"+pp.getProfil()+"\t\t"+pp.getJumlah());
                    }
                    break;
                case 6 :
                    System.out.println("============= View ==============");
                    System.out.println("Marketing\t Owner\t");
                    System.out.println("==================================");
                    for (Kepemilikan_Proyek kp : control.getkp()) {
                        System.out.println(kp.getMarketing()+"\t\t"+kp.getOwner());
                    }
                    break;
            }
            System.out.println("============= Ulang ==============");
            System.out.print("Anda mau mengulangi (y/n)");
            ulang = input.next();
        }while(ulang.equals("y"));
    }
}
