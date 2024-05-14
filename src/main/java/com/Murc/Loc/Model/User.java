package com.Murc.Loc.Model;





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "oc_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String firstName;
    private String lastName;
    private String roleId;
    @Column(unique = true)
    private String email;
    private String password;
    private String telephone;
    private String image;
    private String description;
    private int zoneId;
}
/* 
    private List<User> users = new ArrayList<>();

    @GetMapping
    public Iterable<User> getUser() {
        return users;
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int userId)
    {
        for(User needUser : users){
            if(needUser.getUserId() == userId){
                return Optional.of(needUser);
            }
        }
        return Optional.empty();
    }
    @PostMapping
    public User postUser(@RequestBody User newUser){
        users.add(newUser);
        return newUser;
    }
    @DeleteMapping("/user/del/{id}")
    public void deleteUser(@PathVariable int userId){
        users.removeIf(needUser -> needUser.getUserId() == userId);
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<User> putUser(@PathVariable int userId, @RequestBody User user){
        int userIndex = -1;

        for(User needUser : users){
            if(needUser.getUserId() == userId){
                userIndex = users.indexOf(needUser);
                users.set(userIndex,user);
            }
        }
        return (userIndex == -1) ?
         new ResponseEntity<>(postUser(user), HttpStatus.CREATED) : 
         new ResponseEntity<>(user, HttpStatus.OK);
    }*/