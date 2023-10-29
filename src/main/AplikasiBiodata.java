package main;//(1.gunakan package)
import javax.swing.*; //untuk mengakses komponen GUI Swing
import javax.swing.table.AbstractTableModel; //untuk membuat model tabel kustom.
import java.awt.event.ActionEvent; //untuk menangani peristiwa (event) seperti klik tombol.
import java.awt.event.ActionListener; // (sama kyk atasnya)
import java.awt.event.WindowAdapter; //untuk menangani peristiwa pada jendela, khususnya penutupan jendela.
import java.awt.event.WindowEvent; // (sama kyk atasnya)
import java.awt.Desktop;//untuk membuka file dengan aplikasi default.
import java.io.*; //untuk melakukan operasi berkas seperti penulisan dan pembacaan ke/dari file.
import java.util.ArrayList;//untuk menyimpan data dalam bentuk dinamis.
import java.util.Arrays;//untuk mengelola array.
import java.util.List;
import java.awt.Color;//untuk mengatur warna yang akan digunakan dalam komponen GUI.

// Mendefinisikan kelas AplikasiBiodata yang merupakan turunan dari kelas JFrame.
public class AplikasiBiodata extends JFrame {
    // Konstruktor untuk kelas AplikasiBiodata.
    private JTable table;
    private MyTableModel2 tableModel2;
    private JTextField textFieldNama;
    private JTextField textFieldNomorHP;
    private JTextArea textAreaAlamat;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private ButtonGroup bg;
    private int selectedRowIndex = -1;

