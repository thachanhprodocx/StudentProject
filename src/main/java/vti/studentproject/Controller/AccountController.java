package vti.studentproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vti.studentproject.Model.Entity.Account;
import vti.studentproject.Model.Request.AccountRequest;
import vti.studentproject.Model.Request.SearchAccountReq;
import vti.studentproject.Model.Request.UpdateAccountRQ;
import vti.studentproject.Repository.AccountRepository;
import vti.studentproject.Service.Class.AccountServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private AccountServiceImpl accountService;
//    @PutMapping("/{id}/role")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> updateAccountRole(@PathVariable int id, @RequestParam String newRole) {
//        accountService.updateAccountRole(id, newRole);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    // Get all accounts
    @GetMapping("get-all")
    public Page<Account> getAllAccounts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "1") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return accountService.getAllAccounts(pageable);
    }

    // Get an account by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable int id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }


    // Create a new account
    @PostMapping("create")
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/search")
    public List<Account> searchAccount(@RequestParam(required = false) String username,
                                       @RequestParam(required = false) String fullName,
                                       @RequestParam(required = false) String email) {
        return accountRepository.findByUsernameContainingIgnoreCaseOrFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(username, fullName, email);
    }


    @PostMapping("/search")
    public Page<Account> search(@RequestBody  SearchAccountReq searchAccountReq){
        return accountService.search(searchAccountReq);
    }

    // Update an account by id
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable int id, @RequestBody UpdateAccountRQ accountRequest) {
        return accountService.updateAccount(id, accountRequest);
    }


    // Delete an account by id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
    }
}
