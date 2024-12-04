import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static final String DATA_FILE = "userData.dat";
    private Map<String, String> userData;

    // Constructor
    public DataManager() {
        userData = new HashMap<>();
        loadUserData();
    }

    // Membaca data dari file
    private void loadUserData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            userData = (Map<String, String>) ois.readObject();
            System.out.println("Data pengguna berhasil dimuat.");
        } catch (FileNotFoundException e) {
            System.out.println("File data tidak ditemukan, membuat file baru.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Gagal membaca data pengguna: " + e.getMessage());
        }
    }

    // Menyimpan data ke file
    private void saveUserData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(userData);
            System.out.println("Data pengguna berhasil disimpan.");
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data pengguna: " + e.getMessage());
        }
    }

    // Menambahkan atau memperbarui data pengguna
    public void setUserData(String key, String value) {
        userData.put(key, value);
        saveUserData();
    }

    // Mendapatkan data pengguna
    public String getUserData(String key) {
        return userData.getOrDefault(key, "Data tidak ditemukan.");
    }

    // Menghapus data pengguna
    public void removeUserData(String key) {
        if (userData.containsKey(key)) {
            userData.remove(key);
            saveUserData();
            System.out.println("Data pengguna dengan kunci \"" + key + "\" berhasil dihapus.");
        } else {
            System.out.println("Data dengan kunci \"" + key + "\" tidak ditemukan.");
        }
    }
}