    // 5. konfirmasi ketika keluar dari aplikasi menggunakan tombol X di jendela aplikasi
    public AplikasiBiodata() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(AplikasiBiodata.this, "Anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    System.exit(0); // Keluar dari aplikasi
                }
            }
        });

        // Membuat label untuk header aplikasi.
        JLabel headerLabel = new JLabel("Form Biodata", SwingConstants.CENTER);
        headerLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        headerLabel.setBounds(15, 10, 350, 20);

        // Membuat label dan textField untuk input "Nama".(1.data yang dikelola)
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 40, 100, 10);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);
        textFieldNama.setBackground(new Color(208, 191, 255));

        // Membuat label dan radio button untuk input "Jenis Kelamin".(1.data yang dikelola)
        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 95, 350, 10);
        radioButton1 = new JRadioButton("Laki-Laki", true);
        radioButton1.setBounds(15, 115, 350, 30);
        radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 145, 350, 30);

        // Mengelompokkan radio button untuk memilih satu opsi.
        bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Membuat label dan textField untuk input "Nomor HP".(1.data yang dikelola)
        JLabel labelNomorHP = new JLabel("Nomor HP:");
        labelNomorHP.setBounds(15, 180, 100, 10);
        textFieldNomorHP = new JTextField();
        textFieldNomorHP.setBounds(15, 200, 350, 30);
        textFieldNomorHP.setBackground(new Color(208, 191, 255));

        // Membuat label dan text area untuk input "Alamat".(1.data yang dikelola)
        JLabel labelAlamat = new JLabel("Alamat:");
        labelAlamat.setBounds(15, 235, 350, 10);
        textAreaAlamat = new JTextArea();
        textAreaAlamat.setLineWrap(true);
        textAreaAlamat.setWrapStyleWord(true);
        JScrollPane scrollableTextArea = new JScrollPane(textAreaAlamat);
        scrollableTextArea.setBounds(15, 255, 350, 100);
        textAreaAlamat.setBackground(new Color(208, 191, 255));

        // Membuat tombol "Simpan" untuk menyimpan data biodata.
        JButton button = new JButton("Simpan");
        button.setBounds(15, 365, 100, 40);
        button.setBackground(new Color(248, 117, 170));

        // Membuat tombol "Edit" untuk mengedit data biodata yang ada.
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(130, 365, 100, 40);
        buttonEdit.setBackground(new Color(255, 223, 223));

        // Membuat tombol "Hapus" untuk menghapus data biodata yang ada.
        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(245, 365, 100, 40);
        buttonHapus.setBackground(new Color(255, 246, 246));

        // Membuat tombol "Simpan ke File" untuk menyimpan data biodata ke dalam file.
        JButton buttonSimpan = new JButton("Simpan ke File");
        buttonSimpan.setBounds(15, 410, 150, 40);
        buttonSimpan.setBackground(new Color(174, 222, 252));

        // Membuat tabel untuk menampilkan data biodata.
        //4. Tampung data dalam JTable
        table = new JTable();
        JScrollPane scrollableTable2 = new JScrollPane(table);
        scrollableTable2.setBounds(15, 455, 350, 200);

        // Membuat model tabel kustom (MyTableModel2) dan mengaitkannya dengan tabel.
        tableModel2 = new MyTableModel2();
        table.setModel(tableModel2);

        // Menambahkan action listener untuk tombol "Simpan" yang akan menangani penyimpanan data biodata.
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menentukan jenis kelamin berdasarkan radio button yang dipilih.
                String jenisKelamin = "";

                if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                if (radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }

                // Mengambil data dari input teks dan text area.
                String nama = textFieldNama.getText();
                String nomorHP = textFieldNomorHP.getText();
                String alamat = textAreaAlamat.getText();

                // Memeriksa apakah semua field telah diisi sebelum menyimpan data.(2. data harus terisi semua)
                if (nama.isEmpty() || nomorHP.isEmpty() || alamat.isEmpty() || jenisKelamin.isEmpty()) {
                    JOptionPane.showMessageDialog(AplikasiBiodata.this, "Harap isi semua field sebelum menyimpan data.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Menampilkan dialog konfirmasi sebelum menyimpan data.(3.konfirmasi)
                    int dialogResult = JOptionPane.showConfirmDialog(AplikasiBiodata.this, "Anda yakin ingin menyimpan data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if (selectedRowIndex == -1) {
                            tableModel2.add(new ArrayList<>(Arrays.asList(nama, nomorHP, jenisKelamin, alamat)));
                        } else {
                            tableModel2.updateRow(selectedRowIndex, new ArrayList<>(Arrays.asList(nama, nomorHP, jenisKelamin, alamat)));
                            selectedRowIndex = -1;
                        }
                        clearInputFields();
                    }
                }
            }
        });

        // 3. Mengedit data yang telah disimpan di tabel
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan baris yang dipilih dari tabel.
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    selectedRowIndex = selectedRow;
                    List<String> rowData = tableModel2.getRowData(selectedRow);
                    textFieldNama.setText(rowData.get(0));
                    textFieldNomorHP.setText(rowData.get(1));
                    String jenisKelamin = rowData.get(2);
                    if (jenisKelamin.equals("Laki-Laki")) {
                        radioButton1.setSelected(true);
                    } else if (jenisKelamin.equals("Perempuan")) {
                        radioButton2.setSelected(true);
                    }
                    textAreaAlamat.setText(rowData.get(3));
                }
            }
        });

        // 4. Menghapus data yang telah disimpan di tabel
        buttonHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan baris yang dipilih dari tabel.
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int dialogResult = JOptionPane.showConfirmDialog(AplikasiBiodata.this, "Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        tableModel2.removeRow(selectedRow);
                        clearInputFields();
                    }
                } else {
                    JOptionPane.showMessageDialog(AplikasiBiodata.this, "Pilih baris yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // 2. Menambahkan action listener untuk tombol "Simpan ke File" yang akan menyimpan data ke dalam file txt.
        buttonSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataToFile("biodata.txt");
            }
        });

        // Menyusun komponen-komponen GUI ke dalam jendela aplikasi.
        this.add(headerLabel);
        this.add(button);
        this.add(buttonEdit);
        this.add(buttonHapus);
        this.add(buttonSimpan);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelNomorHP);
        this.add(textFieldNomorHP);
        this.add(labelAlamat);
        this.add(scrollableTextArea);
        this.add(scrollableTable2);

        // Mengatur ukuran dan layout jendela aplikasi.
        this.setSize(400, 700);
        this.setLayout(null);
    }

    // Metode untuk membersihkan input fields setelah penyimpanan atau pengeditan data.
    private void clearInputFields() {
        textFieldNama.setText("");
        textFieldNomorHP.setText("");
        textAreaAlamat.setText("");
        bg.clearSelection();
    }

    // Metode untuk menyimpan data biodata ke dalam file teks.
    private void saveDataToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Menulis header kolom ke dalam file.
            writer.write("Nama\tJenis Kelamin\tNomor HP\tAlamat\n");
            for (int i = 0; i < tableModel2.getRowCount(); i++) {
                List<String> rowData = tableModel2.getRowData(i);
                String nama = rowData.get(0);
                String jenisKelamin = rowData.get(2);
                String nomorHP = rowData.get(1);
                String alamat = rowData.get(3);
                writer.write(nama + "\t" + jenisKelamin + "\t" + nomorHP + "\t" + alamat + "\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke " + fileName, "Informasi", JOptionPane.INFORMATION_MESSAGE);

            // Buka file biodata.txt dengan aplikasi default yang terkait dengan file txt
            Desktop desktop = Desktop.getDesktop();
            File fileToOpen = new File(fileName);
            desktop.open(fileToOpen);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data ke " + fileName, "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Metode main untuk memulai aplikasi Swing di dalam thread event dispatch.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AplikasiBiodata b4 = new AplikasiBiodata();
                b4.setVisible(true);
            }
        });
    }
}

