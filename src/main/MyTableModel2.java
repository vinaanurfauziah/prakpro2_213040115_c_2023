package main;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MyTableModel2 extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Jenis Kelamin", "Nomor HP", "Alamat"};
    private List<List<String>> data = new ArrayList<>();

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        // Mengubah label kolom sesuai dengan yang diinginkan
        switch (col) {
            case 0:
                return "Nama";
            case 1:
                return "Jenis Kelamin";
            case 2:
                return "Nomor HP";
            case 3:
                return "Alamat";
            default:
                return "";
        }
    }

    public Object getValueAt(int row, int col) {
        List<String> rowItem = data.get(row);
        if (col == 1) {
            // Jika kolom adalah "Jenis Kelamin", kembalikan nilainya
            return rowItem.get(2);
        } else if (col == 2) {
            // Jika kolom adalah "Nomor HP", kembalikan nilainya
            return rowItem.get(1);
        } else {
            // Selain itu, kembalikan nilai sesuai dengan kolom yang benar
            return rowItem.get(col);
        }
    }

    public boolean isCellEditable(int row, int col) {
    return false;
}

    public void add(List<String> value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public List<String> getRowData(int rowIndex) {
        return data.get(rowIndex);
    }

    public void updateRow(int rowIndex, List<String> updatedData) {
        data.set(rowIndex, updatedData);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void removeRow(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
