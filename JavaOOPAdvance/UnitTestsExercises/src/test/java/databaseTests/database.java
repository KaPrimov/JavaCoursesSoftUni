package databaseTests;


import org.junit.Assert;
import org.junit.Before;
import database.IDatabase;
import database.Database;
import database.Person;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class database {

    private Person[] arr;
    private Person[] longWrongArr;
    private Person testGosho;

    @Before
    public void initializeObjects() {
        this.arr = new Person[16];
        for (int i = 0; i < 15; i++) {
            this.arr[i] = new Person(i, "Pesho" + i);
        }
        this.longWrongArr = new Person[25];
        for (int i = 0; i < 25; i++) {
            this.longWrongArr[i] = new Person(i, "Pesho" + i);
        }
        testGosho = new Person(1488, "Gosho");
      }

    @Test(expected = OperationNotSupportedException.class)
    public void tryToAddArrayWithBiggerLength() throws OperationNotSupportedException {
        IDatabase<Person> database = new Database<Person>(this.longWrongArr);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void tryToAddEmptyArray() throws OperationNotSupportedException {
        IDatabase<Person> database = new Database<Person>();
    }

    @Test
    public void addsElementAtTheLastCell() throws OperationNotSupportedException {
        IDatabase<Person> database = new Database<Person>(arr);
        database.remove();
        database.add(this.testGosho);
        Person[] array = database.fetch();
        Assert.assertTrue(array[database.getIndex()].equals(this.testGosho));
    }

    @Test
    public void removesOnlyTheLastElement() throws OperationNotSupportedException {
        IDatabase<Person> database = new Database<Person>(arr);
        Person[] arrayAtBeginning = database.fetch();
        Person personToCheck = arrayAtBeginning[database.getIndex()-2];
        database.remove();
        Person[] array = database.fetch();
        Assert.assertTrue(array[database.getIndex()].equals(personToCheck));
    }

    @Test
    public void fetchReturnPersonArray() throws OperationNotSupportedException {
        IDatabase<Person> database = new Database<Person>(arr);
        database.remove();
        Person[] array = database.fetch();
        Assert.assertTrue(array instanceof Person[]);
    }

    @Test
    public void findByIDReturnsCorrectUser() throws OperationNotSupportedException {
        IDatabase<Person> database = new Database<Person>(arr);

        Person personToCompare = this.arr[0];
        Person personFromMethod = database.fetch()[0];

        Assert.assertEquals(personToCompare, personFromMethod);
    }
}
