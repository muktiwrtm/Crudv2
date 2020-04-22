
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class MenuTampil extends JFrame{
    JLabel ljudul;
    JButton input, tampil, hapus, edit, exit;

    public void menu(){
        setTitle("GUI MAHASISWA");

        ljudul = new JLabel("MENU");

        input = new JButton("1. Input Data Mahasiswa");
        tampil = new JButton("2. Tampilkan Seluruh Data");
        hapus = new JButton("3. Hapus Data Mahasiswa");
        edit = new JButton("4. Edit Data Mahasiswa");
        exit = new JButton("0. Exit");

        setLayout(null);
        add(ljudul);
        add(input);
        add(tampil);
        add(hapus);
        add(edit);
        add(exit);

        ljudul.setBounds(178, 30, 50, 25);
        input.setBounds(90, 80, 200, 25);
        tampil.setBounds(90, 120, 200, 25);
        hapus.setBounds(90, 160, 200, 25);
        edit.setBounds(90, 200, 200, 25);
        exit.setBounds(90, 240, 200, 25);

        setSize(400, 350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new InputMahasiswa();
            }
        });

        tampil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new TampilData();
            }
        });

        hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(true);
                try {
                    new HapusData();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuTampil.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuTampil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                try {
                    new EditData();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuTampil.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuTampil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

    }
}