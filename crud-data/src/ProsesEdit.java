import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


class ProsesEdit extends JFrame {
    JLabel ltitle, lnama, lnim, lalamat;
    JTextField txnama, txnim, txalamat;
    JButton simpan, kembali;
    Statement statement;

    public ProsesEdit() {

        setTitle("FORM EDIT DATA");
        ltitle = new JLabel("Form Edit Data");
        lnama = new JLabel("Nama   : ");
        lnim = new JLabel("NIM      :");
        lalamat = new JLabel ("Alamat : ");

        txnama = new JTextField("");
        txnim = new JTextField("");
        txalamat = new JTextField("");

        simpan = new JButton("Simpan");
        kembali = new JButton("Kembali");

        setLayout(null);
        add(ltitle);
        add(lnama);
        add(lnim);
        add(lalamat);

        add(txnama);
        add(txnim);
        add(txalamat);

        add(simpan);
        add(kembali);
        ltitle.setBounds(150, 50, 150, 30);
        lnama.setBounds(150, 90, 50, 20);
        lnim.setBounds(150, 130, 50, 20);
        lalamat.setBounds(150, 170, 50, 20);
        txnama.setBounds(200, 90, 120, 20);
        txnim.setBounds(200, 130, 120, 20);
        txalamat.setBounds(200, 170, 120, 50);
        simpan.setBounds(150, 230, 90, 20);
        kembali.setBounds(250, 230, 90, 20);

        setSize(500,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        simpan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                btnUpdateactionListener();
            }

            private void btnUpdateactionListener() {
                KoneksiDB koneksi = new KoneksiDB();
                try {
                    statement = koneksi.getKoneksi().createStatement();
                    statement.executeUpdate("UPDATE data_mhs SET nim='" + txnim.getText() +"',"+ "nama='"+
                            txnama.getText() +"',"+ "nim='"+txalamat.getText()+"'");

                    JOptionPane.showMessageDialog(null, "Data berhasil di Update!","Hasil",JOptionPane.INFORMATION_MESSAGE);
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal diupdate!","Hasil",JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex){
                    JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!","Hasil",JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        kembali.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                try {
                    new EditData();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProsesEdit.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}