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
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        List<String> rowItem = data.get(row);
        return rowItem.get(col);
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
