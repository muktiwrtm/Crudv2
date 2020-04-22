import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.*;


public class InputMahasiswa extends JFrame{
    JLabel ltitle,lnama,lnim,lalamat;
    JTextField txnama,txnim,txalamat;
    JButton simpan, kembali;
    Statement statement;

    public InputMahasiswa(){
        setTitle("INPUT DATA MAHASISWA");

        ltitle = new JLabel("INPUT DATA MAHASISWA");
        lnama = new JLabel("Nama    : ");
        lnim = new JLabel("NIM      : ");
        lalamat = new JLabel ("Alamat   : ");

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

        ltitle.setBounds(120, 20, 150, 30);
        lnama.setBounds(80, 60, 80, 20);
        lnim.setBounds(80, 90, 80, 20);
        lalamat.setBounds(80, 120, 80, 20);
        txnama.setBounds(180, 60, 120, 20);
        txnim.setBounds(180, 90, 120, 20);
        txalamat.setBounds(180, 120, 120, 60);
        simpan.setBounds(100, 190, 90, 20);
        kembali.setBounds(200, 190, 90, 20);

        setSize(400,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int a1 =  Integer.parseInt(txnim.getText());
                    String a2 = txnama.getText();
                    String a3 = txalamat.getText();

                    KoneksiDB koneksi = new KoneksiDB();
                    try {
                        statement = koneksi.getKoneksi().createStatement();
                        String sql = "INSERT INTO data_mhs VALUES ('"
                                + a2 + "','" + a1 + "','" + a3 + "')";
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(rootPane, "Data Tersimpan");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(InputMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(InputMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane,"TIPE DATA SALAH");
                } catch (Error ext){
                    JOptionPane.showMessageDialog(rootPane,"SALAH");
                }
            }
        });

        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                MenuTampil show = new MenuTampil();
                show.menu();
            }
        });
    }
}