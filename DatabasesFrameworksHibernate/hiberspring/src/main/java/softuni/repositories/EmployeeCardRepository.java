package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.entities.EmployeeCard;

import java.util.List;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Long> {
    EmployeeCard findByNumber(String number);

    @Query("SELECT ec FROM EmployeeCard AS ec " +
            "WHERE ec.id NOT IN (SELECT e.employeeCard.id FROM Employee AS e)" +
            "ORDER BY ec.id")
    List<EmployeeCard> findAllUnusedEmployeeCards();
    /*
    * *
     * select * from employee_cards as ec
     where ec.id not in (select e.card_id from employees as e)
     order by ec.id
     */
}
