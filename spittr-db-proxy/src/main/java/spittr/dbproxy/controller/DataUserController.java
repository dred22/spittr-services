package spittr.dbproxy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spittr.data.UserDao;
import spittr.data.model.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping(path = "/data/users")
public class DataUserController {

    private UserDao userDao;

    public DataUserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers(@RequestParam("page") Optional<Integer> pageNumber) {
        PageRequest page;
        if (pageNumber.isPresent()) {
            page = PageRequest.of(pageNumber.get(), 5);
        } else {
            page = PageRequest.of(0, 5);
        }
        log.info("Getting users, page=[{}]", page);
        List<UserEntity> entitiesList = StreamSupport.stream(userDao.findAll(page).spliterator(), false)
                .collect(Collectors.toList());

        if (entitiesList.size() > 0) {
            return new ResponseEntity<>(entitiesList, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("{userName}")
    public ResponseEntity<UserEntity> getReference(@PathVariable String userName) {
        Optional<UserEntity> user = userDao.findById(userName);
        return user.map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
