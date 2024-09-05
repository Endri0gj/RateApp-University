package rate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import rate.model.User;

@Repository
public interface UserRepo extends  JpaRepository<User,Integer> {
    @Query(value = "SELECT COUNT(*) FROM user u WHERE u.email = :email ", nativeQuery = true)
    int countUsersWithEmail(@Param("email") String email);

    @Query(value = "SELECT DISTINCT * FROM user u WHERE u.email = :email ", nativeQuery = true)
    User getUserByEmail(@Param("email") String email);

      /*
    Sr.No	Method & Description
1
count(): long

returns the number of entities available.

2
delete(Employee entity): void

deletes an entity.

3
deleteAll():void

deletes all the entities.

4
deleteAll(Iterable< extends Employee > entities):void

deletes the entities passed as argument.

5
deleteAll(Iterable< extends Integer > ids):void

deletes the entities identified using their ids passed as argument.

6
existsById(Integer id):boolean

checks if an entity exists using its id.

7
findAll():Iterable< Employee >

returns all the entities.

8
findAllByIds(Iterable< Integer > ids):Iterable< Employee >

returns all the entities identified using ids passed as argument.

9
findById(Integer id):Optional< Employee >

returns an entity identified using id.

10
save(Employee entity): Employee

saves an entity and return the updated one.

11
saveAll(Iterable< Employee> entities): Iterable< Employee>

saves all entities passed and return the updated entities.


     */
}
