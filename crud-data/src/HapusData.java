import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

class HapusData extends JFrame {

    Statement statement;
    ResultSet resultSet;
    JButton hapus, kembali;
    JLabel lJudul;
    String[][] data = new String[480][3];
    String[] kolom = {"NIM", "NAMA", "ALAMAT"};
    JTable tabel;
    JScrollPane scrollPane;
    String DBurl = "jdbc:mysql;//localhost/mahsiswa";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;

    public HapusData() throws ClassNotFoundException, SQLException {
        lJudul = new JLabel("SELURUH DATA MAHASIWA");
        hapus = new JButton("Hapus");
        kembali = new JButton("Kembali");
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);

        setTitle("HAPUS DATA MAHASISWA");
        lJudul.setHorizontalAlignment(SwingConstants.CENTER);
        KoneksiDB koneksi = new KoneksiDB();
        statement = koneksi.getKoneksi().createStatement();
        String sql = "SELECT *FROM data_mhs";
        resultSet = statement.executeQuery(sql);
        int p = 0;
        while (resultSet.next()) {
            data[p][0] = resultSet.getString("nim");
            data[p][1] = resultSet.getString("nama");
            data[p][2] = resultSet.getString("alamat");
            p++;
        }
        setLayout(null);
        add(lJudul);
        add(hapus);
        add(kembali);

        lJudul.setBounds(50, 10, 150, 25);
        kembali.setBounds(30, 90, 100, 25);
        hapus.setBounds(140, 90, 100, 25);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,600);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                MenuTampil show = new MenuTampil();
                show.menu();
            }
        });

        hapus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                KoneksiDB koneksi = new KoneksiDB();
                try {
                    statement = koneksi.getKoneksi().createStatement();
                    statement.executeUpdate("DELETE FROM data_mhs");
                    JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus!", "Hasil", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

}