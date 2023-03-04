package vti.studentproject.Service.Class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import vti.studentproject.Exception.AppException;
import vti.studentproject.Exception.ErrorResponseBase;
import vti.studentproject.Model.Entity.Account;
import vti.studentproject.Model.Entity.ClassEntity;
import vti.studentproject.Model.Entity.Role;
import vti.studentproject.Model.Request.AccountRequest;
import vti.studentproject.Model.Request.BaseRequest;
import vti.studentproject.Model.Request.SearchAccountReq;
import vti.studentproject.Model.Request.UpdateAccountRQ;
import vti.studentproject.Repository.AccountRepository;
import vti.studentproject.Repository.ClassRepository;
import vti.studentproject.Repository.specification.AccountSpecification;
import vti.studentproject.Service.AccountService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createAccount(AccountRequest accountRequest) {
//        // Kiểm tra xem email đã được sử dụng chưa
        if (accountRepository.existsByEmail(accountRequest.getEmail())) {
            throw new AppException(ErrorResponseBase.DOUBLE_EMAIL_EX);
        }

        // Tạo tài khoản mới
        Account account = new Account();
        account.setEmail(accountRequest.getEmail());
        account.setPassword(encoder.encode(accountRequest.getPassword()));
        account.setRole(Role.STUDENT); // Set role mặc định là STUDENT
        account.setFullName(accountRequest.getFullName());
        account.setPhoneNumber(accountRequest.getPhoneNumber());
        account.setAddress(accountRequest.getAddress());
        account.setFacebook(accountRequest.getFacebook());
        account.setDateOfBirth(accountRequest.getDateOfBirth());
        int classEntityId = accountRequest.getClassId();
        ClassEntity classEntity = classRepository.findById(classEntityId)
                .orElseThrow(() -> new AppException(ErrorResponseBase.NOT_FOUND));
        account.setClassEntityId(classEntity);
        account.setUsername(accountRequest.getUsername());
        account.setInformation(accountRequest.getInformation());

        accountRepository.save(account);
    }


    @Override
    public Account getAccountById(int id) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppException(ErrorResponseBase.USERNAME_Not);
        }
        try {
            return optional.get();
        } catch (Exception ex) {
            throw new AppException(ex);
        }
    }

    @Override
    public Account updateAccount(int id, UpdateAccountRQ accountRequest) {
        Account accountDb = accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorResponseBase.NOT_FOUND));
        // chuyen tu accuntrequest >> entity  --logic
        accountDb.setEmail(accountRequest.getEmail());
        accountDb.setAddress(accountRequest.getAddress());
        accountDb.setPassword(encoder.encode(accountRequest.getPassword()));
        int classEntityId = accountRequest.getClassEntityId();
        ClassEntity classEntity = classRepository.findById(classEntityId)
                .orElseThrow(() -> new AppException(ErrorResponseBase.NOT_FOUND));
        accountDb.setClassEntityId(classEntity);
        accountDb.setFacebook(accountRequest.getFacebook());
        accountDb.setDateOfBirth(accountRequest.getDateOfBirth());
        accountDb.setFullName(accountRequest.getFullName());
        accountDb.setPhoneNumber(accountRequest.getPhoneNumber());
        accountDb.setRole(Role.valueOf(accountRequest.getRole()));
        accountDb.setInformation(accountRequest.getInformation());
        accountDb.setUsername(accountRequest.getUsername());
        return accountRepository.save(accountDb);
    }


    @Override
    public void deleteAccount(int id) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_FOUND);
        }
        accountRepository.delete(optional.get());
    }


    @Override
    public Page<Account> getAllAccounts(Pageable pageable) {

        return accountRepository.findAll(pageable);
    }

    @Override
    public Page<Account> search(SearchAccountReq accountRequest) {
        long minPrice = accountRequest.getMinPrice();
        long maxPrice = accountRequest.getMaxPrice();
        if (minPrice >= maxPrice) {
            throw new AppException(ErrorResponseBase.Min_Max);
        }
        Specification<Account> condition = AccountSpecification.buildCondition(accountRequest);
        PageRequest pageRequest = BaseRequest.buildPageRequest(accountRequest);

        return accountRepository.findAll(condition, pageRequest);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optional = accountRepository.findByUsername(username);
        if (optional.isPresent()) {
            Account account = optional.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(account.getRole().name()));
            return new User(account.getUsername(), account.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }


}
