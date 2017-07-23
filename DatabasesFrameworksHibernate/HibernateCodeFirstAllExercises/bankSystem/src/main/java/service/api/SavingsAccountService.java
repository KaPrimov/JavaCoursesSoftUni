package service.api;

public interface SavingsAccountService extends AccountService {
    void addInterest(String accountNumber, double interest);
}
