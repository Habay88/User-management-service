/*
 * package com.users.controller;
 * 
 * import org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.users.com.users.service.UserRecord; import com.users.entity.Users;
 * import com.users.service.UserService;
 * 
 * import lombok.RequiredArgsConstructor;
 * 
 * import java.util.List;
 * 
 * import org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestParam;
 * 
 * @RestController
 * 
 * @RequestMapping("/users")
 * 
 * @RequiredArgsConstructor public class UserController { private final
 * UserService userService;
 * 
 * @GetMapping("/all") public ResponseEntity<List<UserRecord>> getAllUsers() {
 * return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND); }
 * 
 * @PostMapping("/add") public ResponseEntity<Users> add(@RequestBody Users
 * user) { return ResponseEntity.ok(userService.add(user)); }
 * 
 * @GetMapping("/{email}") public Users getByEmail(@PathVariable("email") String
 * email) { return userService.getUser(email); }
 * 
 * @DeleteMapping("/{email}") public void delete(@PathVariable("email") String
 * email) { userService.delete(email); }
 * 
 * @PutMapping("/update") public ResponseEntity<Users> update(@RequestBody Users
 * user) { return ResponseEntity.ok(userService.update(user)); }
 * 
 * }
 */