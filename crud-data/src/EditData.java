import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class EditData extends JFrame{
    JLabel ljudul;
    JButton edit,kembali;
    Statement statement;
    ResultSet resultSet;
    String[][] data = new String[500][4];
    String[] kolom = {"Nim","Nama","Alamat"};
    JTable tabel;
    JScrollPane scrollPane;

    public EditData() throws ClassNotFoundException, SQLException{
        ljudul = new JLabel ("Seluruh Data Mahasiswa");
        edit = new JButton ("Edit");
        kembali = new JButton ("Kembali");
        tabel = new JTable(data,kolom);
        scrollPane = new JScrollPane(tabel);

        setTitle("EDIT DATA MAHASISWA");
        setSize (700,600);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        add(ljudul);
        add(edit);
        add(kembali);

        ljudul.setBounds(150, 50, 250, 30);
        edit.setBounds(150, 90, 100, 20);
        kembali.setBounds(270, 90, 100, 20);

        setLayout(new FlowLayout());
        add(scrollPane);

        KoneksiDB koneksi = new KoneksiDB();
        statement = koneksi.getKoneksi().createStatement();
        String sql = "SELECT *FROM data_mhs";
        resultSet = statement.executeQuery(sql);
        int p = 0;
        while (resultSet.next()){
            data[p][0] = resultSet.getString("nim");
            data[p][1] = resultSet.getString("nama");
            data[p][2] = resultSet.getString("alamat");
            p++;
        }
        statement.close();

        edit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new ProsesEdit();
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