/*
 * package com.users.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.users.com.users.service.IRoleService; import
 * com.users.entity.Roles; import com.users.entity.Users;
 * 
 * import lombok.RequiredArgsConstructor; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import static
 * org.springframework.http.HttpStatus.FOUND; import static
 * org.springframework.http.HttpStatus.CREATED;;
 * 
 * // OPEN API DIRECTORY : http://localhost:8085/swagger-ui/index.html
 * 
 * @RestController
 * 
 * @RequestMapping("/roles")
 * 
 * @RequiredArgsConstructor public class RoleController {
 * 
 * private final IRoleService roleService;
 * 
 * @GetMapping("/all") public ResponseEntity<List<Roles>> getAllRoles() { return
 * new ResponseEntity<>(roleService.getAllRoles(), FOUND);
 * 
 * }
 * 
 * @PostMapping("/create") public ResponseEntity<Roles> createRole(@RequestBody
 * Roles role) {
 * 
 * return new ResponseEntity<>(roleService.createRole(role), CREATED); }
 * 
 * @DeleteMapping("/delete/{id}") public void deleteRole(@PathVariable("id")
 * Long roleId) { roleService.deleteRole(roleId); }
 * 
 * @PostMapping("/remove-user-from-role") public Users
 * removeUserFromRole(@RequestParam("userId") Long
 * userId, @RequestParam("roleId") Long roleId) { return
 * roleService.removeUserFromRole(userId, roleId); }
 * 
 * @PostMapping("assign-user-to-role") public Users
 * assignUserToRole(@RequestParam("userId") Long userId, @RequestParam("roleId")
 * Long roleId) { return roleService.assignUserToRole(userId, roleId); }
 * 
 * }
 */