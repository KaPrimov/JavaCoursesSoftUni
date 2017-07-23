package dao.api;

public interface SavingsAccountDao extends AccountDao {

    void addInterest(String accountNumber, double interest);

}
