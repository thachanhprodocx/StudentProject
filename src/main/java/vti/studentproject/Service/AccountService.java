package vti.studentproject.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vti.studentproject.Model.Entity.Account;
import vti.studentproject.Model.Request.AccountRequest;
import vti.studentproject.Model.Request.SearchAccountReq;
import vti.studentproject.Model.Request.UpdateAccountRQ;

import java.util.List;

@Service
public interface AccountService {
    void createAccount(AccountRequest accountRequest);

    Account getAccountById(int id);

    Account updateAccount(int id, UpdateAccountRQ accountRequest);

    void deleteAccount(int id);

    Page<Account> getAllAccounts(Pageable pageable);

    Page<Account> search(SearchAccountReq accountRequest);

}
